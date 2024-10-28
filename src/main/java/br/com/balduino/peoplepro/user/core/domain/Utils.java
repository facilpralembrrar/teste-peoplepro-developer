package br.com.balduino.peoplepro.user.core.domain;

public final class Utils {
    private static Utils instance;

    private Utils() {
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }

        return instance;
    }

    public void validateString(String fieldName, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(String.format("%s cannot be null or blank", fieldName));
        }
    }

    public void validateObject(String fieldName, Object value) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", fieldName));
        }
    }
}
