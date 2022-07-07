package com.example.cyrptographer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cyrptographer.databinding.ActivityCryptBinding;
import com.example.cyrptographer.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class crypt extends AppCompatActivity {

   private ActivityCryptBinding binding;
    ViewPager2 viewPager2 ;
    TabLayout tabLayout ;
    FragmentAdaptor adaptor;
    androidx.appcompat.widget.Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCryptBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarr);

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager2 = findViewById(R.id.view_pager2);
         tabLayout = findViewById(R.id.tabs);
        FragmentManager fm = getSupportFragmentManager();
        adaptor = new FragmentAdaptor(fm,getLifecycle());
        viewPager2.setAdapter(adaptor);
       new TabLayoutMediator(tabLayout,viewPager2,
        new TabLayoutMediator.TabConfigurationStrategy(){

            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0)
                {
                    tab.setText("Text");
                }
                if(position==1)
                {
                    tab.setText("Document");
                }

            }

        }).attach();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });










       /* FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.encryptmenu:
                startActivity(new Intent(crypt.this, crypt.class));
                Toast.makeText(getApplicationContext(),"Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.decryptmenu:
                startActivity(new Intent(crypt.this, crypt.class));
                Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}