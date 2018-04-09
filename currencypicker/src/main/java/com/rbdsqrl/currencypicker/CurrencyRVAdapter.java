package com.rbdsqrl.currencypicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRVAdapter extends RecyclerView.Adapter {
    Listener listener;
    List<CurrencyDetail> currencyDetailList = new ArrayList<>();
    Context context;

    interface Listener{
        void onCurrencySelected(CurrencyDetail currencyDetail);
    }

    public CurrencyRVAdapter(Context context, List<CurrencyDetail> currencyDetailList, Listener listener) {
        this.context = context;
        this.currencyDetailList = currencyDetailList;
        this.listener = listener;
    }

    public CurrencyRVAdapter(Context context, Listener listener) {
        this(context, new ArrayList<CurrencyDetail>(), listener);
    }

    public void setCurrencyDetailList(List<CurrencyDetail> currencyDetailList){
        this.currencyDetailList = currencyDetailList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_currency, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        CurrencyDetail currencyDetail = null;
        if (!currencyDetailList.isEmpty())
            currencyDetail = currencyDetailList.get(position);
        viewHolder.bindData(currencyDetail);
    }

    @Override
    public int getItemCount() {
        return currencyDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFlag;
        TextView tvName;
        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ivFlag = view.findViewById(R.id.iv_flag);
            tvName = view.findViewById(R.id.tv_name);
        }

        public void bindData(final CurrencyDetail currencyDetail) {
            if (currencyDetail == null)
                return;
            tvName.setText(currencyDetail.getName());
            currencyDetail.loadFlagByCode(context);
            ivFlag.setImageResource(currencyDetail.getFlag());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCurrencySelected(currencyDetail);
                }
            });
        }
    }
}
