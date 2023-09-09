package tat.neft.main.work_with_files
import kotlinx.serialization.Serializable

@Serializable
data class MyFile(
    val icon: String = "",
    val text: String = "",
    val url: String = ""
)
