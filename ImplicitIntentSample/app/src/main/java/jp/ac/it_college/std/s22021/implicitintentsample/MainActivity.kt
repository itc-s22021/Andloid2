package jp.ac.it_college.std.s22021.implicitintentsample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import jp.ac.it_college.std.s22021.implicitintentsample.databinding.ActivityMainBinding
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var onUpdateLocation: LocationCallback

    private var latitude = 0.0
    private var longitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btMapSearch.setOnClickListener(::onMapSearchButton)
    }

    private fun onMapSearchButton(view: View) {
        val searchWord = URLEncoder.encode(
            binding.etSearchWord.text.toString(), "UTF-8"
        )
        val uri = Uri.parse("geo:0,0?q=${searchWord}")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun onMapShowCurrentButtonClick(view: View) {
        val uri = Uri.parse("geo:${latitude},${longitude}")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}