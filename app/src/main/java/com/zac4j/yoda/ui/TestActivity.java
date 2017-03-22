package com.zac4j.yoda.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.model.Timeline;
import java.io.IOException;

/**
 * Test Activity
 * Created by zac on 3/22/2017.
 */

public class TestActivity extends AppCompatActivity {

  private Timeline mFuck;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    String text = "{\n"
        + "  \"statuses\": [\n"
        + "    {\n"
        + "      \"created_at\": \"Tue Mar 21 14:08:22 +0800 2017\",\n"
        + "      \"id\": 4087739483481536,\n"
        + "      \"mid\": \"4087739483481536\",\n"
        + "      \"idstr\": \"4087739483481536\",\n"
        + "      \"text\": \"#送票##亚洲雷鬼姐妹花# 北京三月最值得期待的RASTA聚会！3.24@ModernSkyLAB北京\\n日本雷鬼邓丽君Machaco&大表姐@ChaCha_FadedGhost 领衔主演，@周CHRISTY @Cvalda 以及Avilox 共同献艺，更有北京HIP-HOP/雷鬼 领衔DJ/MC:@王波webber 亲自助阵。转发并@三位好友即有机会获得门票一张，3月23日抽6人。 \u200B\",\n"
        + "      \"textLength\": 277,\n"
        + "      \"source_allowclick\": 0,\n"
        + "      \"source_type\": 1,\n"
        + "      \"source\": \"<a href=\\\"http://app.weibo.com/t/feed/6vtZb0\\\" rel=\\\"nofollow\\\">微博 weibo.com</a>\",\n"
        + "      \"favorited\": false,\n"
        + "      \"truncated\": false,\n"
        + "      \"in_reply_to_status_id\": \"\",\n"
        + "      \"in_reply_to_user_id\": \"\",\n"
        + "      \"in_reply_to_screen_name\": \"\",\n"
        + "      \"pic_urls\": [\n"
        + "        {\n"
        + "          \"thumbnail_pic\": \"http://wx4.sinaimg.cn/thumbnail/666d40c1gy1fdufgvqyk4j20x31asqid.jpg\"\n"
        + "        }\n"
        + "      ],\n"
        + "      \"thumbnail_pic\": \"http://wx4.sinaimg.cn/thumbnail/666d40c1gy1fdufgvqyk4j20x31asqid.jpg\",\n"
        + "      \"bmiddle_pic\": \"http://wx4.sinaimg.cn/bmiddle/666d40c1gy1fdufgvqyk4j20x31asqid.jpg\",\n"
        + "      \"original_pic\": \"http://wx4.sinaimg.cn/large/666d40c1gy1fdufgvqyk4j20x31asqid.jpg\",\n"
        + "      \"geo\": null,\n"
        + "      \"user\": {\n"
        + "        \"id\": 1718436033,\n"
        + "        \"idstr\": \"1718436033\",\n"
        + "        \"class\": 1,\n"
        + "        \"screen_name\": \"虾米音乐\",\n"
        + "        \"name\": \"虾米音乐\",\n"
        + "        \"province\": \"33\",\n"
        + "        \"city\": \"1\",\n"
        + "        \"location\": \"浙江 杭州\",\n"
        + "        \"description\": \"【小虾米，大独家】虾米音乐与韩国S.M.、香港寰亚唱片、华研、BMG、滚石唱片、相信音乐、风潮音乐等厂牌达成独家版权战略合作，享有这些优秀的音乐厂牌在大陆地区所有数字音乐的独家版权。\",\n"
        + "        \"url\": \"http://www.xiami.com/\",\n"
        + "        \"profile_image_url\": \"http://tva3.sinaimg.cn/crop.15.19.325.325.50/666d40c1jw8faq5sbl8o0j209q09qdgd.jpg\",\n"
        + "        \"cover_image\": \"http://ww2.sinaimg.cn/crop.0.1.980.300/666d40c1jw1ekho2umcidj20r808d3zy.jpg\",\n"
        + "        \"cover_image_phone\": \"http://ww1.sinaimg.cn/crop.0.0.640.640.640/638f41a8jw1exw22gdukxj20hs0hstgp.jpg\",\n"
        + "        \"profile_url\": \"xiamixiamixiami\",\n"
        + "        \"domain\": \"xiamixiamixiami\",\n"
        + "        \"weihao\": \"\",\n"
        + "        \"gender\": \"f\",\n"
        + "        \"followers_count\": 4470666,\n"
        + "        \"friends_count\": 679,\n"
        + "        \"pagefriends_count\": 57,\n"
        + "        \"statuses_count\": 23072,\n"
        + "        \"favourites_count\": 92,\n"
        + "        \"created_at\": \"Thu Mar 25 14:00:14 +0800 2010\",\n"
        + "        \"following\": true,\n"
        + "        \"allow_all_act_msg\": true,\n"
        + "        \"geo_enabled\": false,\n"
        + "        \"verified\": true,\n"
        + "        \"verified_type\": 2,\n"
        + "        \"remark\": \"\",\n"
        + "        \"insecurity\": {\n"
        + "          \"sexual_content\": false\n"
        + "        },\n"
        + "        \"ptype\": 200,\n"
        + "        \"allow_all_comment\": true,\n"
        + "        \"avatar_large\": \"http://tva3.sinaimg.cn/crop.15.19.325.325.180/666d40c1jw8faq5sbl8o0j209q09qdgd.jpg\",\n"
        + "        \"avatar_hd\": \"http://tva3.sinaimg.cn/crop.15.19.325.325.1024/666d40c1jw8faq5sbl8o0j209q09qdgd.jpg\",\n"
        + "        \"verified_reason\": \"虾米音乐网官方微博\",\n"
        + "        \"verified_trade\": \"\",\n"
        + "        \"verified_reason_url\": \"\",\n"
        + "        \"verified_source\": \"\",\n"
        + "        \"verified_source_url\": \"\",\n"
        + "        \"verified_state\": 0,\n"
        + "        \"verified_level\": 3,\n"
        + "        \"verified_type_ext\": 0,\n"
        + "        \"pay_remind\": 4,\n"
        + "        \"pay_date\": \"20100512\",\n"
        + "        \"has_service_tel\": false,\n"
        + "        \"verified_reason_modified\": \"\",\n"
        + "        \"verified_contact_name\": \"虾小米客服\",\n"
        + "        \"verified_contact_email\": \"\",\n"
        + "        \"verified_contact_mobile\": \"\",\n"
        + "        \"follow_me\": false,\n"
        + "        \"online_status\": 0,\n"
        + "        \"bi_followers_count\": 489,\n"
        + "        \"lang\": \"zh-cn\",\n"
        + "        \"star\": 0,\n"
        + "        \"mbtype\": 12,\n"
        + "        \"mbrank\": 3,\n"
        + "        \"block_word\": 0,\n"
        + "        \"block_app\": 1,\n"
        + "        \"credit_score\": 80,\n"
        + "        \"user_ability\": 1540,\n"
        + "        \"cardid\": \"star_001\",\n"
        + "        \"urank\": 38\n"
        + "      },\n"
        + "      \"reposts_count\": 16,\n"
        + "      \"comments_count\": 13,\n"
        + "      \"attitudes_count\": 44,\n"
        + "      \"isLongText\": false,\n"
        + "      \"mlevel\": 0,\n"
        + "      \"visible\": {\n"
        + "        \"type\": 0,\n"
        + "        \"list_id\": 0\n"
        + "      },\n"
        + "      \"biz_feature\": 0,\n"
        + "      \"page_type\": 32,\n"
        + "      \"hasActionTypeCard\": 0,\n"
        + "      \"darwin_tags\": [],\n"
        + "      \"hot_weibo_tags\": [],\n"
        + "      \"text_tag_tips\": [],\n"
        + "      \"rid\": \"0_0_1_2775559006590978551\",\n"
        + "      \"userType\": 0,\n"
        + "      \"cardid\": \"star_001\",\n"
        + "      \"positive_recom_flag\": 0,\n"
        + "      \"gif_ids\": \"\",\n"
        + "      \"is_show_bulletin\": 2\n"
        + "    }\n"
        + "  ],\n"
        + "  \"advertises\": [],\n"
        + "  \"ad\": [],\n"
        + "  \"hasvisible\": false,\n"
        + "  \"previous_cursor\": 0,\n"
        + "  \"next_cursor\": 4087737395533666,\n"
        + "  \"total_number\": 150,\n"
        + "  \"interval\": 2000,\n"
        + "  \"uve_blank\": -1,\n"
        + "  \"since_id\": 4087739483481536,\n"
        + "  \"max_id\": 4087737395533666,\n"
        + "  \"has_unread\": 0\n"
        + "}";

    ObjectMapper mapper = new ObjectMapper();
    try {
      mFuck = mapper.readValue(text, Timeline.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (mFuck != null) {
      System.out.println("Fuck : " + mFuck);
    }
  }
}
