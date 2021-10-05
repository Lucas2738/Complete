package com.example.demo.model;


import com.example.demo.json.serialize.JsonSerializable;

import java.util.List;

public class Event implements JsonSerializable {
    private Integer year;
    private Integer num;
    private Integer gameCode;
    private String desc;
    private List<Integer> listInt;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getGameCode() {
        return gameCode;
    }

    public void setGameCode(Integer gameCode) {
        this.gameCode = gameCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Integer> getListInt() {
        return listInt;
    }

    public void setListInt(List<Integer> listInt) {
        this.listInt = listInt;
    }
}