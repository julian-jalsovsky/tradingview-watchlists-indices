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

    public Watchlist() {
        symbols = new TreeMap<>();
        extractSymbols();
        addExchanges();
    }

    protected abstract void extractSymbols();

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
    
    private void addExchanges() {
        Map<String, String> listedCompanies = getListedCompanies();
        for (String symbol : symbols.keySet()) {
            symbols.replace(symbol, listedCompanies.get(symbol));
        }
    }

    private Map<String, String> getListedCompanies() {
        List<String[]> listedCompanies = readCSV();
        return convertListToMap(listedCompanies);
    }

    private Map<String, String> convertListToMap(List<String[]> companies) {
        Map<String, String> map = new HashMap<>();
        for (String[] entry : companies) {
            map.put(entry[0], entry[1]);
        }
        return map;
    }

    private List<String[]> readCSV() {
        String fileName = "listed_companies.csv";
        try(CSVReader reader = new CSVReaderBuilder(new FileReader(fileName)).build()) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
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
