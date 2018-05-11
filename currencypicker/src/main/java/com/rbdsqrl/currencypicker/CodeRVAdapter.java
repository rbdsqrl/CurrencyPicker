package com.rbdsqrl.currencypicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbb20.CCPCountry;

import java.util.ArrayList;
import java.util.List;

class CodeRVAdapter extends RecyclerView.Adapter   {

    private Listener listener;
    private List<CCPCountry> masterCountries;
    private Context context;

    private CodeRVAdapter(Listener listener, List<CCPCountry> masterCountries, Context context) {
        this.listener = listener;
        this.masterCountries = masterCountries;
        this.context = context;
    }

    public CodeRVAdapter(Listener listener, Context context) {
        this(listener,new ArrayList<CCPCountry>(),context);
    }

    interface Listener{
        void onPicked(CCPCountry ccpCountry);
    }

    public void setMasterCountries(List<CCPCountry> masterCountries) {
        this.masterCountries = masterCountries;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_code, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CCPCountry country = masterCountries.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bindData(country);
    }

    @Override
    public int getItemCount() {
        Log.i("PhoneCodePicker", masterCountries.size() + " country size");
        return masterCountries.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView ivFlag;
        TextView tvName;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            ivFlag = view.findViewById(R.id.iv_flag);
            tvName = view.findViewById(R.id.tv_name);
        }

        void bindData(final CCPCountry country) {
            if (country == null)
                return;
            tvName.setText(country.getName() + "  " + country.getPhoneCode());
            ivFlag.setImageResource(country.getFlagID());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPicked(country);
                }
            });
        }
    }
}
