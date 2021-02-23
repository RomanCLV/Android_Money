package com.roman.money;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Intent srcIntent = getIntent();

        User user = srcIntent.getParcelableExtra("user");
        String versionName = srcIntent.getStringExtra("versionName");
        int versionCode = srcIntent.getIntExtra("versionCode", 1);

        TextView authorNameTextView = findViewById(R.id.authorNameTextView);
        TextView authorEmailTextView = findViewById(R.id.authorEmailTextView);
        TextView versionTextView = findViewById(R.id.versionTextView);

        authorNameTextView.setText(user.name);
        authorEmailTextView.setText(user.email);
        versionTextView.setText(versionName + " ~ " + versionCode);
    }
}