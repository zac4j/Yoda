package com.zac4j.yoda.util.weibo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import com.zac4j.yoda.CurrentActivityProvider;
import com.zac4j.yoda.ui.BrowserActivity;
import com.zac4j.yoda.util.StringUtil;
import com.zac4j.yoda.util.image.EmotionManager;
import com.zac4j.yoda.util.loader.WeiboImageLoader;
import org.w3c.dom.Text;

/**
 * Text process helper
 * Created by zac on 4/10/2017.
 */

public class WeiboParser {

    private static CurrentActivityProvider mActivityProvider;

    private static final int SPAN_NAME = 0x001;
    private static final int SPAN_TOPIC = 0x002;
    private static final int SPAN_LINK = 0x003;
    private static final int SPAN_EMOJI = 0x004;

    public WeiboParser() {

    }

    public static void setupText(TextView textView, String content) {
        mActivityProvider = (CurrentActivityProvider) textView.getContext().getApplicationContext();
        SpannableString spannableString = new SpannableString(content);

        int nameStartIndex = 0;
        int topicStartIndex = 0;
        int linkStartIndex = 0;
        int emojiStartIndex = 0;

        boolean hasAtSignal = false; // if has @Name signal
        boolean hasSharpSignal = false;// if has #Topic# signal
        boolean hasLinkSignal = false; // if has http/https link signal
        boolean hasEmojiSignal = false; // if has emoji link signal
        boolean isAsyncProcessing = false; // load emoji is a async process.

        textView.setVisibility(View.VISIBLE);

        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '@') {
                nameStartIndex = i;
                hasAtSignal = true;
            }

            if (hasAtSignal && i > nameStartIndex && i < content.length() - 1) {
                if (content.charAt(i) == ' '
                    || content.charAt(i) == ':'
                    || content.charAt(i) == '：') {
                    setSpan(textView, SPAN_NAME, spannableString, nameStartIndex, i);
                    hasAtSignal = false;
                }
            }

            if (!hasSharpSignal && content.charAt(i) == '#') {
                topicStartIndex = i;
                // System.out.println("start sharp index: " + startTopicIndex);
                hasSharpSignal = true;
            }

            if (hasSharpSignal && i > topicStartIndex && i < content.length() - 1) {
                if (content.charAt(i) == '#') {
                    setSpan(textView, SPAN_TOPIC, spannableString, topicStartIndex, i + 1);
                    hasSharpSignal = false;
                }
            }

            if (content.charAt(i) == 'h' && i + 8 < content.length() - 1) {
                String subContent = content.substring(i, i + 8);
                if (subContent.startsWith("http://") || subContent.startsWith("https://")) {
                    hasLinkSignal = true;
                    linkStartIndex = i;
                }
            }

            if (hasLinkSignal && i > linkStartIndex) {
                if (content.charAt(i) == ' ' || StringUtil.isChinese(content.charAt(i))) {
                    setSpan(textView, SPAN_LINK, spannableString, linkStartIndex, i);
                    hasLinkSignal = false;
                }
            }

            if (content.charAt(i) == '[') {
                hasEmojiSignal = true;
                emojiStartIndex = i;
            }

            if (hasEmojiSignal && i > emojiStartIndex && i < content.length() - 1) {
                if (content.charAt(i) == ']') {
                    hasEmojiSignal = false;
                    isAsyncProcessing = true;
                    setSpan(textView, SPAN_EMOJI, spannableString, emojiStartIndex, i + 1);
                }
            }

            if (i == content.length() - 1) {
                if (hasAtSignal) {
                    setSpan(textView, SPAN_NAME, spannableString, nameStartIndex, i + 1);
                    hasAtSignal = false;
                } else if (hasSharpSignal) {
                    setSpan(textView, SPAN_TOPIC, spannableString, topicStartIndex, i + 1);
                    hasSharpSignal = false;
                } else if (hasLinkSignal) {
                    setSpan(textView, SPAN_LINK, spannableString, linkStartIndex, i + 1);
                    hasLinkSignal = false;
                } else if (hasEmojiSignal) {
                    isAsyncProcessing = true;
                    setSpan(textView, SPAN_EMOJI, spannableString, emojiStartIndex, i + 1);
                }
            }

            if (!isAsyncProcessing) {
                textView.setText(spannableString, TextView.BufferType.SPANNABLE);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }
    }

    private static void setSpan(TextView textView, int spanType, SpannableString spannableString, int startIndex,
        int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        boolean shouldSetSpan = true;
        ClickableSpan clickableSpan = null;
        CharSequence spanContent = spannableString.subSequence(startIndex, endIndex);
        switch (spanType) {
            case SPAN_NAME:
            case SPAN_TOPIC:
                clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        dispatchSpanClickEvent(spanType, spanContent);
                    }
                };
                break;
            case SPAN_LINK:
                clickableSpan = new URLSpan(spanContent.toString()) {
                    @Override
                    public void onClick(View widget) {
                        dispatchSpanClickEvent(spanType, spanContent);
                    }
                };
                break;
            case SPAN_EMOJI:
                String emojiName = spannableString.subSequence(startIndex, endIndex).toString();
                String emojiUrl = EmotionManager.getInstance().getEmotion(emojiName);
                WeiboImageLoader.loadEmoji(textView, emojiUrl, spannableString, startIndex,
                    endIndex);
                shouldSetSpan = false;
                break;
        }

        // System.out.println("startIndex: " + startIndex + " : " + "endIndex: " + endIndex);

        if (shouldSetSpan) {
            spannableString.setSpan(clickableSpan, startIndex, endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private static void dispatchSpanClickEvent(int spanType, CharSequence spanContent) {
        switch (spanType) {
            case SPAN_NAME:
                break;
            case SPAN_TOPIC:
                break;
            case SPAN_LINK:
                Activity currentActivity = mActivityProvider.provideCurrentActivity();
                String link = spanContent.toString();
                currentActivity.startActivity(
                    new Intent(currentActivity, BrowserActivity.class).putExtra(
                        BrowserActivity.EXTRA_LINK, link));
                break;
        }
    }
}
