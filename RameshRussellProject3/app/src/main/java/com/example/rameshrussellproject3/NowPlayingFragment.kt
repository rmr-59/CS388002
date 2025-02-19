package com.example.rameshrussellproject3;

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject
import com.example.rameshrussellproject3.R.id
import com.google.gson.JsonObject
import org.json.JSONArray

private const val API_KEY ="345a12b06ac8255a05602d6308840ce4"
private const val token ="Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNDVhMTJiMDZhYzgyNTVhMDU2MDJkNjMwODg0MGNlNCIsIm5iZiI6MTczOTg1ODY0My45OCwic3ViIjoiNjdiNDIyZDNmMzVkNDEwMjA5NmRjYWI1Iiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.OAR44dpGPkd_zQxLXZl_eYUf7k774DTpJO-jWYsktw8"
class NowPlayingFragment : Fragment(), OnListFragmentInteractionListener{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_now_playing_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        val auth = RequestHeaders()
        params["api-key"] = API_KEY
        auth.put("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNDVhMTJiMDZhYzgyNTVhMDU2MDJkNjMwODg0MGNlNCIsIm5iZiI6MTczOTg1ODY0My45OCwic3ViIjoiNjdiNDIyZDNmMzVkNDEwMjA5NmRjYWI1Iiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.OAR44dpGPkd_zQxLXZl_eYUf7k774DTpJO-jWYsktw8")

        //Using the client, perform the HTTP request
        client[
            "https://api.themoviedb.org/3/movie/now_playing",
            auth,
            params,
            object : JsonHttpResponseHandler()

            {
                /*
                 * The onSuccess function gets called when
                 * HTTP response status is "200 OK"
                 */
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    //TODO - Parse JSON into Models
                    val resultsJSON : JSONObject= json.jsonObject
                    val moviesRawJSON : String = resultsJSON.getString("results")
                    Log.d("result",moviesRawJSON)

                    val gson = Gson()
                    val arrayMovieType = object : TypeToken<List<NowPlaying>>() {}.type

                    val models : List<NowPlaying> = gson.fromJson(moviesRawJSON,arrayMovieType) // Fix me!
                    recyclerView.adapter = NowPlayingRecyclerViewAdapter(models, this@NowPlayingFragment)

                    // Look for this in Logcat:
                    Log.d("NowPlaying", "response successful$moviesRawJSON")


                }

                /*
                 * The onFailure function gets called when
                 * HTTP response status is "4XX" (eg. 401, 403, 404)
                 */
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    // If the error is not null, log it!
                    t?.message?.let {
                        Log.e("NowPlaying", errorResponse)
                    }
                }
            }]


    }
    override fun onItemClick(item: NowPlaying) {
        TODO("Not yet implemented")
    }

}
