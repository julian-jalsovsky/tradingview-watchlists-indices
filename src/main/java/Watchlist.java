import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Watchlist {

    protected final Map<String, String> symbols;

    public Watchlist() throws IOException, CsvException {
        symbols = new TreeMap<>();
        extractSymbols();
        addExchanges();
    }

    protected abstract void extractSymbols();

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

    protected Document requestHTML(String url) {
        Document html;

        try {
            html = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return html;
    }
}
