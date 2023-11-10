package jp.ac.it_college.std.s22021.pokemonsilet2.database.dao;

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import jp.ac.it_college.std.nakasone.poke_api_prototype.database.entity.Pokemon

/**
 * ポケモンのデータにアクセスするためのDAO。多分必須。
 * どういうクエリが必要かは本番実装時に検討
 */
@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAll(): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE generation = :generation")
    fun findByGeneration(generation: Int): List<Pokemon>

    @Insert
    fun insert(pokemon: Pokemon)

    @Query("DELETE FROM pokemon")
    fun delete()
}
