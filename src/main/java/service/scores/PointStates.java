package service.scores;


public enum PointStates {
    ZERO ("0"),
    FIFTEEN ("15"),
    THIRTY ("30"),
    FORTY ("40"),
    ADVANTAGE ("AD");

    private String value;

    PointStates(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
