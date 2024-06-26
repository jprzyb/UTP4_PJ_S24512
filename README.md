# UTP4_PJ_S24512
Biuro podróży otrzymuje od róznych kontrahentów (polskich, angielskich, niemieckich... ) pliki z ofertami wyjazdów-wycieczek. Każda oferta jest w jednym wierszu pliku i zawiera, rozdzielone znakami tabulacji:

lokalizacje_kontrahenta kraj  date_wyjazdu  date_powrotu miejsce cene symbol_waluty

gdzie:
lokalizacja - napis,  oznaczający język_kraj (np. pl_PL, en_US; tak jak zwraca to metoda toString() z klasy Locale)
kraj - nazwa kraju w języku kontrahenta,
daty - (wyjazdu, powrotu) daty w formacie RRRR-MM-DD (np. 2015-12-31),
miejsce - jedno z: [morze, jezioro, góry] - w języku kontrahenta,
cena - liczba w formacie liczb, używanym w kraji kontrahenta,
symbol_waluty = PLN, USD itp.


Napisać aplikację, która:
dodaje zawartość plików ofert do bazy danych (dowolny silnik bazodanowy)
jest zinternacjonalizowana - prezentuje klientowi w tabeli JTable  pełny zestaw ofert w wybranym przez niego języku  i wg wybranych ustawień regionalnych.

Wybrać dwie - trzy lokalizacje do testowania aplikacji.
Pliki kontrahentów dostarczyć w podkatalogu data projektu

W ramach projektu Stworzyć klasy TravelData i Database.
W klasie TravelData zdefiniowac metodę:
List<String> travelData.getOffersDescriptionsList(String loc, String dateFormat)
która zwraca listę napisów, każdy z których jest opisem jednej oferty z plików katalogu data, przedstawionym zgodnie z regułami i w języku lokalizacji loc i przy podanym formacie daty (możliwe formaty określa klasa SimpleDateFormat).

W klasie Database zapewnić utworzenie bazy danych i wpisanie do niej wsyztskich ofert, wczytanych z plików (metoda createDb()) oraz otwarcie GUI z tabelą, pokazującą wczytane oferty. GUI powinno pozwalac na wybór języka i ustawien regionalnych, w których pokazywane są oferty.

Wszelkie operacje bazodanowe mogą być przeprowadzane tylko w klasie Database.

Zapewnić, by następująca (modyfikowalna tylko w miejscach zanzaczonym na zielono) metoda main z klasy Main:

import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    File dataDir = new File("data");
    TravelData travelData = new TravelData(dataDir);
    String dateFormat = "yyyy-MM-dd";
    for (String locale : Arrays.asList("pl_PL", "en_GB")) {
      List<String> odlist = travelData.getOffersDescriptionsList(locale, dateFormat);
      for (String od : odlist) System.out.println(od);
    }
    // --- część bazodanowa
    String url = /*<-- tu należy wpisać URL bazy danych */   
    Database db = new Database(url, travelData);
    db.create();
    db.showGui();
  }
 
}
działala prawidłowo i wyprowadziła dla następującyh przykładowych danych, zawartych w plikach katalogu data:
pl	Japonia	2015-09-01	2015-10-01	jezioro	10000,20	PLN
pl_PL	Włochy	2015-07-10	2015-07-30	morze	4000,10	PLN
en_GB	United States	2015-07-10	2015-08-30	mountains	5,400.20	USD
następujące wyniki:

Japonia 2015-09-01 2015-10-01 jezioro 10 000,2 PLN
Włochy 2015-07-10 2015-07-30 morze 4 000,1 PLN
Stany Zjednoczone Ameryki 2015-07-10 2015-08-30 góry 5 400,2 USD
Japan 2015-09-01 2015-10-01 lake 10,000.2 PLN
Italy 2015-07-10 2015-07-30 sea 4,000.1 PLN
United States 2015-07-10 2015-08-30 mountains 5,400.2 USD

a następnie utworzyła bazę danych oraz pokazała GUI.
