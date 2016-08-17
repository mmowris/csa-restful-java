package hello;

public class Product {

    private final String name;
    private final String acv;

    public Product(long name, String acv) {
        this.name = name;
        this.acv = acv;
    }

    public long getName() {
        return name;
    }

    public String getAcv() {
        return acv;
    }
}
