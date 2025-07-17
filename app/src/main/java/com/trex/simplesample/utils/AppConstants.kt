package com.trex.simplesample.utils

import com.trex.simplesample.domain.models.CountryDto
import com.trex.simplesample.domain.models.Language

object AppConstants {
    const val APP_NAME = "SimpleSample"
    const val DATABASE_NAME = "SIMPLE_SAMPLE_DB"
    const val SOURCE_ID = "sourceId"
    const val COUNTRY_ID = "countryId"
    const val LANGUAGE_ID = "languageId"
    const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "d25d4ab024364b73bbea0d394e583519"
    const val COUNTRY = "us"
    const val PAGE_SIZE = 10


    val COUNTRIES = listOf(
        CountryDto("ae", "United Arab Emirates"),
        CountryDto("ar", "Argentina"),
        CountryDto("at", "Austria"),
        CountryDto("be", "Belgium"),
        CountryDto("bg", "Bulgaria"),
        CountryDto("br", "Brazil"),
        CountryDto("ca", "Canada"),
        CountryDto("ch", "Switzerland"),
        CountryDto("cn", "China"),
        CountryDto("co", "Colombia"),
        CountryDto("cu", "Cuba"),
        CountryDto("cz", "Czechia"),
        CountryDto("de", "Germany"),
        CountryDto("eg", "Egypt"),
        CountryDto("fr", "France"),
        CountryDto("gb", "United Kingdom of Great Britain and Northern Ireland"),
        CountryDto("gr", "Greece"),
        CountryDto("hk", "Hong Kong"),
        CountryDto("hu", "Hungary"),
        CountryDto("id", "Indonesia"),
        CountryDto("ie", "Ireland"),
        CountryDto("il", "Israel"),
        CountryDto("in", "India"),
        CountryDto("it", "Italy"),
        CountryDto("jp", "Japan"),
        CountryDto("kr", "Korea"),
        CountryDto("lt", "Lithuania"),
        CountryDto("lv", "Latvia"),
        CountryDto("ma", "Morocco"),
        CountryDto("mx", "Mexico"),
        CountryDto("my", "Malaysia"),
        CountryDto("ng", "Nigeria"),
        CountryDto("nl", "Netherlands"),
        CountryDto("no", "Norway"),
        CountryDto("nz", "New Zealand"),
        CountryDto("ph", "Philippines"),
        CountryDto("pl", "Poland"),
        CountryDto("pt", "Portugal"),
        CountryDto("ro", "Romania"),
        CountryDto("rs", "Serbia"),
        CountryDto("ru", "Russian Federation"),
        CountryDto("sa", "Saudi Arabia"),
        CountryDto("se", "Sweden"),
        CountryDto("sg", "Singapore"),
        CountryDto("si", "Slovenia"),
        CountryDto("sk", "Slovakia"),
        CountryDto("th", "Thailand"),
        CountryDto("tr", "Turkiye"),
        CountryDto("tw", "Taiwan, Province of China"),
        CountryDto("ua", "Ukraine"),
        CountryDto("us", "United States of America"),
        CountryDto("ve", "Venezuela"),
        CountryDto("za", "South Africa")
    )

    val LANGUAGES = listOf(
        Language("ar", "Arabic"),
        Language("de", "German"),
        Language("en", "English"),
        Language("fr", "French"),
        Language("he", "Hebrew"),
        Language("it", "Italian"),
        Language("nl", "Dutch"),
        Language("no", "Norwegian"),
        Language("pt", "Portuguese"),
        Language("ru", "Russian"),
        Language("sv", "Swedish"),
        Language("zh", "Chinese")
    )

}