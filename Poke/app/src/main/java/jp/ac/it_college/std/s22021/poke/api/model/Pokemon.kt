package jp.ac.it_college.std.s22021.poke.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val sprites: PokemonSprites
)