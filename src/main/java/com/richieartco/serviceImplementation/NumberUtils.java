package com.richieartco.serviceImplementation;

import java.text.NumberFormat;
import java.util.Locale;

public final class NumberUtils {

    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance(Locale.US);

    private NumberUtils() {
    }

    public static String formatNumberWithThousandsSeparator(Number number) {
        if (number == null) {
            return "";
        }
        return NUMBER_FORMAT.format(number);
    }
}
