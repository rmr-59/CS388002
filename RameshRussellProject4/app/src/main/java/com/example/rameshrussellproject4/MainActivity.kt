package com.example.rameshrussellproject4

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.example.rameshrussellproject4.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val API_KEY ="345a12b06ac8255a05602d6308840ce4"
private const val token ="Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNDVhMTJiMDZhYzgyNTVhMDU2MDJkNjMwODg0MGNlNCIsIm5iZiI6MTczOTg1ODY0My45OCwic3ViIjoiNjdiNDIyZDNmMzVkNDEwMjA5NmRjYWI1Iiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.OAR44dpGPkd_zQxLXZl_eYUf7k774DTpJO-jWYsktw8"
private const val Actor_Url="https://api.themoviedb.org/3/person/popular?"
class MainActivity : AppCompatActivity() {
    private val actors = mutableListOf<Actor>()
    private lateinit var actorRecyclerView: RecyclerView
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val actorAdapter = ActorAdapter(this,actors)
        actorRecyclerView = findViewById(R.id.actors)
        actorRecyclerView.layoutManager = GridLayoutManager(this,2)
        //actorRecyclerView.layoutManager = LinearLayoutManager(this)
        actorRecyclerView.adapter= actorAdapter
        val client = AsyncHttpClient()
        val parms = RequestParams()
        val auth = RequestHeaders()
        parms["api-key"]= API_KEY
        auth["Authorization"]= token
        Log.d("auth",auth.toString())
        client.get(Actor_Url,auth,parms,object:JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                //TODO("Not yet implemented")
                Log.e("Error", "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                //TODO("Not yet implemented")
                Log.i("worked",json.toString())
                try{
                    val parsedJSON = createJson().decodeFromString<ActorResponse>(
                        json?.jsonObject.toString()
                    )
                    parsedJSON.response?.let{
                        list->actors.addAll(list)
                    }
                    actorAdapter.notifyDataSetChanged()
                }
                catch(e: JSONException) {
                    Log.e("Error", "Exception: $e")
                }
            }

        })

    }
}