package jp.ac.it_college.std.s22021.poke.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.jar.Attributes

@Serializable
data class Generation(
    val id: Int,
    val name: String,
    val names: List<Attributes.Name>,
    @SerialName("pokemon_species") val pokemonSpecies: List<NamedAPIResource>
)