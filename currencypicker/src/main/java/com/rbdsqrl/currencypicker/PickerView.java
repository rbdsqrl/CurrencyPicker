package com.rbdsqrl.currencypicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PickerView extends Fragment {
    private EditText etSearch;
    private RecyclerView rvCurrency;
    private CurrencyPickerListener currencyPickerListener;
    private List<CurrencyDetail> currencyDetailList = new ArrayList<>();
    private CurrencyRVAdapter currencyRVAdapter;
    private List<CurrencyDetail> selectedCurrenciesList = new ArrayList<>();
    private Context context;

    public PickerView() {
        setCurrenciesList(CurrencyDetail.getAllCurrencies());
    }

    public static PickerView newInstance() {
        PickerView fragment = new PickerView();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("onCreateView","called");
        View view = inflater.inflate(R.layout.fragment_currency, null);
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

        etSearch.setText(context.getResources().getConfiguration().locale.getDisplayCountry(new Locale("en","US" )));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CurrencyPickerListener) {
            currencyPickerListener = (CurrencyPickerListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        currencyPickerListener = null;
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
}
