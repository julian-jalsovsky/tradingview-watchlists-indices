import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NasdaqWatchlist extends Watchlist {

    @Override
    protected void extractSymbols() {
        String url = "https://en.wikipedia.org/wiki/Nasdaq-100";
        Document html = requestHTML(url);
        Element table = html.getElementById("constituents");

        assert table != null;
        Elements links = table.select("tr td:eq(1)");

        storeSymbols(links);
    }
}
