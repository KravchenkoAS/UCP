package Application.Backpack;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    private List<Item> bestItems;

    private Double maxW;

    private Double bestPrice;

    public Backpack() {
    }

    public Backpack(Double maxW) {
        this.maxW = maxW;
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

    private double calcWeight(List<Item> items) {
        double sumW = 0;
        for (Item item : items) {
            sumW += item.getWeight();
        }

        return sumW;
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
            if (calcWeight(items) <= this.maxW) {
                this.bestItems = items;
                this.bestPrice = calcPrice(items);
            }
        } else {
            if (calcWeight(items) <=
                    this.maxW &&
                    calcPrice(items) >
                            this.bestPrice) {
                this.bestItems = items;
                this.bestPrice = calcPrice(items);
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
