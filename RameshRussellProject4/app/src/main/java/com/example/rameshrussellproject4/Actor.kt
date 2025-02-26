package com.example.rameshrussellproject4
import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ActorResponse(
    @SerialName ("results")
    val response: List<Actor>?
)
@Keep
@Serializable
class Actor (
    @SerialName ("name")
    val actorName: String?= null,
    @SerialName("profile_path")
    val actorPic: String?= null,
    @SerialName("known_for")
    val known:List<KnownFor>?
    ):java.io.Serializable

@Keep
@Serializable
data class KnownFor(
    @SerialName("name")
    val movieName: String?= null,
    @SerialName("title")
    val movieTitle: String?= null,
    @SerialName("poster_path")
    val moviePic: String?= null,
    @SerialName("overview")
    val movieDesc : String? = null
):java.io.Serializable
