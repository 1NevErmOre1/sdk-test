package com.example.demo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author gjk
 * <p> 2022-03-22 </p>
 * <p>  </p>
 */
@Data
public class Video {
    @JsonProperty("item_id")
    private String itemId = null;
    @JsonProperty("title")
    private String title = null;
    @JsonProperty("cover")
    private String cover = null;
    @JsonProperty("is_top")
    private Boolean isTop = null;
    @JsonProperty("create_time")
    private Long createTime = null;
    @JsonProperty("is_reviewed")
    private Boolean isReviewed = null;
    @JsonProperty("share_url")
    private String shareUrl = null;
    @JsonProperty("video_status")
    private Integer videoStatus = null;
    @JsonProperty("statistics")
    private VideoStatistics statistics = null;
    @JsonProperty("media_type")
    private Integer mediaType;
}
