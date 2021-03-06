package com.xiarh.purenews.ui.news.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xiarh.purenews.R;
import com.xiarh.purenews.base.BaseFragment;
import com.xiarh.purenews.base.TitleAdapter;
import com.xiarh.purenews.config.Config;
import com.xiarh.purenews.ui.news.ScrollEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新闻主页
 * Created by xiarh on 2017/5/9.
 */

public class NewsHomeFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.news_home_viewpager_)
    ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"头条", "汽车", "足球", "娱乐", "体育", "财经", "科技", "电影"};

    private String[] mIDs = {Config.HEADLINE_ID, Config.CAR_ID, Config.FOOTBALL_ID, Config.ENTERTAINMENT_ID,
            Config.SPORTS_ID, Config.FINANCE_ID, Config.TECH_ID, Config.MOVIE_ID};

    private TitleAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_news;
    }

    @Override
    protected void init() {
        mFragments.add(NewsFragment.newInstance(Config.HEADLINE_ID));
        mFragments.add(NewsFragment.newInstance(Config.CAR_ID));
        mFragments.add(NewsFragment.newInstance(Config.FOOTBALL_ID));
        mFragments.add(NewsFragment.newInstance(Config.ENTERTAINMENT_ID));
        mFragments.add(NewsFragment.newInstance(Config.SPORTS_ID));
        mFragments.add(NewsFragment.newInstance(Config.FINANCE_ID));
        mFragments.add(NewsFragment.newInstance(Config.TECH_ID));
        mFragments.add(NewsFragment.newInstance(Config.MOVIE_ID));
        mAdapter = new TitleAdapter(getChildFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        int position = mViewPager.getCurrentItem();
        EventBus.getDefault().post(new ScrollEvent(mIDs[position]));
    }
}
