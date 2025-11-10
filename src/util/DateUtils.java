package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Data não pode ser vazia");
        }
        try {
            return LocalDate.parse(dateStr.trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Use dd/MM/yyyy", e);
        }
    }
    
    public static String formatDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(FORMATTER);
    }
    
    public static boolean isDateInRange(LocalDate date, LocalDate minDate, LocalDate maxDate) {
        if (date == null) {
            return false;
        }
        boolean afterMin = minDate == null || !date.isBefore(minDate);
        boolean beforeMax = maxDate == null || !date.isAfter(maxDate);
        return afterMin && beforeMax;
    }
}
