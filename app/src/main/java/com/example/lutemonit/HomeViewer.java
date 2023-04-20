package com.example.lutemonit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
public class HomeViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeview);

        // Kiinnitetään tablayout ja fragmentArea koodiin
        TabLayout tablayout = findViewById(R.id.tabLayout);
        ViewPager2 fragmentArea = findViewById(R.id.fragmentArea);

        Fragment fragment = new FragmentBottom();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, fragment).commit();

        //Luodaan uusi Tabpageradapter olio
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);
        //Kiinnitetään se fragment-areaan
        fragmentArea.setAdapter(tabPagerAdapter);


        // ClickListener Tablayoutille
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Asetetaan fragmentiin oikea "tab-positio"
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
        // Asetetaan tabien "alaviivan" siirtyminen tabien vaihdon aikana
        fragmentArea.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tablayout.getTabAt(position).select();
            }
        });
    }
}
