package com.example.gurukrupa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class toplist extends RecyclerView.Adapter<toplist.MyViewHolder> {
    public List<model> offersList;
    Context context;

    public toplist(List<model> offersList, Context context) {
        this.offersList = offersList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.datarow,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

        myViewHolder.filename.setText(offersList.get(i).getTitle());
        myViewHolder.ammount.setText(offersList.get(i).getAmmount());

    }


    @Override
    public int getItemCount() {
        return offersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView filename,ammount;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);

            filename=(TextView)itemView.findViewById(R.id.title_row);
            ammount=(TextView)itemView.findViewById(R.id.ammount);

            cardView=(CardView)itemView.findViewById(R.id.data_row);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context, ""+filename.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

