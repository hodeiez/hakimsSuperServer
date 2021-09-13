package com.example.hakimssuperserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Created by Hodei Eceiza
 * Date: 9/10/2021
 * Time: 09:09
 * Project: hakimsSuperServer
 * Copyright: MIT
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailReq {
    @JsonProperty("email")
    private String sendTo;
    @JsonProperty("sender")
    private String mailfrom;
    @JsonProperty("content")
    private String content;
    @JsonProperty("name")
    private String name;

    //@JsonCreator
    public EmailReq(String sendTo, String mailfrom, String content, String name) {
        this.sendTo = sendTo;
        this.mailfrom = mailfrom;
        this.content=content;
        this.name = name;
    }
}
