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

    @JsonProperty("maxV")
    private Double maxV;

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

    public Double getMaxV() {
        return maxV;
    }

    public void setMaxV(Double maxV) {
        this.maxV = maxV;
    }

    public Double getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(Double bestPrice) {
        this.bestPrice = bestPrice;
    }

    public void init(Backpack backpack) {
        this.setBestItems(backpack.getBestItems());
        this.setMaxV((Math.ceil(backpack.getBestVolume() * 100) / 100));
        this.setBestPrice(backpack.getBestPrice());
    }

    public static BackpackDTO fromModel(Backpack backpack) {
        BackpackDTO dto = new BackpackDTO();
        dto.init(backpack);
        return dto;
    }
}
