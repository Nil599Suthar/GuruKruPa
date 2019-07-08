package com.example.gurukrupa.Fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.gurukrupa.Api_Models.bookd;
import com.example.gurukrupa.Api_Models.flat_booking_data_model;
import com.example.gurukrupa.R;
import com.example.gurukrupa.RetrofitUrl.Api.APIInterface;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class Shop_Fragment extends Fragment {

    private TextView shop_booking_chart,show_empty_shop, show_book_shop;
    private FrameLayout  shop_frame;
    private View view;
    private com.example.gurukrupa.Fragments.flat_data_adapter flat_data_adapter;
    private List<flat_booking_data_model> shop_list = new ArrayList<>();
    private RecyclerView recyclerView2;
    private RecyclerView.LayoutManager layoutManager2;
    private Context context;
    private String[] details={"Booked","Received_Payment","Other Payment"};;


    public Shop_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shop_, container, false);

        shop_booking_chart = view.findViewById(R.id.shop_booking_chart);
        shop_frame = view.findViewById(R.id.shop_frame);
        show_empty_shop = view.findViewById(R.id.show_empty_shop);
        show_book_shop = view.findViewById(R.id.show_book_shop);

        GetShopPieChart();

        flat_data_adapter = new flat_data_adapter(shop_list, context);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.shop_data_list);
        layoutManager2 = new LinearLayoutManager(context);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(flat_data_adapter);
        flat_booking_data_model flat_booking_data_model;


        for (int i = 0; i < 2; i++) {
            flat_booking_data_model = new flat_booking_data_model(i,i,""+details[i],0,0,0,0);
            shop_list.add(flat_booking_data_model);
        }


        flat_data_adapter.notifyDataSetChanged();


        return view;
    }

    private void GetShopPieChart() {



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
                int booked_shop = Integer.parseInt(mod.get(1).getValue());

                int total_shop = 60;
                int empty_shop = total_shop - booked_shop;

                show_empty_shop.setText("Available Shop :- "+empty_shop);
                show_book_shop.setText("Booked Shop :- "+booked_shop);

                AnimatedPieView mAnimatedPieView = view.findViewById(R.id.animatedPieView1);
                AnimatedPieViewConfig config = new AnimatedPieViewConfig();
                config.startAngle(-90)// Starting angle offset
                        .addData(new SimplePieInfo(empty_shop, Color.parseColor("#BE000000"), "Shop Available")).drawText(true).strokeMode(false)//Data (bean that implements the IPieInfo interface)
                        .addData(new SimplePieInfo(booked_shop, Color.parseColor("#FF0000"), "Shop Booked ")).drawText(true).strokeMode(false)

                        .duration(2000).textSize(15);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
                mAnimatedPieView.applyConfig(config);
                mAnimatedPieView.start();
            }

            @Override
            public void onFailure(Call<List<bookd>> call, Throwable t) {

            }
        });


    }



}
