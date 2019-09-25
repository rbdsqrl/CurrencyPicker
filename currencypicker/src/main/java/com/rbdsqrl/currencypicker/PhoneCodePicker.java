package com.rbdsqrl.currencypicker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.widget.EditText;

import com.hbb20.CCPCountry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PhoneCodePicker extends BasePicker {

    List<CCPCountry> masterCountries;
    List<CCPCountry> selectedCountries;
    private Double widthFactor;
    CodePickerListener codePickerListener;
    CodeRVAdapter codeRVAdapter;
    private Boolean isCancelable;
    private int windowFeature;
    private int gravity;

    public static PhoneCodePicker getInstance(){
        return new PhoneCodePicker();
    }

    public PhoneCodePicker(){
        widthFactor = 0.6;
        isCancelable = true;
        windowFeature = Window.FEATURE_NO_TITLE;
        gravity = Gravity.CENTER;
        masterCountries = CCPCountry.getLibraryMasterCountriesEnglish();
    }

    @Override
    protected void onDialogReady(Bundle savedInstanceState) {
        RecyclerView recyclerView = dialog.findViewById(R.id.recycler_phone_code);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false));
        codeRVAdapter = new CodeRVAdapter(new CodeRVAdapter.Listener() {
            @Override
            public void onPicked(CCPCountry ccpCountry) {
                codePickerListener.onPhoneCodePicked(ZCountry.build(ccpCountry, context));
            }
        }, context);
        selectedCountries = new ArrayList<>(masterCountries.size());
        selectedCountries.addAll(masterCountries);
        codeRVAdapter.setMasterCountries(selectedCountries);
        recyclerView.setAdapter(codeRVAdapter);
        EditText editText = dialog.findViewById(R.id.currency_code_picker_search);
        editText.addTextChangedListener(new TextWatcher() {

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

        editText.setText(context.getResources().getConfiguration().locale.getDisplayCountry(new Locale("en","US" )));
    }

    @SuppressLint("DefaultLocale")
    private void search(String text) {
        selectedCountries.clear();
        for (CCPCountry country : masterCountries) {
            Log.i("PhoneCodePicker search", country.getName()  +" " + text );
            if (country.getName().toLowerCase(Locale.ENGLISH).contains(text.toLowerCase())) {
                selectedCountries.add(country);
            }
        }
        codeRVAdapter.setMasterCountries(selectedCountries);
        codeRVAdapter.notifyDataSetChanged();
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_code_picker;
    }

    @Override
    public DialogParams getParams() {
        return new DialogParams(widthFactor,isCancelable, windowFeature, gravity);
    }

    public void setWidthFactor(Double widthFactor) {
        this.widthFactor = widthFactor;
    }

    public void setCodePickerListener(CodePickerListener codePickerListener) {
        this.codePickerListener = codePickerListener;
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
