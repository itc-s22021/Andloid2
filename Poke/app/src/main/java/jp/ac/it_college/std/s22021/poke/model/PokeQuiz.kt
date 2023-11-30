package jp.ac.it_college.std.s22021.poke.model

data class PokeQuiz(
    val imageUrl: String,
    val choices: List<String>,
    val correct: String
)