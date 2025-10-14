// kotlin
package com.example.examen.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.examen.domain.model.Country
import com.example.examen.data.local.model.userCache
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext context: Context,
    private val gson: Gson,
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(
            PreferencesConstants.PREF_NAME,
            Context.MODE_PRIVATE,
        )

    fun saveCountryList(
        countryList: List<Country>,
        offset: Int,
        totalCount: Int,
    ) {
        prefs
            .edit()
            .putString(PreferencesConstants.KEY_COUNTRY_CACHE, gson.toJson(countryList))
            .putLong(PreferencesConstants.KEY_LAST_UPDATE, System.currentTimeMillis())
            .putInt(PreferencesConstants.KEY_OFFSET, offset)
            .putInt(PreferencesConstants.KEY_TOTAL_COUNT, totalCount)
            .apply()
    }

    fun getCountryCache(): userCache? {
        val json = prefs.getString(PreferencesConstants.KEY_COUNTRY_CACHE, null)
        val lastUpdate = prefs.getLong(PreferencesConstants.KEY_LAST_UPDATE, 0L)
        val offset = prefs.getInt(PreferencesConstants.KEY_OFFSET, 0)
        val totalCount = prefs.getInt(PreferencesConstants.KEY_TOTAL_COUNT, 0)

        if (json == null) return null

        val type = object : TypeToken<List<Country>>() {}.type
        val countryList: List<Country> = gson.fromJson(json, type)

        return userCache(
            countryList = countryList,
            lastUpdate = lastUpdate,
            offset = offset,
            totalCount = totalCount,
        )
    }

    fun isCacheValid(): Boolean {
        val lastUpdate = prefs.getLong(PreferencesConstants.KEY_LAST_UPDATE, 0L)
        return System.currentTimeMillis() - lastUpdate < PreferencesConstants.CACHE_DURATION
    }

    fun clearCache() {
        prefs.edit().clear().apply()
    }

    fun saveSelectedCountry(country: Country) {
        val json = gson.toJson(country)
        prefs.edit().putString("selected_country", json).apply()
    }

    fun getSelectedCountry(): Country? {
        val json = prefs.getString("selected_country", null) ?: return null
        return gson.fromJson(json, Country::class.java)
    }

}


