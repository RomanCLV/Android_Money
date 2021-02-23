package com.roman.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrencyListActivity extends AppCompatActivity {

    private List<Currency> currencies;
    private CurrencyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);
        currencies = new ArrayList<>();
        loadRatesFromApi("USD");
        adapter = new CurrencyAdapter(currencies);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadRatesFromApi(String base) {
        final String TAG = "CurrencyListActivity";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url("https://api.exchangeratesapi.io/latest?base=" + base)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure:", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = Objects.requireNonNull(response.body()).string();
                try {
                    JSONObject json = new JSONObject(body);
                    JSONObject rates = json.getJSONObject("rates");
                    double EUR = 1 / rates.getDouble("EUR");
                    double USD = 1 / rates.getDouble("USD");
                    double GBP = 1 / rates.getDouble("GBP");
                    double JPY = 1 / rates.getDouble("JPY");
                    currencies.add(new Currency(CurrencyType.Euro,   R.drawable.euro_flag, EUR));
                    currencies.add(new Currency(CurrencyType.Dollar, R.drawable.usa_flag, USD));
                    currencies.add(new Currency(CurrencyType.Pound,  R.drawable.british_flag, GBP));
                    currencies.add(new Currency(CurrencyType.Yen,    R.drawable.japan_flag, JPY));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
                catch (JSONException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
        Log.i("CurrencyListActivity", "Started HTTP Request");
    }
}