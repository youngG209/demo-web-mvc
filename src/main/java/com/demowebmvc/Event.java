package com.demowebmvc;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Event {
//    @Validated 그룹 설정
    interface ValidateLimit {}
    interface ValidateName {}

    private Integer id;

//    @Validated 그룹 설정
//    @NotBlank(groups = ValidateName.class)
    @NotBlank
    private String name;

//    @Min(최소값) : 최소값 설정
//    @Validated 그룹 설정
//    @Min(value = 0, groups = ValidateLimit.class)
    @Min(value = 0)
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
