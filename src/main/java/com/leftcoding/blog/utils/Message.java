package com.leftcoding.blog.utils;

import java.io.Serializable;

/**
 * Created by XdaTk on 16/5/22.
 */
public class Message implements Serializable {

    public static final String MESSAGE_ATTRIBUTE = "message";

    public static enum Type {
        DANGER, WARNING, INFO, SUCCESS;
    }

    private final String message;
    private final Type type;
    private final Object[] args;

    public Message(String message, Type type) {
        this.message = message;
        this.type = type;
        this.args = null;
    }

    public Message(String message, Type type, Object... args) {
        this.message = message;
        this.type = type;
        this.args = args;
    }

    public String getMessage() {
        return message;
    }

    public Type getType() {
        return type;
    }

    public Object[] getArgs() {
        return args;
    }
}