package io.mcvalorant.utils;

import java.text.DecimalFormat;

public class StringFormatUtils {

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$" + length + "s", string);
    }

    public static String decimalFormatNumber(int format) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(format);
    }
}
