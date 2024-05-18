package zad1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class TestMain {
    public static void main(String[] args) {
        Locale locale = new Locale("en_GB".split("_")[0]);
        System.out.println(locale.getCountry());
        System.out.println(locale.getLanguage());
    }
}
