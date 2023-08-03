package tat.neft.files
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

@Serializable
data class MyFile(
    val icon: String = "",
    val text: String = "",
    val url: String = ""
)
