package Application.Backpack;

public class Item {

    private Long id_order;

    private String name;

    private Double volume;

    private Double price;

    public Item(Long id_order, String name, Double volume, Double price) {
        this.id_order = id_order;
        this.name = name;
        this.volume = volume;
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

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
