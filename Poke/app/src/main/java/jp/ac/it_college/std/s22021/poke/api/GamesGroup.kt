package jp.ac.it_college.std.s22021.poke.api

import io.ktor.client.call.body
import jp.ac.it_college.std.s22021.poke.api.model.Generation

object GamesGroup {
    /**
     * 世代情報を取る
     */
    suspend fun getGeneration(gen: Int): Generation {
        return Client.get("/generation/$gen/").body()
    }
}