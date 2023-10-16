package io.mcvalorant.utils;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class FormatUtils {

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$" + length + "s", string);
    }

    public static String decimalFormatNumber(int format) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(format);
    }

    public static Boolean isNumeric(String input) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }
}
