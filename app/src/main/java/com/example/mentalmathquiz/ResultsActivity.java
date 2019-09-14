package com.example.mentalmathquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ResultsActivity extends AppCompatActivity implements OETab.OnFragmentInteractionListener ,
MCTab.OnFragmentInteractionListener, GameTab.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem mcTab, oeTab, gameTab;
    public PagerAdapter pagerAdapter;

    private String quizlength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        quizlength = intent.getStringExtra(NumberInputActivity.quizlength);

        tabLayout = findViewById(R.id.tablayout);
        mcTab = findViewById(R.id.MCTab);
        oeTab = findViewById(R.id.OETab);
        gameTab = findViewById(R.id.GameTab);
        viewPager = findViewById(R.id.view_pager);




        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    public void onFragmentInteraction(Uri uri) {
        //
    }

    public String getQuizLength() {
        return quizlength;
    }

}
