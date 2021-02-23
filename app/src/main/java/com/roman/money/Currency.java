package com.roman.money;

import android.os.Parcel;
import android.os.Parcelable;

public class Currency implements Parcelable {

    private final CurrencyType currencyType;
    private final int currencyOrdinal;
    private double value;
    private final int flagId;

    public Currency(CurrencyType currency, int flagId) {
        this.currencyType = currency;
        this.currencyOrdinal = this.currencyType.ordinal();
        this.flagId = flagId;
    }

    public Currency(CurrencyType currency, int flagId, double value) {
        this.currencyType = currency;
        this.currencyOrdinal = this.currencyType.ordinal();
        this.flagId = flagId;
        this.value = value;
    }

    public CurrencyType getCurrencyType() {
        return this.currencyType;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getFlagId() {
        return flagId;
    }

    public String getName() {
        switch (this.currencyType) {
            case Euro: return "Euro";
            case Dollar: return "Dollar";
            case Pound: return "Pound";
            case Yen: return "Yen";
            default: return "?";
        }
    }

    public String getSymbol() {
        switch (this.currencyType) {
            case Euro: return "€";
            case Dollar: return "$";
            case Pound: return "£";
            case Yen: return "¥";
            default: return "?";
        }
    }

    private static double getCurrencyToDollarFactor(CurrencyType currency) {
        switch (currency) {
            case Euro: return 1.21;
            case Pound: return 1.40;
            case Yen: return 0.0095;
            case Dollar:
            default: return 1.00;
        }
    }

    public double convertValue(CurrencyType toCurrency) {
        return this.value * getCurrencyToDollarFactor(this.currencyType) / getCurrencyToDollarFactor(toCurrency);
    }

    protected Currency(Parcel in) {
        value = in.readDouble();
        flagId = in.readInt();
        currencyOrdinal = in.readInt();
        currencyType = CurrencyType.values()[currencyOrdinal];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(value);
        dest.writeInt(flagId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel in) {
            return new Currency(in);
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };
}
