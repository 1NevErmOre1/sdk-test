package com.example.demo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author gjk
 * <p> 2022-03-22 </p>
 * <p>  </p>
 */
@Data
public class VideoListResponseData {


    @JsonProperty("error_code")
    private Integer errorCode = null;
    @JsonProperty("description")
    private String description = null;
    @JsonProperty("cursor")
    private Long cursor = null;
    @JsonProperty("has_more")
    private Boolean hasMore = null;
    @JsonProperty("list")
    private List<Video> list = null;
}
