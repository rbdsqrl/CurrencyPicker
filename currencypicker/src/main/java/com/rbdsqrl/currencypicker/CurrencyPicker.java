package com.rbdsqrl.currencypicker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Window;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CurrencyPicker extends BasePicker {

    private CurrencyPickerListener currencyPickerListener;
    private List<CurrencyDetail> currencyDetailList = new ArrayList<>();
    private CurrencyRVAdapter currencyRVAdapter;
    private List<CurrencyDetail> selectedCurrenciesList = new ArrayList<>();
    private double widthFactor;
    private Boolean isCancelable;
    private int windowFeature;
    private int gravity;

    public void setListener(CurrencyPickerListener currencyPickerListener) {
        this.currencyPickerListener = currencyPickerListener;
    }

    public static CurrencyPicker newInstance() {
        return new CurrencyPicker();
    }

    public CurrencyPicker() {
        widthFactor = 0.6;
        isCancelable = true;
        windowFeature = Window.FEATURE_NO_TITLE;
        gravity = Gravity.CENTER;
        setCurrenciesList(CurrencyDetail.getAllCurrencies());
    }

    public void setCurrenciesList(List<CurrencyDetail> newCurrencies) {
        this.currencyDetailList.clear();
        this.currencyDetailList.addAll(newCurrencies);
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

    public void setWidthFactor(double widthFactor) {
        this.widthFactor = widthFactor;
    }

    @Override
    protected void onDialogReady(Bundle savedInstanceState) {
        selectedCurrenciesList = new ArrayList<>(currencyDetailList.size());
        selectedCurrenciesList.addAll(currencyDetailList);

        EditText etSearch = dialog.findViewById(R.id.currency_code_picker_search);
        RecyclerView rvCurrency = dialog.findViewById(R.id.recyclerView_currency);
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

    }

    @Override
    public int getLayout() {
        return R.layout.dialog_currency_picker;
    }

    @Override
    public DialogParams getParams() {
        return new DialogParams(widthFactor,isCancelable, windowFeature, gravity);
    }

    public void setDialogCancelable(Boolean cancelable) {
        isCancelable = cancelable;
    }

    public void setDialogWindowFeature(int windowFeature) {
        this.windowFeature = windowFeature;
    }

    public void setDialogGravity(int gravity) {
        this.gravity = gravity;
    }
}
