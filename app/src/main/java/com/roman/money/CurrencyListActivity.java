package com.roman.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CurrencyListActivity extends AppCompatActivity {

    private List<Currency> currencies;
    private CurrencyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);
        currencies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            currencies.add(new Currency(CurrencyType.Euro,   R.drawable.euro_flag, 1.22));
            currencies.add(new Currency(CurrencyType.Dollar, R.drawable.usa_flag, 1.00));
            currencies.add(new Currency(CurrencyType.Pound,  R.drawable.british_flag, 1.41));
            currencies.add(new Currency(CurrencyType.Yen,    R.drawable.japan_flag, 0.0095));
        }
        adapter = new CurrencyAdapter(currencies);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}