package hello;

public class Product {

    private final String name;
    private final String productcode;

    public Product(String name, String productcode) {
        this.name = name;
        this.productcode = productcode;
    }

    public String getName() {
        return name;
    }

    public String getProductcode() {
        return productcode;
    }
}
