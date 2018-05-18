package com.zac4j.yoda.common;

import java.util.HashMap;

/**
 * Created by zac on 2018/5/18.
 * Description:emoji library
 */
public class Emoji {

    private static final HashMap<String, String> sEmojiNameMap;

    static {
        sEmojiNameMap = new HashMap<>();
        sEmojiNameMap.put("开心", "\\ude04");
        sEmojiNameMap.put("伤心","\\udc94");
    }

    public static String parseEmoji(String name) {
        if (sEmojiNameMap.containsKey(name)) {
            return sEmojiNameMap.get(name);
        }
        return "\\ude22";
    }
}
