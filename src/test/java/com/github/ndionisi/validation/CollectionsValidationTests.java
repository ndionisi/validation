package com.github.ndionisi.validation;

import com.github.ndionisi.validation.bean.OrderItem;
import com.github.ndionisi.validation.bean.OrderWithList;
import com.github.ndionisi.validation.bean.OrderWithSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ValidationApplication.class)
public class CollectionsValidationTests {

    @Inject
    private Validator validator;

    @Test
    public void validateOrderWithSet() {
        OrderWithSet order = new OrderWithSet();
        order.setItems(new HashSet<>(buildOrderItems()));

        Set<ConstraintViolation<OrderWithSet>> violations = validator.validate(order);
        assertThat(violations, hasSize(2));
        Set<String> propertyPaths = toPath(violations);
        assertThat(propertyPaths.contains("items[].productName"), is(true));
        assertThat(propertyPaths.contains("items[].quantity"), is(true));
    }

    @Test
    public void validateOrderWithList() {
        OrderWithList order = new OrderWithList();
        order.setItems(new ArrayList<>(buildOrderItems()));

        Set<ConstraintViolation<OrderWithList>> violations = validator.validate(order);
        assertThat(violations, hasSize(2));
        Set<String> propertyPaths = toPath(violations);
        assertThat(propertyPaths.contains("items[0].productName"), is(true));
        assertThat(propertyPaths.contains("items[2].quantity"), is(true));
    }

    private Collection<OrderItem> buildOrderItems() {
        OrderItem invalidItem1 = new OrderItem("", 1);
        OrderItem validItem = new OrderItem("Google Nexus 5", 1);
        OrderItem invalidItem2 = new OrderItem("Apple iPhone 6S", -1);
        return Arrays.asList(invalidItem1, validItem, invalidItem2);
    }

    private <T> Set<String> toPath(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(Path::toString)
                .collect(Collectors.toSet());
    }

}
