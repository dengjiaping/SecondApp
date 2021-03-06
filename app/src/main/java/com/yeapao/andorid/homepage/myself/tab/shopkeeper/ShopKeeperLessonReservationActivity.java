package com.yeapao.andorid.homepage.myself.tab.shopkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.common.collect.Lists;
import com.scottfu.sflibrary.springindicator.ScrollerViewPager;
import com.scottfu.sflibrary.springindicator.SpringIndicator;
import com.scottfu.sflibrary.util.SystemDateUtil;
import com.yeapao.andorid.R;
import com.yeapao.andorid.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;

/**
 * Created by fujindong on 2017/7/28.
 */

public class ShopKeeperLessonReservationActivity extends BaseActivity {

    private static final String TAG = "ShopKeeperLessonReservationActivity";

    ScrollerViewPager viewPager;

    private int currentDay = 0;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ShopKeeperLessonReservationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_reservation);
        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();
        viewPager.setCurrentItem(currentDay);

        // just set viewPager
        springIndicator.setViewPager(viewPager);

        initTopBar();
    }

    @Override
    protected void initTopBar() {
        initTitle("课程预约");
        initBack();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private List<String> getTitles(){
        ArrayList<String> days = new ArrayList<>();

//        TODO 计算时间

        String date = SystemDateUtil.getCurrentYYYYMMDD();
        String[] day = date.split("-");
        String[] w = SystemDateUtil.getCurrentWeek();
        for (int i = 0; i < w.length; i++) {

            if (w[i].equals(day[2])) {
                currentDay = i;
                days.add("今");
            } else {
                days.add(w[i]);
            }
        }
        return days;
    }

    private List<Integer> getBgRes(){
        return Lists.newArrayList(R.drawable.y_you, R.drawable.y_you, R.drawable.y_you, R.drawable.y_you
                , R.drawable.y_you, R.drawable.y_you, R.drawable.y_you);
    }


}
