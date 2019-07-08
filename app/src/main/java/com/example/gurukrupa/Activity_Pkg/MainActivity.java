package com.example.gurukrupa.Activity_Pkg;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurukrupa.Adapters.PagerAdapter2;
import com.example.gurukrupa.Api_Models.bookd;
import com.example.gurukrupa.Api_Models.flat_booking_data_model;
import com.example.gurukrupa.R;
import com.example.gurukrupa.RetrofitUrl.Api.APIInterface;
import com.example.gurukrupa.toplist;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<bookd> row_list = new ArrayList<>();
    private List<flat_booking_data_model> flat_list = new ArrayList<>();
    private com.example.gurukrupa.toplist toplist;
    private com.example.gurukrupa.Fragments.flat_data_adapter flat_data_adapter;
    private RecyclerView recyclerView, recyclerView2;
    private RecyclerView.LayoutManager layoutManager, layoutManager2;
    private ImageView expand_view, hide_view;
    private TextView flat_booking_chart, shop_booking_chart, show_empty_flat, show_empty_shop, show_book_flat, show_book_shop;
    private FrameLayout flate_frame, shop_frame;
    private String[] filenames;
    private String[] ammount;
    private int[] final_rate, stamp_amount, remain_amount, other_exp_amount;
    private TextView title_row, amount;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Flat"));
        tabLayout.addTab(tabLayout.newTab().setText("Shop"));
        tabLayout.addTab(tabLayout.newTab().setText("Reminder"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        PagerAdapter2 adapter = new PagerAdapter2(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//Flat data list


        //end
        title_row = findViewById(R.id.title_row);
        amount = findViewById(R.id.ammount);

        flat_booking_chart = findViewById(R.id.flat_booking_chart);
        shop_booking_chart = findViewById(R.id.shop_booking_chart);
        flate_frame = findViewById(R.id.flat_frame);
        shop_frame = findViewById(R.id.shop_frame);
        show_book_flat = findViewById(R.id.show_book_flat);
        show_book_shop = findViewById(R.id.show_book_shop);
        show_empty_flat = findViewById(R.id.show_empty_flat);
        show_empty_shop = findViewById(R.id.show_empty_shop);


        //Retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface apiInterface = retrofit.create(APIInterface.class);


        Call<List<bookd>> call = apiInterface.getUser();

        call.enqueue(new Callback<List<bookd>>() {
            @Override
            public void onResponse(Call<List<bookd>> call, Response<List<bookd>> response) {

                List<bookd> mod = response.body();

                filenames = new String[mod.size()];
                ammount = new String[mod.size()];
                for (int i = 0; i < mod.size(); i++) {

                    filenames[i] = mod.get(i).getValue();
                    ammount[i] = mod.get(i).getField();


                }
                addintolist(filenames, ammount);
            }

            private void addintolist(String[] filenames, String[] ammount) {

                toplist = new toplist(row_list, context);
                recyclerView = (RecyclerView) findViewById(R.id.top_list);
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(toplist);
                bookd offers2;

                for (int i = 0; i < 4; i++) {
                    offers2 = new bookd(i, "" + filenames[i], "" + ammount[i]);
                    row_list.add(offers2);
                }


                toplist.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<bookd>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

//        expand_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CreateExpandView_FlateBookingChart();
//            }
//        });
//        flat_booking_chart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CreateExpandView_FlateBookingChart();
//            }
//        });
//
//
//        hide_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CreateExpandView_ShopBookingChart();
//            }
//        });
//        shop_booking_chart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CreateExpandView_ShopBookingChart();
//            }
//        });
//
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

    private void CreateExpandView_FlateBookingChart() {

//                flate_frame.setVisibility(View.VISIBLE);

        if (flate_frame.getVisibility() == View.VISIBLE) {
            flate_frame.setVisibility(View.GONE);
            expand_view.setImageResource(R.drawable.ic_expand_more_black_24dp);

        } else if (flate_frame.getVisibility() == View.GONE) {
            flate_frame.setVisibility(View.VISIBLE);
            expand_view.setImageResource(R.drawable.ic_expand_less_black_24dp);

        }
    }


    private void CreateExpandView_ShopBookingChart() {

//                flate_frame.setVisibility(View.VISIBLE);

        if (shop_frame.getVisibility() == View.VISIBLE) {
            shop_frame.setVisibility(View.GONE);
            hide_view.setImageResource(R.drawable.ic_expand_more_black_24dp);

        } else if (shop_frame.getVisibility() == View.GONE) {
            shop_frame.setVisibility(View.VISIBLE);
            hide_view.setImageResource(R.drawable.ic_expand_less_black_24dp);
        }
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
}

   // @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.dashboard) {
//            // Handle the camera action
//        } else if (id == R.id.master) {
//
//        } else if (id == R.id.transaction) {
//
//        } else if (id == R.id.master_report) {
//
//        } else if (id == R.id.transacation_report) {
//
//        } else if (id == R.id.user_access) {
//
//        } else if (id == R.id.backup) {
//
//        } else if (id == R.id.exit) {
//
//        }
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//}

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