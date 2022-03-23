package com.example.demo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author gjk
 * <p> 2022-03-22 </p>
 * <p>  </p>
 */
@Data
public class VideoListResponse {
    @JsonProperty("data")
    private VideoListResponseData data = null;
    @JsonProperty("extra")
    private Extra extra = null;
}
