import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        HashMap<String, Watchlist> watchlists = new HashMap<>();
        watchlists.put("S&P500", new SP500Watchlist());
        watchlists.put("Nasdaq100", new NasdaqWatchlist());
        watchlists.put("Dow30", new DowWatchlist());

        writeToFile(watchlists);
    }

    private static void writeToFile(HashMap<String, Watchlist> watchlists) {
        for (Map.Entry<String, Watchlist> watchlist : watchlists.entrySet()) {
            BufferedWriter writer;
            String fileName = "watchlist-" + watchlist.getKey() + ".txt";
            try {
                writer = new BufferedWriter(new FileWriter(fileName));
                writer.write(watchlist.getValue().getWatchlist());
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
