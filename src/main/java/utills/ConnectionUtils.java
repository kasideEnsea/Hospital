package utills;

public enum ConnectionUtils {
    URL("jdbc:postgresql://127.0.0.1:5432/hospital"),
    USER("postgres"),
    PASSWORD("12345678");

    public final String value;
    ConnectionUtils(String value) {
        this.value = value;
    }
}
