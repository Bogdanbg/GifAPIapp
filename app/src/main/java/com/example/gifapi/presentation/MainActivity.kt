package com.example.gifapi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gifapi.presentation.adapters.GifAdapter
import com.example.gifapi.data.GifResponse
import com.example.gifapi.R
import com.example.gifapi.data.network.RetrofitClient
import com.example.gifapi.data.network.GiphyApi
import com.example.gifapi.domain.GifModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val gifsList = mutableListOf<GifModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GifAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        adapter = GifAdapter(this,gifsList)
        recyclerView.adapter = adapter

        fetchGifsFromServer()
    }

    private fun fetchGifsFromServer() {
        val apiKey = "Q22n7OqnzmmMvOgr2E3pLLjLB6FA3pqM"
        val limit = 10

        val retrofit = RetrofitClient.getInstance()
        val giphyApi = retrofit.create(GiphyApi::class.java)

        val call = giphyApi.getTrendingGifs(apiKey,limit,0)

        call.enqueue(object : Callback<GifResponse>{
            override fun onResponse(call: Call<GifResponse>, response: Response<GifResponse>) {
                if (response.isSuccessful){
                    val gifResponse = response.body()
                    val gifs = gifResponse?.data

                    adapter.updateData(gifs)

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

    private fun showErrorToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        private const val TAG = "GifActivity"
    }
}