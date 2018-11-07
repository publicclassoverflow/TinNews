package com.coollime.tinnews.retrofit;

import com.coollime.tinnews.retrofit.response.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsRequestApi {
    // Calling newapi.org with
    // https://newsapi.org/v2/top-headlines?country=us
    @GET("top-headlines")
    Observable<BaseResponse> getNewsByCountry(@Query("country") String country);
}
