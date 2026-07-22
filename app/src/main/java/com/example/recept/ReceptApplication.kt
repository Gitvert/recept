package com.example.recept

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Owns the process-wide Firestore handle as a lazy singleton. Firebase itself is
 * auto-initialised by the google-services plugin's FirebaseInitProvider, so there is
 * nothing to set up here beyond exposing the instance for the ViewModel factory.
 */
class ReceptApplication : Application() {
    val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
}
