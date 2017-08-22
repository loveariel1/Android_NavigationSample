package com.ahsiu.navigationsample.epoxy;

/**
 * Created by jackie780919 on 2017/8/22.
 */

public class Comment {
    private final int id;
    private final String comment;

    public Comment(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }
}
