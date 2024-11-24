package com.example.composelogin.storage

//import android.content.Context
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.core.stringPreferencesKey
//import androidx.datastore.preferences.preferencesDataStore
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//import javax.inject.Inject
//
//private val Context.dataStore by preferencesDataStore("auth_prefs")
//
//class TokenManager @Inject constructor(private val context: Context) {
//    private val TOKEN_KEY = stringPreferencesKey("auth_token")
//
//    val authToken: Flow<String?> = context.dataStore.data.map { prefs ->
//        prefs[TOKEN_KEY]
//    }
//
//    suspend fun saveToken(token: String) {
//        context.dataStore.edit { prefs ->
//            prefs[TOKEN_KEY] = token
//        }
//    }
//
//    suspend fun clearToken() {
//        context.dataStore.edit { prefs ->
//            prefs.remove(TOKEN_KEY)
//        }
//    }
//}