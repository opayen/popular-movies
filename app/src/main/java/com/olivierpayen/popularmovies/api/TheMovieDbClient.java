package com.olivierpayen.popularmovies.api;

import com.olivierpayen.popularmovies.BuildConfig;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class TheMovieDbClient {
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private TheMovieDbInterface apiService;

    public TheMovieDbClient() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.httpUrl().newBuilder().addQueryParameter("api_key", BuildConfig.theMovieDbApiKey).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(TheMovieDbInterface.class);
    }

    public TheMovieDbInterface getApiService() {
        return apiService;
    }
}
