package zad1;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Offer {
    private Locale offerLocale;
    private final String country;
    private Date startDate;
    private String tStartDate;
    private Date endDate;
    private String tEndDate;
    private final String location;
    private double price;
    private String tPrice;
    private final String currency;
    String boundleName;
    private Locale destLocale;
    public Offer(Locale locale, String countryName, Date dateFrom, Date dateTo, String location, double price, String currency) {
        this.offerLocale = locale;
        this.country = countryName;
        this.startDate = dateFrom;
        this.endDate = dateTo;
        this.location = location;
        this.price = price;
        this.currency = currency;
        boundleName = "zad1.MessagesBundle";
    }

    private Offer(Locale locale, String country, String startDate, String endDate, String location, String price, String currency) {
        this.offerLocale = locale;
        this.country = country;
        this.tStartDate = startDate;
        this.tEndDate = endDate;
        this.location = location;
        this.tPrice = price;
        this.currency = currency;
        boundleName = "zad1.MessagesBundle";
    }

    public Offer translate(Locale locale, String dateFormat){
        destLocale = Locale.forLanguageTag(locale.getDisplayName().split("_")[0]);
        NumberFormat numberFormat = NumberFormat.getInstance(destLocale);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        ResourceBundle info = ResourceBundle.getBundle(boundleName, locale);

        return new Offer(
                this.offerLocale,
                translateCountryName(offerLocale, destLocale, this.country),
                simpleDateFormat.format(startDate),
                simpleDateFormat.format(endDate),
                info.getString(location),
                numberFormat.format(price),
                currency
        );
    }

    private String translateCountryName(Locale offerLocale, Locale destLocale, String countryName) {
        for (Locale l : Locale.getAvailableLocales()) {
            if (l.getDisplayCountry(offerLocale).equals(country)) {
                return l.getDisplayCountry(destLocale);
            }
        }
        return countryName;
    }



    public String getTranslatedOffer(){
        return country+"\t"+tStartDate+"\t"+tEndDate+"\t"+location+"\t"+tPrice+"\t"+currency;
    }
    public String getFullTranslated(){
        return this.offerLocale.getLanguage()+"\t"+country+"\t"+tStartDate+"\t"+tEndDate+"\t"+location+"\t"+tPrice+"\t"+currency;
    }
    public String getFullOffer(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return this.offerLocale.getLanguage()+"\t"+country+"\t"+simpleDateFormat.format(startDate)+"\t"+simpleDateFormat.format(endDate)+"\t"+location+"\t"+price+"\t"+currency;
    }
}
