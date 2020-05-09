package Application.Backpack;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    private List<Item> bestItems;

    private Double maxV;

    private Double bestPrice;

    private Double bestVolume;

    public Backpack(Double maxV) {
        this.maxV = maxV;
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

    public Double getBestVolume() {
        return bestVolume;
    }

    public void setBestVolume(Double bestVolume) {
        this.bestVolume = bestVolume;
    }

    private double calcWeight(List<Item> items) {
        double sumV = 0;
        for (Item item : items) {
            sumV += item.getVolume();
        }

        return sumV;
    }

    private double calcPrice(List<Item> items) {
        double sumPrice = 0;
        for (Item item : items) {
            sumPrice += item.getPrice();
        }

        return sumPrice;
    }

    private void checkSet(List<Item> items) {
        if (this.bestItems == null) {
            if (calcWeight(items) <= this.maxV) {
                this.bestItems = items;
                this.bestPrice = calcPrice(items);
            }
        } else {
            if (calcWeight(items) <=
                    this.maxV &&
                    calcPrice(items) >
                            this.bestPrice) {
                this.bestItems = items;
                this.bestPrice = calcPrice(items);
                this.bestVolume = calcWeight(items);
            }
        }
    }

    public void makeAllSets(List<Item> items) {
        if (items.size() > 0) {
            checkSet(items);
        }

        for (int i = 0; i < items.size(); i++) {
            List<Item> newSet = new ArrayList<>(items);
            newSet.remove(i);
            makeAllSets(newSet);
        }
    }

}
