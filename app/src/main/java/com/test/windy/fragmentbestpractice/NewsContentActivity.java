package com.test.windy.fragmentbestpractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.test.windy.fragmentbestpractice.fragment.NewsContentFragment;

/**
 * Created by windog on 2016/5/22.
 */
public class NewsContentActivity extends Activity {

    /* 此方法是给 “此活动的调用方” 用的 ，对方只需把我要的参数丢进这个方法即可。
     * */
    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);

        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);

        context.startActivity(intent);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_content);

        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");

        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().
                findFragmentById(R.id.news_content_fragment);

        newsContentFragment.refresh(newsTitle, newsContent);
    }

}
