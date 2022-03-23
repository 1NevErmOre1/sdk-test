package com.example.demo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author gjk
 * <p> 2022-03-23 </p>
 * <p>  </p>
 */
@Data
public class Extra {
    @JsonProperty("error_code")
    private Integer errorCode = null;
    @JsonProperty("description")
    private String description = null;
    @JsonProperty("sub_error_code")
    private Integer subErrorCode = null;
    @JsonProperty("sub_description")
    private String subDescription = null;
    @JsonProperty("logid")
    private String logid = null;
    @JsonProperty("now")
    private Integer now = null;
}
