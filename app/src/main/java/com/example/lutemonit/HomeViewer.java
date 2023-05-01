package com.example.lutemonit;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
public class HomeViewer extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = HomeViewer.this;
        setContentView(R.layout.activity_homeview);

        // Link code to layout things
        TabLayout tablayout = findViewById(R.id.tabLayout);
        ViewPager2 fragmentArea = findViewById(R.id.fragmentArea);
        // Create new fragment
        Fragment fragment = new FragmentBottom();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, fragment).commit();

        // Create new adapter
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);
        // add it to the fragment area
        fragmentArea.setAdapter(tabPagerAdapter);


        // ClickListener to tbalayout
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Set right tab-position to the fragment
                fragmentArea.setCurrentItem(tab.getPosition());
                Fragment fragment = new FragmentBottom();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // Set tabs underline to move right place when tab selected
        fragmentArea.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tablayout.getTabAt(position).select();
            }
        });
    }

    public Context getContext() {
        return context;
    }
}
