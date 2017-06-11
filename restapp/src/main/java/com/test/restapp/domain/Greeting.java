package com.test.restapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Greeting {

    private String name;

    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Greeting(String name) {
        this.name = name;
    }

    @JsonProperty( required = true)
    @ApiModelProperty(name = "welcoming the user", required = true)
    public String getMessage() {
        return "Welcome "+name;
    }
}
