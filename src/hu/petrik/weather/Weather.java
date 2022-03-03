package hu.petrik.weather;

public class Weather {
    private String country;
    private String today;
    private int low;
    private int high;
    private String tomorrow;
    private int tLow;
    private int tHigh;

    public String getCountry() {
        return country;
    }

    public Weather(String line) {
        line = line.replace("/", "\t");

        String[] data = line.split("\\t+", -1);

        country = data[0].trim();
        today = data[1].trim();
        low = Integer.parseInt(data[2].trim());
        high = Integer.parseInt(data[3].trim());
        tomorrow = data[4].trim();
        tLow = Integer.parseInt(data[5].trim());
        tHigh = Integer.parseInt(data[6].trim());
    }

    @Override
    public String toString() {
        return String.format("%-15s:\t %-10s %2d/%-2d \ttomorrow: %-10s %2d/%-2d", country, tomorrow, low, high, tomorrow, tLow, tHigh);
    }
}
