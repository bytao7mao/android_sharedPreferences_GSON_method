package com.example.taolen.gson_demo;

import java.util.List;

/**
 * Created by taoLen on 4/10/2018.
 */

public class Employee {
    private String name;
    private String profession;
    private Integer profId;
    private List<String> roles;
    //private boolean, float, double

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getProfId() {
        return profId;
    }

    public void setProfId(Integer profId) {
        this.profId = profId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
