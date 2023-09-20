public class Main {
    public static void main(String[] args) {

        Watchlist watchlistSP500 = new SP500Watchlist();
        System.out.println(watchlistSP500.getWatchlist());

        Watchlist watchlistNasdaq = new NasdaqWatchlist();
        System.out.println(watchlistNasdaq.getWatchlist());
    }
}
