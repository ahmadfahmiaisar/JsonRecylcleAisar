package com.bismillah.jsonrecylcleaisar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

//menginduk/ warisi sifat dari recycleview
public class AdapterData extends RecyclerView.Adapter<AdapterData.DataViewHolder> {
   private Context context;
   private ArrayList<MixData>itemData;

   public AdapterData (Context context, ArrayList<MixData> itemData){
       this.context = context;
       this.itemData = itemData;
   }


    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater
               .from(parent.getContext())
               .inflate(R.layout.list_item, parent, false);
       return new DataViewHolder(view);
    }


    public class DataViewHolder extends RecyclerView.ViewHolder {
       TextView valuesatu, valuedua, valuetiga, valueempat;
        public DataViewHolder(View itemView) {
            super(itemView);
            valuesatu = itemView.findViewById(R.id.valuesatu);
            valuedua = itemView.findViewById(R.id.valuedua);
            valuetiga = itemView.findViewById(R.id.valuetiga);
            valueempat = itemView.findViewById(R.id.valueempat);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData.DataViewHolder holder, int position) {
        holder.valuesatu.setText(itemData.get(position).getValuesatu());
        holder.valuedua.setText(itemData.get(position).getValuedua());
        holder.valuetiga.setText(itemData.get(position).getValuetiga());
        holder.valueempat.setText(itemData.get(position).getValueempat());
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }
}
