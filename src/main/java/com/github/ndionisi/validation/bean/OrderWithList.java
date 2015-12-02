package com.github.ndionisi.validation.bean;

import javax.validation.Valid;
import java.util.List;

public class OrderWithList {

    private List<OrderItem> items;

    @Valid
    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
