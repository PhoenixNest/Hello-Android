package com.dev.a0302json;

import java.io.Serializable;

public class Score implements Serializable {

    private int math;
    private int english;
    private int chinese;

    public Score(int math, int english, int chinese) {
        this.math = math;
        this.english = english;
        this.chinese = chinese;
    }

}
