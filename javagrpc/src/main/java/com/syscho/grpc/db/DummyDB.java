package com.syscho.grpc.db;

import com.syscho.grpc.stub.ProductOuterClass;

import java.util.HashMap;
import java.util.Map;


public class DummyDB {


    Map<Integer, ProductOuterClass.Product> departmentsMap;

    public DummyDB() {
        departmentsMap = new HashMap<>();
        departmentsMap.put(101, ProductOuterClass.Product.newBuilder().setProductName("Nike Shoes").setProductType(ProductOuterClass.ProductType.CLOTHING).setProductId(101).build());
        departmentsMap.put(102, ProductOuterClass.Product.newBuilder().setProductName("Jeans").setProductType(ProductOuterClass.ProductType.HOUSE_HOLD).setProductId(102).build());
        departmentsMap.put(103, ProductOuterClass.Product.newBuilder().setProductName("House Hold Stuff").setProductType(ProductOuterClass.ProductType.ELECTRONIC).setProductId(103).build());
        departmentsMap.put(104, ProductOuterClass.Product.newBuilder().setProductName("Mobile").setProductId(104).build());
    }


    public ProductOuterClass.Products getProducts() {
        ProductOuterClass.Products.Builder products = ProductOuterClass.Products.newBuilder();
        departmentsMap.values().forEach(e -> products.addProducts(e));
        return products.build();
    }

    public ProductOuterClass.Product getProductById(int productId) {
        return departmentsMap.getOrDefault(productId, null);
    }

    public boolean deleteById(int productId) {
        ProductOuterClass.Product product = departmentsMap.remove(productId);
        if (null == product) return false;
        return true;
    }

    public ProductOuterClass.Product save(ProductOuterClass.Product product) {
        return departmentsMap.put(product.getProductId(), product);
    }


}











