package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.Navigation;
import com.example.myapplication.R;
import com.example.myapplication.listener.onclicklistener.LogOutOnClickListener;
import com.google.android.material.navigation.NavigationView;

public class CamerasActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle ;
    Toolbar mToolbar;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameras);

        // TOOL BAR
        /*mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.drawer);
        mNavigationView = findViewById(R.id.navitationView);
        mNavigationView.setNavigationItemSelectedListener(this);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,mToolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mActionBarDrawerToggle.syncState();*/
        // END TOOL BAR

        // LOAD DEFAULT FRAGMENT
        /*mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.container_fragment,new ProfileFragment());
        mFragmentTransaction.commit();*/
        // END LOAD DEFAULT FRAGMENT

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Ferme le Drawer
        /*mDrawerLayout.closeDrawer(GravityCompat.START);

        if(item.getItemId() == R.id.profile){

            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.container_fragment,new ProfileFragment());
            mFragmentTransaction.commit();

        }

        if(item.getItemId() == R.id.button_add){

            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.container_fragment,new AddCameraFragment());
            mFragmentTransaction.commit();

        }*/

        return true;
    }
}
