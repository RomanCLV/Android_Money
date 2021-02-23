package com.roman.money;

import  androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Currency currency = getIntent().getParcelableExtra("currency");
        TextView currencyTextView = findViewById(R.id.currencyTextView);
        currencyTextView.setText(currency.getSymbol());
        ImageView imageView = findViewById(R.id.currencyImageView);
        imageView.setImageResource(currency.getFlagId());
        findViewById(R.id.converterButton).setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ShowToast", "SetTextI18n"})
            @Override
            public void onClick(View v) {
                EditText convertEditText = findViewById(R.id.convertEditText);
                TextView euroConvertedTextView = findViewById(R.id.euroConvertedTextView);
                TextView poundConvertedTextView = findViewById(R.id.poundConvertedTextView);
                TextView dollarConvertedTextView = findViewById(R.id.dollarConvertedTextView);
                TextView yenConvertedTextView = findViewById(R.id.yenConvertedTextView);

                String amountStr = convertEditText.getText().toString();
                double amount = 0.0;
                if (amountStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "An amount is required!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        //currency.setValue(Double.parseDouble(amountStr));
                        amount = Double.parseDouble(amountStr);
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Number value expected!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                euroConvertedTextView.setText(Double.toString(round(currency.convertValue(amount, CurrencyType.Euro), 2)));
                poundConvertedTextView.setText(Double.toString(round(currency.convertValue(amount, CurrencyType.Pound), 2)));
                dollarConvertedTextView.setText(Double.toString(round(currency.convertValue(amount, CurrencyType.Dollar), 2)));
                yenConvertedTextView.setText(Double.toString(round(currency.convertValue(amount, CurrencyType.Yen), 2)));
            }
        });
        findViewById(R.id.aboutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                User user = new User("Roman Clavier", "roman.clavier.2001@gmail.com");
                intent.putExtra("user", user);
                intent.putExtra("versionName", "v1.1.2");
                intent.putExtra("versionCode", 2);
                startActivity(intent);
            }
        });
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value *= factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}