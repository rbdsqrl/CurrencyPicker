package com.rbdsqrl.currencypicker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CurrencyPicker extends DialogFragment {
    private EditText etSearch;
    private RecyclerView rvCurrency;
    private CurrencyPickerListener currencyPickerListener;
    private List<CurrencyDetail> currencyDetailList = new ArrayList<>();
    private CurrencyRVAdapter currencyRVAdapter;
    private List<CurrencyDetail> selectedCurrenciesList = new ArrayList<>();
    private Context context;
    private double widthFactor;

    public void setListener(CurrencyPickerListener currencyPickerListener){
        this.currencyPickerListener = currencyPickerListener;
    }

    public static CurrencyPicker newInstance() {
        CurrencyPicker picker = new CurrencyPicker();
        return picker;
    }

    public CurrencyPicker() {
        widthFactor = 0.6;
        setCurrenciesList(CurrencyDetail.getAllCurrencies());
    }

    public void setCurrenciesList(List<CurrencyDetail> newCurrencies) {
        this.currencyDetailList.clear();
        this.currencyDetailList.addAll(newCurrencies);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getDialog()!=null){
            Window window = getDialog().getWindow();
            window.requestFeature(Window.FEATURE_NO_TITLE);
        }

        View view = inflater.inflate(R.layout.dialog_currency_picker, null);
        context =  getContext();
        selectedCurrenciesList = new ArrayList<>(currencyDetailList.size());
        selectedCurrenciesList.addAll(currencyDetailList);

        etSearch =  view.findViewById(R.id.currency_code_picker_search);
        rvCurrency =  view.findViewById(R.id.recyclerView_currency);
        rvCurrency.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
        currencyRVAdapter = new CurrencyRVAdapter(context, new CurrencyRVAdapter.Listener() {
            @Override
            public void onCurrencySelected(CurrencyDetail currencyDetail) {
                currencyPickerListener.onSelectCurrency(currencyDetail.getName(), currencyDetail.getCode(), currencyDetail.getSymbol());
            }
        });
        currencyRVAdapter.setCurrencyDetailList(selectedCurrenciesList);
        rvCurrency.setAdapter(currencyRVAdapter);


        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString());
            }
        });

        return view;
    }

    @Override
    public void dismiss() {
        if (getDialog() != null) {
            super.dismiss();
        } else {
            getFragmentManager().popBackStack();
        }
    }


    @SuppressLint("DefaultLocale")
    private void search(String text) {
        selectedCurrenciesList.clear();
        for (CurrencyDetail currency : currencyDetailList) {
            if (currency.getName().toLowerCase(Locale.ENGLISH).contains(text.toLowerCase())) {
                selectedCurrenciesList.add(currency);
            }
        }
        currencyRVAdapter.notifyDataSetChanged();
    }

    public void setWidthFactor(double widthFactor){
        this.widthFactor = widthFactor;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            Point size = new Point();
            Display display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);
            int width = size.x;
            window.setLayout((int) (width * widthFactor), WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
        }
    }
}
