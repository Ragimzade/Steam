package browser;

public enum Browsers {
    CHROME("chrome"), FIREFOX("firefox"), EDGE("edge");


    private String textvalue;

     Browsers(String text) {
        textvalue = text;
    }

    public String toString() {
        return String.valueOf(textvalue);
    }
}