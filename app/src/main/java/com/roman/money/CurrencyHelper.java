package com.roman.money;

public class CurrencyHelper {

    public static String getSymbol(Currency currency) {
        switch (currency) {
            case Euro: return "€";
            case Dollar: return "$";
            case Pound: return "£";
            case Yen: return "¥";
            default: return "?";
        }
    }

    private static double getCurrencyToDollarFactor(Currency currency) {
        switch (currency) {
            case Euro: return 1.21;
            case Pound: return 1.40;
            case Yen: return 0.0095;
            case Dollar:
            default: return 1.00;
        }
    }

    public static double convert(double value, Currency currency, Currency toCurrency) {
        return value * getCurrencyToDollarFactor(currency) / getCurrencyToDollarFactor(toCurrency);
    }
}
