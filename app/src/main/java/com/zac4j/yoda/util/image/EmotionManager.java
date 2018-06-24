package com.zac4j.yoda.util.image;

import android.text.style.ImageSpan;
import com.zac4j.yoda.data.model.EmotionEntry;
import com.zac4j.yoda.util.loader.WeiboImageLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zac on 2018/6/20.
 * Description:Helper class for manage emotions.
 */
public class EmotionManager {

    private static EmotionManager sInstance;
    private Map<String, String> emotionContainer;
    private boolean mInitializedEmotionContainer;

    private EmotionManager() {
    }

    public static EmotionManager getInstance() {
        if (sInstance == null) {
            sInstance = new EmotionManager();
        }
        return sInstance;
    }

    public void saveEmotions(List<EmotionEntry> emotionEntries) {

        if (mInitializedEmotionContainer) {
            return;
        }

        if (emotionContainer == null) {
            emotionContainer = new HashMap<>();
            mInitializedEmotionContainer = true;
        }
        // Save emotions url into memory
        for (EmotionEntry entry : emotionEntries) {
            emotionContainer.put(entry.getPhrase(), entry.getIcon());
        }
    }

    public String getEmotion(String phrase) {
        return emotionContainer.get(phrase);
    }

}
