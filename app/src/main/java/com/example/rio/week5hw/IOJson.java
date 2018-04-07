package com.example.rio.week5hw;
//To JsonString Lib

import java.io.IOException;

public class IOJson {
    private enum Pos {
        TOP, MIDDLE, BOTTOM;
    }

    public static String toJSONString(String...strings) throws Exception {
        if((strings.length% 2) != 0 | (strings.length < 2))
            throw new IOException("The input must be even number and more than 2");
        StringBuffer buffer = new StringBuffer();
        if(strings.length == 2) {
            buffer.append("{");
            buffer.append(build(strings[0], strings[1]));
            buffer.append("}");
            return buffer.toString();
        }
        for(int i = 0; i < strings.length; i=i+2) {
            if(i == 0)
                buffer.append(build(strings[i], strings[i+1],Pos.TOP));
            else if(i == (strings.length - 2)) {
                buffer.append(build(strings[i], strings[i+1],Pos.BOTTOM));
            }
            else
                buffer.append(build(strings[i], strings[i+1],Pos.MIDDLE));
        }
        return buffer.toString();
    }

    private static String build(String title, String value) {
        return String.format("\"%s\":\"%s\"", title, value);
    }

    private static String build(String title, String value, Pos pos) throws Exception {

        switch (pos) {
            case TOP:
                return String.format("{\"%s\":\"%s\",", title, value);
            case MIDDLE:
                return String.format("\"%s\":\"%s\",", title, value);
            case BOTTOM:
                return String.format("\"%s\":\"%s\"}", title, value);
            default:
                throw new Exception("Position is not accepted");
        }

    }
}
