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
                onSelectCurrency(CurrencyType.Euro, R.drawable.euro_flag);
            }
        });
        findViewById(R.id.poundButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectCurrency(CurrencyType.Pound, R.drawable.british_flag);
            }
        });
        findViewById(R.id.dollarButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectCurrency(CurrencyType.Dollar, R.drawable.usa_flag);
            }
        });
        findViewById(R.id.yenButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectCurrency(CurrencyType.Yen, R.drawable.japan_flag);
            }
        });
    }

    private void onSelectCurrency(CurrencyType currency, int flagId) {
        Intent intent = new Intent(ChooseCurrencyActivity.this, MainActivity.class);
        intent.putExtra("currency", new Currency(currency, flagId));
        startActivity(intent);
    }
}