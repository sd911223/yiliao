package com.platform.util;

public class StringUtil {

    public String[] getStingArray(String Str) {
        if (Str.contains("-")) {
            Str = Str.replace("-", "");
        }
        if (Str.contains("[")) {
            Str = Str.replace("[", "");
        }
        if (Str.contains("]")) {
            Str = Str.replace("]", "");
        }
        if (Str.contains("\"")) {
            Str = Str.replace("\"", "");
        }
        if (Str.contains("None")) {
            Str = Str.replace("None", "");
        }
        if (Str.contains(",")) {
            String[] split = Str.split(",");
            return new String[]{split[0], split[1]};
        }
        return null;
    }
}
