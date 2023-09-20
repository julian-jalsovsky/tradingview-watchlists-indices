import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {

        Watchlist watchlist = new SP500Watchlist();
        System.out.println(watchlist.getWatchlist());
    }
}
