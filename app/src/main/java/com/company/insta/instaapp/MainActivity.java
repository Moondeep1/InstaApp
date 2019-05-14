package com.company.insta.instaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionDrawerToggle;
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate", "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //layout variables
        mDrawerLayout = findViewById(R.id.main_drawer_layout);
        mNavigationView = findViewById(R.id.main_nav_view);


        mActionDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mActionDrawerToggle);
        mActionDrawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         //
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if(item.getItemId()==R.id.profile)
                {
                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawer(Gravity.START );
                    return true;
                } else if(item.getItemId()==R.id.search)
               {
                   Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                   return true;
               }else if(item.getItemId()==R.id.log_out)
               {
                   Toast.makeText(MainActivity.this, "Log out   ", Toast.LENGTH_SHORT).show();
                   return true;
               }else
               {
                   Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
               }

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mActionDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        boolean isUserLoggedIn = SharedPreferenceManager.getInstance(getApplicationContext()).isUserLoggedIn();
        if (!isUserLoggedIn) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else {

        }
    }
}
