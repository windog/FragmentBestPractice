package com.test.windy.fragmentbestpractice.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.test.windy.fragmentbestpractice.NewsContentActivity;
import com.test.windy.fragmentbestpractice.R;
import com.test.windy.fragmentbestpractice.adapter.NewsAdapter;
import com.test.windy.fragmentbestpractice.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windog on 2016/5/22.
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsAdapter adapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();  //获取新闻，数据源即从这里来
        adapter = new NewsAdapter(activity, R.layout.news_item, newsList);   //这里错了，adapter 需要的是Listview子项布局ID
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);

        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;   //可以找到news_content_layout则为双页模式
        } else {
            isTwoPane = false;
        }
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);

        if (isTwoPane) {
            //双页模式，只更改右侧 fragment 内容即可
            NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().
                    findFragmentById(R.id.news_content_fragment);

            newsContentFragment.refresh(news.getTitle(), news.getContent());
        } else {
            // 单页模式 ，转到 NewsContentActivity
            NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
        }


    }

    public List<News> getNews() {
        List<News> newsList = new ArrayList<News>();

        News news1 = new News();
        news1.setTitle("Exclusive: Suppliers question Tesla's goals for Model 3 output");
        news1.setContent("Tesla Motors Inc has surprised parts makers with plans to move up the launch of high-volume production of its Model 3 to 2018, two years earlier than planned - an acceleration that supplier executives and industry consultants said would be difficult to achieve and potentially costly.\n" +
                "\n" +
                "In the past three months, Tesla (TSLA.O) has told suppliers the company was doubling its original production projections to 100,000 Model 3s in 2017 and 400,000 in 2018, several supplier industry executives familiar with the plans told Reuters.\n" +
                "\n" +
                "Details on Model 3 production projections have not been reported previously, and Tesla did not break out target volumes for the Model 3.\n" +
                "\n" +
                "Tesla has taken 373,000 orders for the Model 3 - which has a starting price of $35,000, about half its Model S - and has said it would begin customer deliveries in late 2017. But it has made no promises, and, on earlier models, customers waited months for delivery.\n" +
                "\n" +
                "Citing \"tremendous demand,\" Chief Executive Elon Musk told analysts on an April call that the company planned to boost total production, including the existing Model S and Model X crossover, to 500,000 in 2018 - two years earlier than its original target and a 10-fold increase over the 50,000 vehicles it made in 2015.");
        newsList.add(news1);

        News news2 = new News();
        news2.setTitle("Microsoft to crack down on content promoting extremist acts");
        news2.setContent("With the world growing more concerned about attacks by militant groups on civilians, Microsoft Corp on Friday outlined new policies to crack down what it called \"terrorist content\" on some of its consumer services.\n" +
                "\n" +
                "In a blog post, the company said it would ban what it called \"terrorist content\" on some services such as gaming tool Xbox Live, the consumer version of its Outlook email service, and its consumer documents-sharing service.\n" +
                "\n" +
                "But for its search engine Bing, Microsoft cited free expression and said it would remove links only when that \"is required of search providers under local law.\"\n" +
                "\n" +
                "Initially, Microsoft will rely on consumers to report objectionable content. The company also said it would fund research of a tool that scans content and flags images, audio and video.\n" +
                "\n" +
                "\"We will consider terrorist content to be material posted by or in support of organizations included on the Consolidated United Nations Security Council Sanctions List that depicts graphic violence, encourages violent action, endorses a terrorist organization or its acts, or encourages people to join such groups,\" the blog post said.");
        newsList.add(news2);

        News news3 = new News();
        news3.setTitle("Panasonic can speed up Tesla plant investment if needed");
        news3.setContent("Japan's Panasonic Corp is ready to bring forward its investment in a Tesla battery plant it is helping establish if this is required to meet demand for the electric car maker's upcoming Model 3 sedan.\n" +
                "\n" +
                "\"We will do our best to move up the schedule if requested,\" Yoshio Ito, head of Panasonic's automotive and industrial systems (AIS) division, told reporters at a briefing on Friday.\n" +
                "\n" +
                "Panasonic plans to contribute $1.6 billion to Tesla's $5 billion \"Gigafactory\" in phases over the next few years. Production of the batteries is due to start later this year.\n" +
                "\n" +
                "A faster ramp-up of the battery plant would be crucial as Tesla has said it would respond to brisk demand for the Model 3 by tooling up its factories to build 500,000 vehicles a year in 2018, two years earlier than planned.");
        newsList.add(news3);

        return newsList;
    }
}
