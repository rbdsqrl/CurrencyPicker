package com.rbdsqrl.currencypicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public void setListener(CurrencyPickerListener currencyPickerListener){
        this.currencyPickerListener = currencyPickerListener;
    }

    public static CurrencyPicker newInstance(String dialogTitle) {
        CurrencyPicker picker = new CurrencyPicker();
        Bundle bundle = new Bundle();
        bundle.putString("dialogTitle", dialogTitle);
        picker.setArguments(bundle);
        return picker;
    }

    public CurrencyPicker() {
        setCurrenciesList(CurrencyDetail.getAllCurrencies());
    }

    public void setCurrenciesList(List<CurrencyDetail> newCurrencies) {
        this.currencyDetailList.clear();
        this.currencyDetailList.addAll(newCurrencies);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_currency_picker, null);
        Bundle args = getArguments();
        if (args != null && getDialog() != null) {
            String dialogTitle = args.getString("dialogTitle");
            getDialog().setTitle(dialogTitle);
            int width = getResources().getDimensionPixelSize(R.dimen.cp_dialog_width);
            int height = getResources().getDimensionPixelSize(R.dimen.cp_dialog_height);
            getDialog().getWindow().setLayout(width, height);
        }

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


}
