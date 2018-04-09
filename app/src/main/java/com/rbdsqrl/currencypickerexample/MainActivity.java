package com.rbdsqrl.currencypickerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rbdsqrl.currencypicker.CurrencyPicker;
import com.rbdsqrl.currencypicker.CurrencyPickerListener;

public class MainActivity extends AppCompatActivity {
CurrencyPicker currencyPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showPicker();
    }

    private void showPicker() {
        currencyPicker =  CurrencyPicker.newInstance();
        currencyPicker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol) {
                Log.i("onPicked", name + code +  symbol);

            }
        });
        currencyPicker.show(getSupportFragmentManager(),"picker");
    }
}
