package com.simplertutorials.android.wheathograophy.data.api

import com.simplertutorials.android.wheathograophy.data.api.util.NetworkError
import com.simplertutorials.android.wheathograophy.data.api.util.Result
import com.simplertutorials.android.wheathograophy.domain.ApiWeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import timber.log.Timber

class ApiService(
    private val httpClient: HttpClient
) {
    var BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val weather = "weather/"
    suspend fun getWeather(
        city: String,
        apiKey: String
    ): Result<ApiWeatherResponse, NetworkError> =
        makeNetworkRequest(
            requestBody = {
                httpClient.get("${BASE_URL}$weather") {
                    parameter("q", city)
                    parameter("appid", apiKey)
                }
            },
            onSuccess = {
                it.body<ApiWeatherResponse>()
            }
        )


    private suspend fun <T> makeNetworkRequest(
        requestBody: suspend () -> HttpResponse,
        onSuccess: suspend (HttpResponse) -> T
    ): Result<T, NetworkError> {

        val response = try {
            requestBody()
        } catch (e: UnresolvedAddressException) {
            Timber.w("no internet")
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            Timber.w("serialization error, ${e.message}")
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                Result.Success(onSuccess(response))
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }.also {
            Timber.w("response received $it // $response")
        }
    }
}