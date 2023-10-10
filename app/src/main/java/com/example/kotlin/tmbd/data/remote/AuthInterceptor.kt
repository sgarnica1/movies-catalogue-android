package com.example.kotlin.tmbd.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authToken: String) : Interceptor {

    /**
     * @brief Este método intercepta la petición y añade el token de autenticación
     * @param chain
     * @return Response
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $authToken") // Add your authorization header here
            .build()
        return chain.proceed(request)
    }
}