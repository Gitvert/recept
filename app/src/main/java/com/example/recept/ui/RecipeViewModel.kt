package com.example.recept.ui

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.recept.ReceptApplication
import com.example.recept.R
import com.example.recept.model.Recipe
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val RECIPES_COLLECTION = "recipes"

/** Each Firestore document stores the recipe as a serialized JSON string under this field. */
private const val RECIPE_JSON_FIELD = "json"

// Lenient so extra/missing fields in stored recipes don't break decoding.
private val json = Json { ignoreUnknownKeys = true }

class RecipeViewModel(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val uiState: StateFlow<RecipeUiState> = _uiState.asStateFlow()

    /**
     * Ensures a signed-in Google user (auto-firing the sign-in flow on first launch),
     * then fetches the recipe collection once. Safe to call again to retry after an error.
     * [activityContext] must be an Activity context so Credential Manager can show its UI.
     */
    fun signInAndLoad(activityContext: Context) {
        _uiState.value = RecipeUiState.Loading
        viewModelScope.launch {
            val signedIn = auth.currentUser != null || trySignIn(activityContext)
            if (!signedIn) {
                _uiState.value = RecipeUiState.Error("Kunde inte logga in. Försök igen.")
                return@launch
            }
            loadRecipes()
        }
    }

    private suspend fun trySignIn(context: Context): Boolean {
        val googleIdOption = GetGoogleIdOption.Builder()
            // false = let the user pick any Google account on first sign-in, not just
            // previously-authorised ones.
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.default_web_client_id))
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        return try {
            val result = CredentialManager.create(context).getCredential(context, request)
            val idToken = GoogleIdTokenCredential.createFrom(result.credential.data).idToken
            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(firebaseCredential).await()
            true
        } catch (e: GetCredentialException) {
            false
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun loadRecipes() {
        _uiState.value = try {
            val snapshot = firestore.collection(RECIPES_COLLECTION).get().await()
            val recipes = snapshot.documents.mapNotNull { doc ->
                val raw = doc.getString(RECIPE_JSON_FIELD) ?: return@mapNotNull null
                // Skip any document whose JSON is malformed rather than failing the whole load.
                runCatching { json.decodeFromString<Recipe>(raw).copy(id = doc.id) }.getOrNull()
            }
            RecipeUiState.Success(recipes)
        } catch (e: Exception) {
            RecipeUiState.Error("Kunde inte hämta recept. Försök igen.")
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as ReceptApplication
                RecipeViewModel(app.firestore, FirebaseAuth.getInstance())
            }
        }
    }
}
