package logic.handler;

import javafx.util.StringConverter;

public class TimeHandler extends StringConverter<Number> {
    private static final String SEPARATOR_MINUTES = "min und ";
    private static final String SEPARATOR_SECONDS = "s";

    @Override
    public String toString(Number numberDurationInMs) {
        int durationInMs = numberDurationInMs.intValue();
        durationInMs /= 1000;
        if (durationInMs == 0) return "";
        int min = durationInMs / 60;
        int sec = durationInMs % 60;
        return min+ SEPARATOR_MINUTES + sec+ SEPARATOR_SECONDS;
    }

    @Override
    public Number fromString(String str) {
        if (str == null || str.isEmpty()) return 0;

        int indexMinSeparator = str.indexOf(SEPARATOR_MINUTES);
        int indexSecSeparator = str.indexOf(SEPARATOR_SECONDS);

        String min = str.substring(0, indexMinSeparator);
        String sec = str.substring(indexMinSeparator + SEPARATOR_MINUTES.length(), indexSecSeparator);

        try {
            int minutes = Integer.parseInt(min);
            int seconds = Integer.parseInt(sec);
            return minutes * 60000 + seconds*1000;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}