package com.github.ndionisi.validation.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

public class OrderItem {

    private String productName;

    private int quantity;

    public OrderItem(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    @NotEmpty
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Min(1)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
