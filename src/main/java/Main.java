import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {

        Watchlist watchlist = new SP500Watchlist();
        System.out.println(watchlist.getWatchlist());

//        String url = "https://www.alphavantage.co/query?function=LISTING_STATUS&apikey=F0VU7B8U0G2QASWW";
//        downloadCSV(url, "listed_companies.csv");


    }

    private static void downloadCSV(String url, String fileName) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream());
        FileOutputStream fis = new FileOutputStream(fileName);
        byte[] buffer = new byte[1024];
        int count;

        while ((count = bis.read(buffer,0,1024)) != -1) {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}
