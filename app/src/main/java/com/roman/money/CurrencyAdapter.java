package com.roman.money;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

public class CurrencyAdapter extends Adapter<CurrencyAdapter.ViewHolder> implements View.OnClickListener {

    private final List<Currency> currencies;

    public CurrencyAdapter(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Currency currency = currencies.get(position);
        holder.flag.setImageResource(currency.getFlagId());
        holder.name.setText(currency.getName());
        holder.symbol.setText(currency.getSymbol());
        holder.symbol2.setText(currency.getSymbol());
        holder.rate.setText(Double.toString(MainActivity.round(currency.getValue(), 4)));
        holder.itemView.setTag(currency);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rootItem:
                Context context = v.getContext();
                Currency currency = (Currency) v.getTag();
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("currency", currency);
                context.startActivity(intent);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView flag;
        final TextView name;
        final TextView symbol;
        final TextView symbol2;
        final TextView rate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.flagImageView);
            name = itemView.findViewById(R.id.nameTextView);
            symbol = itemView.findViewById(R.id.symboleTextView);
            symbol2 = itemView.findViewById(R.id.symbole2TextView);
            rate = itemView.findViewById(R.id.rateTextView);
        }
    }
}
