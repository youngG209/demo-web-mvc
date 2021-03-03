package com.demowebmvc;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Event {

    interface ValidateLimit {}
    interface ValidateName {}

    private Integer id;

    @NotBlank(groups = ValidateName.class)
    private String name;

//    @Min(최소값) : 최소값 설정
    @Min(value = 0, groups = ValidateLimit.class)
    private Integer limit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setName(String name) {
        this.name = name;
    }
}
