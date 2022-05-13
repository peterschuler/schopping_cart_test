package org.demo.microservice.schoppingcart.schoppingcartapp.core.input;

public enum ProductCategory {

    TOYS("cat-1"),
    BOOKS("cat-2"),
    PHONES("cat-3");

    private final String categoryId;

    ProductCategory(String categoryId) {
       this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }
}
