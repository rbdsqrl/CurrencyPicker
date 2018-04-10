package com.rbdsqrl.currencypickerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rbdsqrl.currencypicker.PickerView;
import com.rbdsqrl.currencypicker.CurrencyPicker;
import com.rbdsqrl.currencypicker.CurrencyPickerListener;

public class MainActivity extends AppCompatActivity implements CurrencyPickerListener {
    CurrencyPicker currencyPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //To show a picker dialog
        showPicker();

        //To inflate a picker fragment in a frame layout
        loadCurrencyFragment();
    }

    private void loadCurrencyFragment() {
        //fragment class which can be opened in a container
        PickerView picker =  PickerView.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, picker,"fragment-currency").commit();
    }

    private void showPicker() {
        //pop up dialog
        currencyPicker = CurrencyPicker.newInstance();
        //adjust the width of the layout, width = widthFactor * maxDialogWidth(window width)
        //currencyPicker.setWidthFactor(0.8);
        currencyPicker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol) {
                Toast.makeText(getBaseContext(),name + " " + code + " " + symbol,Toast.LENGTH_SHORT).show();
                currencyPicker.dismiss();
            }
        });
        currencyPicker.show(getSupportFragmentManager(), "picker");
    }


    //Listener for PickerView, must be overridden by implementing CurrencyPickerListener
    @Override
    public void onSelectCurrency(String name, String code, String symbol) {
        Toast.makeText(getBaseContext(),name + " " + code + " " + symbol,Toast.LENGTH_SHORT).show();
    }
}
