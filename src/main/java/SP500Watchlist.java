import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SP500Watchlist extends Watchlist {

    private final String url;
    private final Map<String, String> symbols;

    public SP500Watchlist() throws IOException, CsvException {
        url = "https://en.wikipedia.org/wiki/List_of_S%26P_500_companies";
        symbols = new TreeMap<>();
        extractSymbols();
        addExchanges();
    }

    private void addExchanges() throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader("listed_companies.csv")).build();
        List<String[]> myEntries = reader.readAll();

        Map<String, String> map = new HashMap<>();
        for (String[] entry : myEntries) {
            map.put(entry[0], entry[1]);
        }

        for (String symbol : symbols.keySet()) {
            symbols.replace(symbol, map.get(symbol));
        }
    }

    @Override
    public String getWatchlist() {
        StringBuilder watchlist = new StringBuilder();
        for (Map.Entry<String, String> set : symbols.entrySet()) {
            watchlist.append(set.getValue())
                    .append(":")
                    .append(set.getKey())
                    .append(",");
        }
        return watchlist.toString();
    }

    @Override
    protected void extractSymbols() {
        Document html = requestHTML(url);
        Element table = html.getElementById("constituents");

        assert table != null;
        Elements links = table.getElementsByClass("external text");

        for (Element link : links) {
            symbols.put(link.text(), null);
        }
    }
}
