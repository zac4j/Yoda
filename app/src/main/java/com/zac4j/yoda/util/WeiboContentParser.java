package com.zac4j.yoda.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

/**
 * Text process helper
 * Created by zac on 4/10/2017.
 */

public class WeiboContentParser {

  private static final int MAX_NICKNAME_LENGTH = 25;

  public static void setupText(TextView textView, String content) {
    SpannableString spannableString = new SpannableString(content);

    int startNameIndex = 0;
    int startTopicIndex = 0;

    boolean hasAtSignal = false; // if has @Name signal
    boolean hasSharpSignal = false;// if has #Topic# signal

    textView.setVisibility(View.VISIBLE);

    for (int i = 0; i < content.length(); i++) {
      if (content.charAt(i) == '@') {
        startNameIndex = i;
        hasAtSignal = true;
      }

      if (hasAtSignal && i > startNameIndex && i < content.length() - 1) {
        if (content.charAt(i) == ' ' || content.charAt(i) == ':' || content.charAt(i) == '：') {
          setClickableSpan(spannableString, startNameIndex, i);
          hasAtSignal = false;
        }
      }

      if (!hasSharpSignal && content.charAt(i) == '#') {
        startTopicIndex = i;
        // System.out.println("start sharp index: " + startTopicIndex);
        hasSharpSignal = true;
      }

      if (hasSharpSignal && i > startTopicIndex && i < content.length() - 1) {
        if (content.charAt(i) == '#') {
          setClickableSpan(spannableString, startTopicIndex, i + 1);
          hasSharpSignal = false;
        }
      }
      textView.setText(spannableString);
      textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
  }

  private static void setClickableSpan(SpannableString spannableString, int startIndex,
      int endIndex) {
    if (startIndex >= endIndex || endIndex - startIndex > MAX_NICKNAME_LENGTH) {
      return;
    }
    // System.out.println("startIndex: " + startIndex + " : " + "endIndex: " + endIndex);
    ClickableSpan clickableSpan = new ClickableSpan() {
      @Override public void onClick(View widget) {
        // Click Event
      }
    };
    spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
}
