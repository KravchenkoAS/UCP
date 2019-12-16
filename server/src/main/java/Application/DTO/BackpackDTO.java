package Application.DTO;

import Application.Backpack.Backpack;
import Application.Backpack.Item;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BackpackDTO implements Serializable {

    @JsonProperty("bestItems")
    private List<Item> bestItems;

    @JsonProperty("maxW")
    private Double maxW;

    @JsonProperty("bestPrice")
    private Double bestPrice;

    public BackpackDTO() {
    }

    public List<Item> getBestItems() {
        return bestItems;
    }

    public void setBestItems(List<Item> bestItems) {
        this.bestItems = bestItems;
    }

    public Double getMaxW() {
        return maxW;
    }

    public void setMaxW(Double maxW) {
        this.maxW = maxW;
    }

    public Double getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(Double bestPrice) {
        this.bestPrice = bestPrice;
    }

    public void init(Backpack backpack) {
        this.setBestItems(backpack.getBestItems());
        this.setMaxW(backpack.getMaxW());
        this.setBestPrice(backpack.getBestPrice());
    }

    public static BackpackDTO fromModel(Backpack backpack) {
        BackpackDTO dto = new BackpackDTO();
        dto.init(backpack);
        return dto;
    }
}
