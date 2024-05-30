package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class TravelData {
    private static File dataDir;
    private List<Offer> offers;
    private List<Offer> translated;
    public TravelData(File dataDir) {
        this.dataDir = dataDir;
    }
    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        try {
            offers = loadOffers(readAll());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        translated = translateOffers(new Locale(locale.split("_")[0],locale.split("_")[1]), dateFormat);
        return translatedAsString();
    }
    public List<String> translatedAsString() {
        List<String> result = new ArrayList<>();
        for(Offer f : translated) result.add(f.getTranslatedOffer());
        return result;
    }
    private List<Offer> translateOffers(Locale locale, String dateFromat) {
        List<Offer> result = new ArrayList<>();
        for(Offer offer : offers) result.add(offer.translate(locale, dateFromat));
        return result;
    }
    private List<Offer> loadOffers(List<String> lines) {
        List<Offer> result = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(String line : lines){
            String[] fields = line.split("\t");
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale(fields[0].split("_")[0]));
            try {
                result.add(
                        new Offer(
                                new Locale(fields[0]), // code
                                fields[1], // name
                                simpleDateFormat.parse(fields[2]), // start date
                                simpleDateFormat.parse(fields[3]), // end date
                                fields[4], // place
                                numberFormat.parse(fields[5]).doubleValue(),
                                fields[6]) // currency
                );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
    public static List<String> readAll() throws IOException {
        if (!dataDir.isDirectory()) {
            throw new IllegalArgumentException("Provided fileDir is not a directory");
        }
        List<String> allLines = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(dataDir.toPath())) {
            paths.filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            List<String> lines = Files.readAllLines(filePath);
                            allLines.addAll(lines);
                        } catch (IOException e) {
                            e.getMessage();
                        }
                    });
        }

        return allLines;
    }
    public List<Offer> getOffers(){
        return offers;
    }
    public List<String> translatedAsStringGUI() {
        List<String> result = new ArrayList<>();
        for(Offer f : translated) result.add(f.getFullTranslated());
        return result;
    }
    public List<String> offersAsStringGUI() {
        List<String> result = new ArrayList<>();
        for(Offer f : offers) result.add(f.getFullOffer());
        return result;
    }
}
