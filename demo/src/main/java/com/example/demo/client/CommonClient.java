package com.example.demo.client;

import com.example.demo.videoApi.VideoApi;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;

/**
 * @author gjk
 * <p> 2022-03-21 </p>
 * <p>  </p>
 */
@NoArgsConstructor
public class CommonClient {

    private String clientKey;

    private String clientSecret;

    public final static String baseUrl = "https://open.douyin.com/";

    public CommonClient(String clientKey, String clientSecret){
        this.clientKey = clientKey;
        this.clientSecret = clientSecret;
    }
    public VideoApi initVideoApiClient() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return build.create(VideoApi.class);
    }

}
