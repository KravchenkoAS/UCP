package Application.Backpack;

public class Item {

    private Long id_order;

    private String name;

    private Double weight;

    private Double price;

    public Item() {
    }

    public Item(Long id_order, String name, Double weight, Double price) {
        this.id_order = id_order;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
