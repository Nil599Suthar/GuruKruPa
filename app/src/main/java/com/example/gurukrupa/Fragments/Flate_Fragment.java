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
import com.example.gurukrupa.RetrofitUrl.Api.APIInterface2;
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
public class Flate_Fragment extends Fragment {

    private TextView flat_booking_chart,show_empty_flat, show_book_flat;
    private FrameLayout flate_frame;

    private List<flat_booking_data_model> flat_list = new ArrayList<>();
    private RecyclerView recyclerView2;
    private RecyclerView.LayoutManager layoutManager2;
    private View view;
    private int[] final_rate,stamp_amount,remain_amount,other_exp_amount;
    private Context context;
    private String[] details={"Booked","Received_Payment","Other Payment"};
    private com.example.gurukrupa.Fragments.flat_data_adapter flat_data_adapter;
    public Flate_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_flate_, container, false);


        flat_booking_chart = view.findViewById(R.id.flat_booking_chart);
        flate_frame = view.findViewById(R.id.flat_frame);
        show_book_flat = view.findViewById(R.id.show_book_flat);
        show_empty_flat = view.findViewById(R.id.show_empty_flat);

        GetFlatePieChart(details);
        GetFlatBookingDetails();

        return view;
    }

    private void GetFlatBookingDetails() {
        Retrofit retrofit2=new Retrofit.Builder()
                .baseUrl(APIInterface2.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface2 apiInterface2=retrofit2.create(APIInterface2.class);
        Call<List<flat_booking_data_model>> call2=apiInterface2.getFdata();

        call2.enqueue(new Callback<List<flat_booking_data_model>>() {
            @Override
            public void onResponse(Call<List<flat_booking_data_model>> call, Response<List<flat_booking_data_model>> response) {
                List<flat_booking_data_model> mod=response.body();

                final_rate=new int[mod.size()];
                stamp_amount=new int[mod.size()];
                remain_amount=new int[mod.size()];
                other_exp_amount=new int[mod.size()];
                for(int i=0;i<mod.size();i++){

                    final_rate[i]=mod.get(i).getFinal_rate();
                    stamp_amount[i]=mod.get(i).getStamp_amount();
                    remain_amount[i]=mod.get(i).getRemain_amount();
                    other_exp_amount[i]=mod.get(i).getOther_exp_amount();


                }
                addintolist2(final_rate,stamp_amount,remain_amount,other_exp_amount);
            }

            private void addintolist2(int[] final_rate, int[] stamp_amount, int[] remain_amount, int[] other_exp_amount) {
                flat_data_adapter = new flat_data_adapter(flat_list, context);
                recyclerView2 = (RecyclerView) view.findViewById(R.id.flat_data_list);
                layoutManager2 = new LinearLayoutManager(context);
                recyclerView2.setLayoutManager(layoutManager2);
                recyclerView2.setItemAnimator(new DefaultItemAnimator());
                recyclerView2.setAdapter(flat_data_adapter);
                flat_booking_data_model flat_booking_data_model;


                for (int i = 0; i < 2; i++) {
                    flat_booking_data_model = new flat_booking_data_model(i,i,""+details[i],final_rate[i],stamp_amount[i],remain_amount[i],other_exp_amount[i]);
                    flat_list.add(flat_booking_data_model);
                }


                flat_data_adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<flat_booking_data_model>> call, Throwable t) {

            }
        });
    }


    private void GetFlatePieChart(final String[] details) {


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
                int booked_flat = Integer.parseInt(mod.get(0).getValue());
                details[0]="Booked "+booked_flat;

                int total_flat= 60;
                int empty_flat = total_flat - booked_flat;

                show_empty_flat.setText("Available Flat :- "+empty_flat);

                show_book_flat.setText("Booked Flat :- "+booked_flat);

                AnimatedPieView mAnimatedPieView = view.findViewById(R.id.animatedPieView);
                AnimatedPieViewConfig config = new AnimatedPieViewConfig();
                config.startAngle(-90)// Starting angle offset
                        .addData(new SimplePieInfo(empty_flat, Color.parseColor("#BE000000"), "Flat Available")).drawText(true).strokeMode(false)//Data (bean that implements the IPieInfo interface)
                        .addData(new SimplePieInfo(booked_flat, Color.parseColor("#FF0000"), "Flat Booked ")).drawText(true).strokeMode(false)

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
