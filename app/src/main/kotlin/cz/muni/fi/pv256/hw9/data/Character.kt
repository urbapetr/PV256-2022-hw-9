package cz.muni.fi.pv256.hw9.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
) : Parcelable
