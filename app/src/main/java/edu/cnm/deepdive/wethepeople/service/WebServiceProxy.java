package edu.cnm.deepdive.wethepeople.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.wethepeople.BuildConfig;
import edu.cnm.deepdive.wethepeople.model.dto.SearchResult;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServiceProxy {

  /**
   *
   * @param searchTerm A list of search terms to implement
   * @param apiKey an Api key to get data
   */
  @GET("v4/documents")
  Single<SearchResult> getHits(
      @Query("filter[searchTerm]") String searchTerm,
      @Query("api_key") String apiKey);

  static WebServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }


  class InstanceHolder {

    private static final WebServiceProxy INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .baseUrl(BuildConfig.BASE_URL)
          .client(client)
          .build();
      INSTANCE = retrofit.create(WebServiceProxy.class);
    }


  }
}