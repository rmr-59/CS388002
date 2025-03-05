package com.example.rameshrusselllab4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.rameshrusselllab4.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
//import com.codepath.asynchttpclient.BuildConfig
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.DeserializationStrategy
//import com.example.rameshrusselllab4.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val ARTICLE_SEARCH_URL =
    "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=${SEARCH_API_KEY}"

class MainActivity : AppCompatActivity() {
    private val articles = mutableListOf<DisplayArticle>()
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // TODO: Set up ArticleAdapter with articles
        val articleAdapter = ArticleAdapter(this@MainActivity, articles)
        lifecycleScope.launch {
            (application as ArticleApplication).db.articleDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayArticle(
                        entity.headline,
                        entity.articleAbstract,
                        entity.byline,
                        entity.mediaImageUrl
                    )
                }.also { mappedList ->
                    articles.clear()
                    articles.addAll(mappedList)
                    articleAdapter.notifyDataSetChanged()
                }
            }
        }
        super.onCreate(savedInstanceState)
        articlesRecyclerView= findViewById(R.id.articles)
        articlesRecyclerView.adapter = articleAdapter

        articlesRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            articlesRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        val client = AsyncHttpClient()
        client.get(ARTICLE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched articles: $json")
                try {
                    // TODO: Create the parsedJSON


                    // TODO: Do something with the returned json (contains article information)
                    val parsedJson = createJson().decodeFromString<SearchNewsResponse>(
                        json.jsonObject.toString()
                    )
                    // TODO: Save the articles and reload the screen
                    parsedJson.response?.docs?.let { list ->
                        lifecycleScope.launch(IO){
                        (application as ArticleApplication).db.articleDao().deleteAll()
                        (application as ArticleApplication).db.articleDao().insertAll(
                            list.map {
                                ArticleEntity(
                                    headline = it.headline?.main,
                                    articleAbstract = it.abstract,
                                    byline = it.byline?.original,
                                    mediaImageUrl = it.mediaImageUrl
                                )
                            })
                    }

                        articleAdapter.notifyDataSetChanged()


                    }
                }catch (e: JSONException) {
                        Log.e(TAG, "Exception: $e")
                    }
                }



        })
    }
}