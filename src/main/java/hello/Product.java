package hello;

public class Product {

    private final String name;
    private final String acv;

    public Product(String name, String acv) {
        this.name = name;
        this.acv = acv;
    }

    public String getName() {
        return name;
    }

    public String getAcv() {
        return acv;
    }
}
