package com.example.recept.data

import com.example.recept.model.Ingredient
import com.example.recept.model.Recipe

val sampleRecipes = listOf(
    Recipe(
        name = "Pannkakor",
        ingredients = listOf(
            Ingredient(name = "vetemjöl", quantity = 2.5, unit = "dl"),
            Ingredient(name = "mjölk", quantity = 6.0, unit = "dl"),
            Ingredient(name = "ägg", quantity = 3.0, unit = "st"),
            Ingredient(name = "salt", quantity = 0.5, unit = "tsk"),
            Ingredient(name = "smör", quantity = 2.0, unit = "msk"),
        ),
        steps = listOf(
            "Vispa ihop mjöl och hälften av mjölken",
            "Vispa ner resten av mjölken, äggen och saltet",
            "Stek tunna pannkakor i smör",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Tomatpasta",
        ingredients = listOf(
            Ingredient(name = "pasta", quantity = 400.0, unit = "g"),
            Ingredient(name = "krossade tomater", quantity = 800.0, unit = "g"),
            Ingredient(name = "vitlök", quantity = 3.0, unit = "klyftor"),
            Ingredient(name = "olivolja", quantity = 2.0, unit = "msk"),
            Ingredient(name = "basilika", quantity = 1.0, unit = "kruka"),
        ),
        steps = listOf(
            "Koka pastan",
            "Fräs vitlöken och sjud tomatsåsen",
            "Vänd ihop med pasta och basilika",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Omelett",
        ingredients = listOf(
            Ingredient(name = "ägg", quantity = 8.0, unit = "st"),
            Ingredient(name = "mjölk", quantity = 4.0, unit = "msk"),
            Ingredient(name = "ost", quantity = 100.0, unit = "g"),
            Ingredient(name = "salt", quantity = 0.5, unit = "tsk"),
            Ingredient(name = "svartpeppar", quantity = 0.25, unit = "tsk"),
        ),
        steps = listOf(
            "Vispa ihop ägg och mjölk",
            "Stek på medelvärme",
            "Strö över ost, vik ihop och servera",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Köttbullar",
        ingredients = listOf(
            Ingredient(name = "blandfärs", quantity = 500.0, unit = "g"),
            Ingredient(name = "lök", quantity = 1.0, unit = "st"),
            Ingredient(name = "ägg", quantity = 1.0, unit = "st"),
            Ingredient(name = "ströbröd", quantity = 1.0, unit = "dl"),
            Ingredient(name = "mjölk", quantity = 1.0, unit = "dl"),
            Ingredient(name = "salt", quantity = 1.0, unit = "tsk"),
        ),
        steps = listOf(
            "Blanda ströbröd och mjölk, låt svälla",
            "Finhacka löken och blanda med färs, ägg och kryddor",
            "Rulla bullar och stek i smör",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Kycklingcurry",
        ingredients = listOf(
            Ingredient(name = "kycklingfilé", quantity = 500.0, unit = "g"),
            Ingredient(name = "kokosmjölk", quantity = 400.0, unit = "ml"),
            Ingredient(name = "currypasta", quantity = 2.0, unit = "msk"),
            Ingredient(name = "paprika", quantity = 2.0, unit = "st"),
            Ingredient(name = "lök", quantity = 1.0, unit = "st"),
            Ingredient(name = "ris", quantity = 3.0, unit = "dl"),
        ),
        steps = listOf(
            "Skär kyckling och grönsaker i bitar",
            "Fräs lök och currypasta, tillsätt kyckling",
            "Häll på kokosmjölk och låt sjuda med paprikan",
            "Servera med kokt ris",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Grönsakssoppa",
        ingredients = listOf(
            Ingredient(name = "morötter", quantity = 3.0, unit = "st"),
            Ingredient(name = "potatis", quantity = 4.0, unit = "st"),
            Ingredient(name = "lök", quantity = 1.0, unit = "st"),
            Ingredient(name = "grönsaksbuljong", quantity = 1.5, unit = "l"),
            Ingredient(name = "grädde", quantity = 1.0, unit = "dl"),
        ),
        steps = listOf(
            "Skala och skär grönsakerna i bitar",
            "Koka mjuka i buljong",
            "Mixa slät och rör i grädden",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Lax med potatis",
        ingredients = listOf(
            Ingredient(name = "laxfilé", quantity = 600.0, unit = "g"),
            Ingredient(name = "potatis", quantity = 800.0, unit = "g"),
            Ingredient(name = "citron", quantity = 1.0, unit = "st"),
            Ingredient(name = "dill", quantity = 1.0, unit = "kruka"),
            Ingredient(name = "smör", quantity = 2.0, unit = "msk"),
        ),
        steps = listOf(
            "Koka potatisen mjuk",
            "Ugnsstek laxen med citron och dill i 180°C i 15 min",
            "Servera med smörig potatis",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Vegetarisk lasagne",
        ingredients = listOf(
            Ingredient(name = "lasagneplattor", quantity = 12.0, unit = "st"),
            Ingredient(name = "aubergine", quantity = 1.0, unit = "st"),
            Ingredient(name = "zucchini", quantity = 1.0, unit = "st"),
            Ingredient(name = "krossade tomater", quantity = 800.0, unit = "g"),
            Ingredient(name = "bechamelsås", quantity = 5.0, unit = "dl"),
            Ingredient(name = "ost", quantity = 200.0, unit = "g"),
        ),
        steps = listOf(
            "Skiva och stek grönsakerna",
            "Varva plattor, tomatsås, grönsaker och bechamel i en form",
            "Toppa med ost och gratinera i ugn 200°C i 30 min",
        ),
        portions = 6,
    ),
    Recipe(
        name = "Räksmörgås",
        ingredients = listOf(
            Ingredient(name = "skagenröra", quantity = 300.0, unit = "g"),
            Ingredient(name = "bröd", quantity = 4.0, unit = "skivor"),
            Ingredient(name = "ägg", quantity = 2.0, unit = "st"),
            Ingredient(name = "sallad", quantity = 1.0, unit = "st"),
            Ingredient(name = "citron", quantity = 1.0, unit = "st"),
        ),
        steps = listOf(
            "Koka äggen och skiva dem",
            "Bre bröd med smör och lägg på sallad",
            "Toppa med skagenröra, ägg och en klick citron",
        ),
        portions = 2,
    ),
    Recipe(
        name = "Fläskfilé med rödvinssås",
        ingredients = listOf(
            Ingredient(name = "fläskfilé", quantity = 600.0, unit = "g"),
            Ingredient(name = "rödvin", quantity = 2.0, unit = "dl"),
            Ingredient(name = "grädde", quantity = 2.0, unit = "dl"),
            Ingredient(name = "smör", quantity = 1.0, unit = "msk"),
            Ingredient(name = "potatis", quantity = 800.0, unit = "g"),
        ),
        steps = listOf(
            "Bryn fläskfilén runt om och stek klar i ugn 175°C",
            "Koka ner rödvin, tillsätt grädde och sjud till såsen tjocknar",
            "Servera med kokt potatis",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Tacos",
        ingredients = listOf(
            Ingredient(name = "blandfärs", quantity = 500.0, unit = "g"),
            Ingredient(name = "taco-kryddmix", quantity = 1.0, unit = "påse"),
            Ingredient(name = "tortillabröd", quantity = 8.0, unit = "st"),
            Ingredient(name = "tomat", quantity = 3.0, unit = "st"),
            Ingredient(name = "isbergssallad", quantity = 0.5, unit = "st"),
            Ingredient(name = "riven ost", quantity = 200.0, unit = "g"),
        ),
        steps = listOf(
            "Stek färsen och krydda med taco-kryddmix",
            "Skär tomat och sallad i bitar",
            "Fyll tortillabröden med färs och tillbehör",
        ),
        portions = 4,
    ),
    Recipe(
        name = "Chokladbollar",
        ingredients = listOf(
            Ingredient(name = "havregryn", quantity = 3.0, unit = "dl"),
            Ingredient(name = "socker", quantity = 1.0, unit = "dl"),
            Ingredient(name = "kakao", quantity = 2.0, unit = "msk"),
            Ingredient(name = "smör", quantity = 100.0, unit = "g"),
            Ingredient(name = "kallt kaffe", quantity = 2.0, unit = "msk"),
            Ingredient(name = "pärlsocker", quantity = 1.0, unit = "dl"),
        ),
        steps = listOf(
            "Blanda alla torra ingredienser",
            "Rör ner smör och kaffe till en smet",
            "Rulla bollar och rulla dem i pärlsocker",
        ),
        portions = 20,
    ),
    Recipe(
        name = "Halloumiwok",
        ingredients = listOf(
            Ingredient(name = "halloumi", quantity = 250.0, unit = "g"),
            Ingredient(name = "broccoli", quantity = 1.0, unit = "st"),
            Ingredient(name = "paprika", quantity = 2.0, unit = "st"),
            Ingredient(name = "sojasås", quantity = 3.0, unit = "msk"),
            Ingredient(name = "ris", quantity = 3.0, unit = "dl"),
        ),
        steps = listOf(
            "Skär halloumi och grönsaker i bitar",
            "Stek halloumin gyllenbrun och lägg åt sidan",
            "Woka grönsakerna, blanda i halloumi och sojasås, servera med ris",
        ),
        portions = 4,
    ),
)
