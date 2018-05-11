package com.rbdsqrl.currencypickerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.rbdsqrl.currencypicker.CodePickerListener;
import com.rbdsqrl.currencypicker.PhoneCodePicker;
import com.rbdsqrl.currencypicker.PickerView;
import com.rbdsqrl.currencypicker.CurrencyPicker;
import com.rbdsqrl.currencypicker.CurrencyPickerListener;
import com.rbdsqrl.currencypicker.ZCountry;

public class MainActivity extends AppCompatActivity implements CurrencyPickerListener {

    ImageView ivFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivFlag = findViewById(R.id.iv_flag);
    }

    private void loadCurrencyFragment() {
        //fragment class which can be opened in a container
        PickerView picker =  PickerView.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, picker,"fragment-currency").commit();
    }

    private void showPicker() {
        //pop up dialog
        /*final CurrencyPicker currencyPicker = CurrencyPicker.newInstance();
        //adjust the width of the layout, width = widthFactor * maxDialogWidth(window width)
        //currencyPicker.setWidthFactor(0.8);
        currencyPicker.setListener(new CurrencyPickerListener() {
            @Override
            public void onSelectCurrency(String name, String code, String symbol) {
                Toast.makeText(getBaseContext(),name + " " + code + " " + symbol,Toast.LENGTH_SHORT).show();
                currencyPicker.dismiss();
            }
        });
        currencyPicker.show(getSupportFragmentManager(), "picker");*/

        final PhoneCodePicker phoneCodePicker = PhoneCodePicker.getInstance();
        phoneCodePicker.setWidthFactor(0.6);
        phoneCodePicker.setCodePickerListener(new CodePickerListener() {
            @Override
            public void onPhoneCodePicked(ZCountry country) {
                Toast.makeText(getBaseContext(),country.toString(), Toast.LENGTH_SHORT).show();
                ivFlag.setImageBitmap(country.getFlag());
                phoneCodePicker.closeDialog();
            }

        });
        phoneCodePicker.show(getSupportFragmentManager(),"code-picker");
    }


    //Listener for PickerView, must be overridden by implementing CurrencyPickerListener
    @Override
    public void onSelectCurrency(String name, String code, String symbol) {
        Toast.makeText(getBaseContext(),name + " " + code + " " + symbol,Toast.LENGTH_SHORT).show();
    }

    public void openCurrency(View v){
        switch (v.getId()){
            case R.id.btn_dialog:
                showPicker();
                break;
            case R.id.btn_fragment:
                loadCurrencyFragment();
                break;
        }
    }
}
