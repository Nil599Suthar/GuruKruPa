package com.example.gurukrupa.Activity_Pkg;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.gurukrupa.R;
import com.example.gurukrupa.Api_Models.model;
import com.example.gurukrupa.toplist;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    List<model> row_list=new ArrayList<>();
    com.example.gurukrupa.toplist toplist;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    private String[] filenames={"Booked Flat","Booked Shop","Total Inquiry","Total Payment"};
    private String[] ammount={"2","0","0","2517000"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        toplist=new toplist(row_list,this);
        recyclerView=(RecyclerView)findViewById(R.id.top_list);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(toplist);
        model offers2;
        for(int i=0;i<4;i++) {
            offers2 = new model(""+filenames[i],""+ammount[i]);
            row_list.add(offers2);
        }


        toplist.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.dashboard) {
            // Handle the camera action
        } else if (id == R.id.master) {

        } else if (id == R.id.transaction) {

        } else if (id == R.id.master_report) {

        } else if (id == R.id.transacation_report) {

        } else if (id == R.id.user_access) {

        }else if (id == R.id.backup) {

        }else if (id == R.id.exit) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

//call service

  /*  APIInterface apiService = APIClient.getClient().create(APIInterface.class);


    Call<AppVersionCheck> Versioncheck;
        if (FixLabels.Server.contains("sts.karnataka.gov.in")) {
                Versioncheck = apiService.versionCheckLive();

                } else {

                Versioncheck = apiService.versionCheck();


                }


                Versioncheck.enqueue(new Callback<AppVersionCheck>() {
@Override
public void onResponse(Call<AppVersionCheck> call, Response<AppVersionCheck> response) {

        if (response.isSuccessful()) {

        AppVersionCheck appVersionChecks = response.body();

        if (appVersionChecks != null) {
        Log.d("login1", appVersionChecks.returnMessage);
        Log.d("login2", appVersionChecks.mhtAppVersion.get(0).appVersion);


        app_version = appVersionChecks.mhtAppVersion.get(0).appVersion;
        if (versionName.equals(app_version)) {
        Toast.makeText(activity_splash_activity.this, "same", Toast.LENGTH_SHORT).show();
        } else {
//                        Toast.makeText(activity_splash_activity.this, "not same", Toast.LENGTH_SHORT).show();
        continueExecution1();
        }

        }

//                    for (AppVersionCheck version : appVersionChecks) {
//
//                        app_status = version.getApp_status();
//                        app_version = version.getApp_version();
//                        app_version_id = version.getApp_version_id();
//                    }
//
        }
        }

@Override
public void onFailure(Call<AppVersionCheck> call, Throwable t) {

        NoinetAlertDailog();
//                Toast.makeText(activity_splash_activity.this, FixLabels.Error, Toast.LENGTH_SHORT).show();
        }
        });
*/