package Application.DTO;

import Application.Entites.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Order_Cargo_RouteDTO {

    @JsonProperty("id_order")
    private Long id_order;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("length")
    private Float length;

    @JsonProperty("width")
    private Float width;

    @JsonProperty("height")
    private Float height;

    @JsonProperty("weight")
    private Float weight;

    @JsonProperty("volume")
    private Float volume;

    @JsonProperty("amount")
    private Float amount;

    @JsonProperty("startPoint")
    private Long startPoint;

    @JsonProperty("endPoint")
    private Long endPoint;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("isContainer")
    private Boolean isContainer;

    @JsonProperty("isDocuments")
    private Boolean isDocuments;

    @JsonProperty("stack")
    private Boolean stack;

    @JsonProperty("express")
    private Boolean express;

    public Order_Cargo_RouteDTO() {
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Long getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Long startPoint) {
        this.startPoint = startPoint;
    }

    public Long getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Long endPoint) {
        this.endPoint = endPoint;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getContainer() {
        return isContainer;
    }

    public void setContainer(Boolean container) {
        isContainer = container;
    }

    public Boolean getDocuments() {
        return isDocuments;
    }

    public void setDocuments(Boolean documents) {
        isDocuments = documents;
    }

    public Boolean getStack() {
        return stack;
    }

    public void setStack(Boolean stack) {
        this.stack = stack;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Boolean getExpress() {
        return express;
    }

    public void setExpress(Boolean express) {
        this.express = express;
    }

    public void init(Order order) {
        this.setId_order(order.getId_order());
        this.setType(order.getCargo().getType());
        this.setName(order.getName());
        this.setLength(order.getCargo().getLength());
        this.setWidth(order.getCargo().getWidth());
        this.setHeight(order.getCargo().getHeight());
        this.setWeight(order.getCargo().getWeight());
        this.setVolume(order.getCargo().getVolume());
        this.setAmount(order.getCargo().getNumber());
        this.setStartPoint(order.getRoute().getStart_point().getId_point());
        this.setEndPoint(order.getRoute().getEnd_point().getId_point());
        this.setDate(order.getDate_of_dispatch());
        this.setContainer(order.getContainer());
        this.setDocuments(order.getDocuments());
        this.setStack(order.getCargo().getStack());
        this.setExpress(order.getExpress());
    }

    public static Order_Cargo_RouteDTO fromModel(Order order) {
        Order_Cargo_RouteDTO dto = new Order_Cargo_RouteDTO();
        dto.init(order);
        return dto;
    }
}
