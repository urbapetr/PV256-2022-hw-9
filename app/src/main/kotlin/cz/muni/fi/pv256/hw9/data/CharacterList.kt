package cz.muni.fi.pv256.hw9.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterList(
    @Json(name = "results") val characters: List<Character>
)
