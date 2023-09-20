import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DowWatchlist extends Watchlist {

    @Override
    protected void extractSymbols() {
        String url = "https://en.wikipedia.org/wiki/Dow_Jones_Industrial_Average";
        Document html = requestHTML(url);
        Element table = html.getElementById("constituents");

        assert table != null;
        Elements links = table.getElementsByClass("external text");

        storeSymbols(links);
    }
}
