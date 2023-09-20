import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class Watchlist {

    public abstract String getWatchlist();
    protected abstract void extractSymbols();

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
