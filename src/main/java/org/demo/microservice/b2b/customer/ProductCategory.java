package org.demo.microservice.b2b.customer;

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
