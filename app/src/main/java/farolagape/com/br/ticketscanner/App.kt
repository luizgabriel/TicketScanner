package farolagape.com.br.ticketscanner

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import farolagape.com.br.ticketscanner.services.TicketsService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    private val mBaseUrl = "http://farolagape.herokuapp.com/"
    lateinit var ticketsService: TicketsService

    override fun onCreate() {
        super.onCreate()

        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(this.cacheDir, cacheSize.toLong())

        val okHttpClient = OkHttpClient.Builder()
                .cache(cache)
                .build()

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val builder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build()

        ticketsService = builder.create(TicketsService::class.java)
    }

}
