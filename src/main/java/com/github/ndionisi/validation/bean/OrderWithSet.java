package com.github.ndionisi.validation.bean;

import javax.validation.Valid;
import java.util.Set;

public class OrderWithSet {

    private Set<OrderItem> items;

    @Valid
    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
}
