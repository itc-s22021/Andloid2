package jp.ac.it_college.std.s22021.pokemonsilet2.api

import io.ktor.client.call.body
import jp.ac.it_college.std.s22021.pokemonsilet2.model.NamedApiResourceList

object Games {
    /**
     * /generation エンドポイントへパラメータなしだと
     * [NamedApiResourceList] 型で取得できる。
     */
    public suspend fun getGenerations(): NamedApiResourceList {
        return ApiClient.get("/generation").body()
    }
}