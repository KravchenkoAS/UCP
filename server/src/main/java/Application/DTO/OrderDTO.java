package Application.DTO;

import Application.Entites.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class OrderDTO {
    @JsonProperty("id_order")
    private Long id_order;

    @JsonProperty("name")
    private String name;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("status")
    private String status;

    @JsonProperty("date_of_dispatch")
    private LocalDate date_of_dispatch;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("isDocuments")
    private Boolean isDocuments;

    @JsonProperty("isContainer")
    private Boolean isContainer;

    @JsonProperty("express")
    private Boolean express;

    public OrderDTO() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate_of_dispatch() {
        return date_of_dispatch;
    }

    public void setDate_of_dispatch(LocalDate date_of_dispatch) {
        this.date_of_dispatch = date_of_dispatch;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getDocuments() {
        return isDocuments;
    }

    public void setDocuments(Boolean documents) {
        isDocuments = documents;
    }

    public Boolean getContainer() {
        return isContainer;
    }

    public void setContainer(Boolean container) {
        isContainer = container;
    }

    public Boolean getExpress() {
        return express;
    }

    public void setExpress(Boolean express) {
        this.express = express;
    }

    public void init(Order order) {
        this.setId_order(order.getId_order());
        this.setName(order.getName());
        this.setDate(order.getDate());
        this.setStatus(order.getStatus());
        this.setPrice(order.getPrice());
        this.setDate_of_dispatch(order.getDate_of_dispatch());
        this.setContainer(order.getContainer());
        this.setDocuments(order.getDocuments());
        this.setExpress(order.getExpress());
    }

    public static OrderDTO fromModel(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.init(order);
        return dto;
    }
}
