package com.example.demo;

import com.example.demo.client.CommonClient;
import com.example.demo.videoApi.VideoApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gjk
 * <p> 2022-03-22 </p>
 * <p>  </p>
 */
@Configuration
public class init {

    @Bean
    public VideoApi initApi(){
      return new CommonClient().initVideoApiClient();
    }

}
