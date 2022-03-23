package com.example.demo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author gjk
 * <p> 2022-03-22 </p>
 * <p>  </p>
 */
@Data
public class VideoStatistics {
    @JsonProperty("comment_count")
    private Integer commentCount = null;
    @JsonProperty("digg_count")
    private Integer diggCount = null;
    @JsonProperty("download_count")
    private Integer downloadCount = null;
    @JsonProperty("play_count")
    private Integer playCount = null;
    @JsonProperty("share_count")
    private Integer shareCount = null;
    @JsonProperty("forward_count")
    private Integer forwardCount = null;
}
