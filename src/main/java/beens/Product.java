package beens;

import java.io.Serializable;

public class Product implements Serializable {
private static final long serialVersionUID = 1L;
  private  int prodId;
  private  String name;
  private   int price;

    public Product() { }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, int price) {
        this.prodId = id;
        this.name = name;
        this.price = price;
    }

    public int getProdId() {
        return prodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
