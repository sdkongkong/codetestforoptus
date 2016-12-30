package com.kevinsong.optus.codetestoptus.network;


import com.kevinsong.optus.codetestoptus.model.Venue;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface VenuesApiService {

    @GET("sample.json")
    Observable<List<Venue>> getVenues();
}