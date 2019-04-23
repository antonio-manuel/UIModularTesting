package eu.antoniolopez.playground.api.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import eu.antoniolopez.playground.api.BuildConfig
import eu.antoniolopez.playground.api.R
import eu.antoniolopez.playground.api.instrumentation.Configuration
import eu.antoniolopez.playground.api.instrumentation.ResponseInterceptor
import eu.antoniolopez.playground.api.moshi.MoshiCalendarAdapter
import eu.antoniolopez.playground.api.moshi.MoshiDateAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

val apiInfrastructureModule = Kodein.Module("apiModule") {

    val moshi by lazy {
        Moshi.Builder()
            .add(Date::class.java, MoshiDateAdapter().nullSafe())
            .add(Calendar::class.java, MoshiCalendarAdapter().nullSafe())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    fun buildRetrofit(baseUrl: String, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .baseUrl(baseUrl)
            .build()
    }
    bind<Moshi>() with singleton { moshi }

    bind<Configuration>() with singleton {
        val context: Context = instance()
        Configuration(
            context.getString(R.string.api_version),
            context.getString(R.string.api_base_url)
        )
    }
    bind<OkHttpClient>() with singleton {
        val responseInterceptor: ResponseInterceptor = instance()
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(responseInterceptor)
            .addInterceptor(logging)
            .build()
        okHttpClient
    }

    bind<ResponseInterceptor>() with singleton { ResponseInterceptor() }

    bind<Retrofit>() with singleton {
        val httpClient: OkHttpClient = instance()
        val configuration: Configuration = instance()
        buildRetrofit(configuration.baseUrl, httpClient)
    }
}
