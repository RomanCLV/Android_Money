package com.roman.money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseCurrencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_currency);

        findViewById(R.id.euroButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectCurrency(Currency.Euro);
            }
        });
        findViewById(R.id.poundButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectCurrency(Currency.Pound);
            }
        });
        findViewById(R.id.dollarButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectCurrency(Currency.Dollar);
            }
        });
        findViewById(R.id.yenButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectCurrency(Currency.Yen);
            }
        });
    }

    private void onSelectCurrency(Currency currency) {
        Intent intent = new Intent(ChooseCurrencyActivity.this, MainActivity.class);
        intent.putExtra("currencyOrdinal", currency.ordinal());
        startActivity(intent);
    }
}