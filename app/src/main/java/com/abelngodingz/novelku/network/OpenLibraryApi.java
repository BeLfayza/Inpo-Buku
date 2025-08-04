package com.abelngodingz.novelku.network;

import com.abelngodingz.novelku.response.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import com.abelngodingz.novelku.model.BookDetail;


public interface OpenLibraryApi {
    @GET("search.json")
    Call<SearchResponse> searchBooks(@Query("q") String query);

    @GET("{key}.json")
    Call<BookDetail> getBookDetail(@Path(value = "key", encoded = true) String key);

}
