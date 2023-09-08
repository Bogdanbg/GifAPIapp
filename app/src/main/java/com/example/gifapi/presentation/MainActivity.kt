package com.example.gifapi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gifapi.GifFragment

import com.example.gifapi.presentation.adapters.GifAdapter
import com.example.gifapi.data.GifResponse
import com.example.gifapi.R
import com.example.gifapi.data.network.RetrofitClient
import com.example.gifapi.data.network.GiphyApi
import com.example.gifapi.databinding.ItemGifBinding
import com.example.gifapi.domain.GifModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), GifAdapter.OnItemClickListener {

    private val gifsList = mutableListOf<GifModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GifAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        adapter = GifAdapter(this)
        recyclerView.adapter = adapter

        fetchGifsFromServer()
    }

    private fun fetchGifsFromServer() {
        val apiKey = "Q22n7OqnzmmMvOgr2E3pLLjLB6FA3pqM"


        val retrofit = RetrofitClient.getInstance()
        val giphyApi = retrofit.create(GiphyApi::class.java)

        val call = giphyApi.getTrendingGifs(apiKey, GIF_LIMIT,0)

        call.enqueue(object : Callback<GifResponse>{
            override fun onResponse(call: Call<GifResponse>, response: Response<GifResponse>) {
                if (response.isSuccessful){
                    val gifResponse = response.body()
                    val gifs = gifResponse?.data

                    gifs?.let { adapter.updateData(it) }

                    adapter.notifyDataSetChanged()
                }else{
                    var errorBody = response.errorBody()?.string()
                    Log.e(TAG, "Failed to fetch GIFs: $errorBody")
                    showErrorToast("Gif loading error!")
                }
            }

            override fun onFailure(call: Call<GifResponse>, t: Throwable) {
                Log.e(TAG, "Network error: ${t.message}")
                showErrorToast("Network error")
            }
        })
    }

    private fun openFullScreenGifFragment(gifUrl: String){
        val fragment = GifFragment()
        val args = Bundle()
        args.putString("gif_url",gifUrl)
        fragment.arguments = args

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onGifItemClicked(gifUrl: String){
        openFullScreenGifFragment(gifUrl)
    }
    private fun showErrorToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        private const val TAG = "GifActivity"
        private const val GIF_LIMIT = 10

    }
}