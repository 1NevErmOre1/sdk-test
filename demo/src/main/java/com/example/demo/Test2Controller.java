package com.example.demo;

import com.example.demo.client.CommonClient;
import com.example.demo.resp.VideoListResponse;
import com.example.demo.videoApi.VideoApi;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author gjk
 * <p> 2022-03-21 </p>
 * <p>  </p>
 */
@RestController
public class Test2Controller {
    @Autowired
    private VideoApi videoApi;

    @RequestMapping("/test321")
    public void test(){
        Call<VideoListResponse> token = videoApi.videoList("cc1aee2d-df2e-4c9c-a0aa-16bc0b548cb8", 20, null, "act.26981f9093923c398122185bbcdd3e53JrhSfAVYr3rjbIlpcSKK3Q2qu2EY");
        try {
            VideoListResponse body = token.execute().body();
            System.out.println(body.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
