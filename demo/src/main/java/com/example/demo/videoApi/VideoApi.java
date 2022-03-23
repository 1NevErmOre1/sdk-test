package com.example.demo.videoApi;

import com.example.demo.resp.VideoListResponse;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author gjk
 * <p> 2022-03-21 </p>
 * <p> 抖开视频接口 </p>
 */
public interface VideoApi {
    /**
     * 用户视频列表获取
     * @param openId 用户openId
     * @param count 页面大小
     * @param cursor 游标 默认 0
     * @param token token
     * @return 返回体
     */
    @Headers({
            "Content-Type:application/json"
    })
    @GET("/video/list/")
    Call<VideoListResponse> videoList(@Query("open_id") String openId,
                                      @Query("count") Integer count,
                                      @Query("cursor") Integer cursor,
                                      @Header("access-token") String token);

}
