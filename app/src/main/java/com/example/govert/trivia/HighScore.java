package com.example.govert.trivia;

import java.io.Serializable;

public class HighScore implements Serializable {
    private String nickName;
    private Integer score;

    public HighScore(String nickName, Integer score) {
        this.nickName = nickName;
        this.score = score;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
