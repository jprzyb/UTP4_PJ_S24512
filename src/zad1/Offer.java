package zad1;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class Offer {
    private Locale code;
    private final String country;
    private Date startDate;
    private String tStartDate;
    private Date endDate;
    private String tEndDate;
    private final String location;
    private double price;
    private String tPrice;
    private final String currency;

    private Locale destLocale;
    private Properties dictionary;

    public Offer(Locale countryCode, String countryName, Date dateFrom, Date dateTo, String location, double price, String currency) {
//        System.out.println(countryCode);
        this.code = countryCode;
        this.country = countryName;
        this.startDate = dateFrom;
        this.endDate = dateTo;
        this.location = location;
        this.price = price;
        this.currency = currency;
        try  {
            InputStream input = Files.newInputStream(Paths.get("dic.properties"));
            dictionary = new Properties();
            dictionary.load(input);
        } catch (IOException e) {
            System.err.println("Couldn't find dictionary! " + e);
        }
    }

    private Offer(Locale code, String country, String startDate, String endDate, String location, String price, String currency) {
        this.code = code;
        this.country = country;
        this.tStartDate = startDate;
        this.tEndDate = endDate;
        this.location = location;
        this.tPrice = price;
        this.currency = currency;
    }

    public Offer translate(String dest, String dateFormat){
        destLocale = Locale.forLanguageTag(dest.split("_")[0]);
        NumberFormat numberFormat = NumberFormat.getInstance(destLocale);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String loc = location;
        return new Offer(
                code,
                translateCountry(),
                simpleDateFormat.format(startDate),
                simpleDateFormat.format(endDate),
                translateWord(loc),
                numberFormat.format(price),
                currency
        );
    }
    private String translateCountry() {
        for (Locale locale : Locale.getAvailableLocales()) if (locale.getDisplayCountry(code).equals(country)) return locale.getDisplayCountry(destLocale);
        return null;
    }

    private String translateWord(String word) {
        String key = code.getLanguage() + "-" + destLocale.getLanguage() + "." + word;
        return dictionary.getProperty(key, word);
    }

    public String getTranslatedOffer(){
        return country+"\t"+tStartDate+"\t"+tEndDate+"\t"+location+"\t"+tPrice+"\t"+currency;
    }
    public String getFullTranslated(){
        return code.getLanguage()+"\t"+country+"\t"+tStartDate+"\t"+tEndDate+"\t"+location+"\t"+tPrice+"\t"+currency;
    }
    public String getFullOffer(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return code.getLanguage()+"\t"+country+"\t"+simpleDateFormat.format(startDate)+"\t"+simpleDateFormat.format(endDate)+"\t"+location+"\t"+price+"\t"+currency;
    }
}
