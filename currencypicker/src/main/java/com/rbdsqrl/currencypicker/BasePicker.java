package com.rbdsqrl.currencypicker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BasePicker extends DialogFragment {
    Context context;
    Activity activity;
    private DialogParams dialogParams;
    View dialog;

    public BasePicker() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogParams = getParams();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            AppView.setWindowProps(getDialog(),dialogParams.isCancelable,dialogParams.windowFeature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflater.inflate(getLayout(), null);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            AppView.setWindowDimens(getDialog(),dialogParams.widthFactor,dialogParams.gravity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dialog = getView();
        context = this.getContext();
        activity = this.getActivity();
        onDialogReady(savedInstanceState);
    }

    protected abstract void onDialogReady(Bundle savedInstanceState);

    public abstract int getLayout();

    public abstract DialogParams getParams();

    public void closeDialog() {
        dismiss();
    }

    class DialogParams{
        private Double widthFactor;
        private Boolean isCancelable;
        private int windowFeature;
        private int gravity;

        DialogParams(Double widthFactor, Boolean isCancelable, int windowFeature, int gravity) {
            this.widthFactor = widthFactor;
            this.isCancelable = isCancelable;
            this.windowFeature = windowFeature;
            this.gravity = gravity;
        }
    }
}

