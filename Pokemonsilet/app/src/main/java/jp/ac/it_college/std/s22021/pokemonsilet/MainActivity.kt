package jp.ac.it_college.std.s22021.pokemonsilet

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import jp.ac.it_college.std.s22021.pokemonsilet.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.URL
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    // OkHttpClientを作成
    private val client = OkHttpClient.Builder()
        .connectTimeout(100000, TimeUnit.MILLISECONDS)
        .readTimeout(100000.toLong(), TimeUnit.MILLISECONDS)
        .build()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //APIボタンが送信されると流れる処理
        binding.button.setOnClickListener {

            //画面から図鑑番号を受け取り
            var pokemonNumber = binding.editTextNumber.text
            //リクエストURLを作成
            var requestUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonNumber

            //別スレッドでAPIを処理
            Thread {
                //ハンドラーを生成(別スレッドで画面を操作するために必要)
                val handler : Handler = Handler(Looper.getMainLooper())

                //pokeAPIを叩く（レスポンスボディの取得）
                var responseBody = startGetRequest(requestUrl)

                if (responseBody != null) {
                    //JSONをPokemonクラスに変換
                    var pokemon = Json.decodeFromString<Pokemon>(responseBody)

                    //ポケモン名を取得
                    var pokemonName = pokemon.name

                    //ポケモンの画像を取得
                    var pokemonImage = pokemon.sprites?.frontDefault

                    //ポケモンのURL画像を画面に表示させる
                    coroutineScope.launch {
                        val originalDeferred = coroutineScope.async(Dispatchers.IO) {
                            getOriginalBitmap(pokemonImage)
                        }

                        val originalBitmap = originalDeferred.await()
                        loadImage(originalBitmap)
                    }

                    //メインスレッド意外ではこの処理が必要
                    handler.post {
                        //ポケモンの名前を表示
                        binding.textView.text = pokemonName
                    }
                } else {
                    //TODO:存在しないポケモンが入力されたらエラー処理
                }
            }.start()
        }
    }
    @Throws(IOException::class)
    private fun startGetRequest(url:String): String? {
        // リクエストを作成
        val request: Request = Request.Builder()
            .url(url)
            .build()

        //レスポンスの取得
        client.newCall(request).execute().use { response ->
            return response.body?.string()
        }
    }

    private fun getOriginalBitmap(imageUrl: String?): Bitmap =
        URL(imageUrl).openStream().use {
            BitmapFactory.decodeStream(it)
        }

    private fun loadImage(bmp: Bitmap) {
        val progressBar = binding.progressBar
        val imageView = binding.imageView
        progressBar.visibility = View.GONE
        imageView.setImageBitmap(bmp)
        imageView.visibility = View.VISIBLE
    }
}
