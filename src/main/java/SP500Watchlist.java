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

    public SP500Watchlist() throws IOException, CsvException {
        super();
    }

    @Override
    protected void extractSymbols() {
        String url = "https://en.wikipedia.org/wiki/List_of_S%26P_500_companies";
        Document html = requestHTML(url);
        Element table = html.getElementById("constituents");

        assert table != null;
        Elements links = table.getElementsByClass("external text");

        for (Element link : links) {
            symbols.put(link.text(), null);
        }
    }
}
