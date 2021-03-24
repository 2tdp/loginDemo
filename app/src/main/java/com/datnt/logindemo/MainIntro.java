package com.datnt.logindemo;

import Fragments.FragmentIntro1;
import Fragments.FragmentIntro2;
import Fragments.FragmentIntro3;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainIntro extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private LinearLayout dot_Layout;
    private TextView[] dots;
    private TextView tvSkip, tvContineu;
    private int currentPage;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intro);

        pager = findViewById(R.id.pager);
        dot_Layout = findViewById(R.id.llDot_layout);
        tvSkip = findViewById(R.id.tvSkipIntro);
        tvContineu = findViewById(R.id.tvContineuIntro);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentIntro1());
        fragmentList.add(new FragmentIntro2());
        fragmentList.add(new FragmentIntro3());

        pagerAdapter = new SlideFragmentLoginAdapter(getSupportFragmentManager(), fragmentList);
        pager.setAdapter(pagerAdapter);

        addDotIndicator(0);
        clickSkip(tvSkip);
        pager.addOnPageChangeListener(viewListener);

        tvContineu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPage == 2) {
                    Intent intent = new Intent(getBaseContext(), ContentMain.class);
                    startActivity(intent);
                    finish();
                } else {
                    pager.setCurrentItem(currentPage + 1);
                }
            }
        });
    }

    public void addDotIndicator(int position) {
        dots = new TextView[3];
        dot_Layout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setAlpha(Float.parseFloat("0.24"));
            dots[i].setTextColor(getResources().getColor(R.color.orangeMain));

            dot_Layout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setAlpha(Float.parseFloat("1"));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotIndicator(position);
            currentPage = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void clickSkip(TextView tvSkip) {
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), ContentMain.class);
                startActivity(intent);
                finish();
            }
        });
    }
}