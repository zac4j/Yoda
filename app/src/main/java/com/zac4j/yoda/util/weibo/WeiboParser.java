package com.zac4j.yoda.util.weibo;

import android.app.Activity;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.zac4j.yoda.App;
import com.zac4j.yoda.CurrentActivityProvider;
import com.zac4j.yoda.ui.BrowserActivity;
import com.zac4j.yoda.ui.WebViewActivity;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Text process helper
 * Created by zac on 4/10/2017.
 */

@Singleton
public class WeiboParser {

    private CurrentActivityProvider mActivityProvider;

    private static final int MAX_NICKNAME_LENGTH = 25;
    private static final int SPAN_NAME = 0x001;
    private static final int SPAN_TOPIC = 0x002;
    private static final int SPAN_LINK = 0x003;

    @Inject
    public WeiboParser(CurrentActivityProvider activityProvider) {
        mActivityProvider = activityProvider;
    }

    public void setupText(TextView textView, String content) {
        SpannableString spannableString = new SpannableString(content);

        int nameStartIndex = 0;
        int topicStartIndex = 0;
        int linkStartIndex = 0;

        boolean hasAtSignal = false; // if has @Name signal
        boolean hasSharpSignal = false;// if has #Topic# signal
        boolean hasLinkSignal = false; // if has http/https link signal

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
                    setClickableSpan(SPAN_NAME, spannableString, nameStartIndex, i);
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
                    setClickableSpan(SPAN_TOPIC, spannableString, topicStartIndex, i + 1);
                    hasSharpSignal = false;
                }
            }

            if (content.charAt(i) == 'h') {
                String subContent = content.substring(i, i + 8);
                if (subContent.startsWith("http://") || subContent.startsWith("https://")) {
                    hasLinkSignal = true;
                    linkStartIndex = i;
                }
            }

            if (hasLinkSignal && i > linkStartIndex && content.charAt(i) == ' ') {
                setClickableSpan(SPAN_LINK, spannableString, linkStartIndex, i);
            }

            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void setClickableSpan(int spanType, SpannableString spannableString, int startIndex,
        int endIndex) {
        if (startIndex >= endIndex || endIndex - startIndex > MAX_NICKNAME_LENGTH) {
            return;
        }
        // System.out.println("startIndex: " + startIndex + " : " + "endIndex: " + endIndex);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                CharSequence spanContent = spannableString.subSequence(startIndex, endIndex);
                dispatchSpanClickEvent(spanType, spanContent);
            }
        };
        spannableString.setSpan(clickableSpan, startIndex, endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void dispatchSpanClickEvent(int spanType, CharSequence spanContent) {
        switch (spanType) {
            case SPAN_NAME:
                break;
            case SPAN_TOPIC:
                break;
            case SPAN_LINK:
                Activity currentActivity = mActivityProvider.provideCurrentActivity();
                String link = spanContent.toString();
                currentActivity.startActivity(new Intent(currentActivity, BrowserActivity.class)
                    .putExtra(BrowserActivity.EXTRA_LINK, link));
                break;
        }
    }
}
