package com.syscho.grpc.service;

import com.syscho.grpc.db.DummyDB;
import com.syscho.grpc.exception.ProductNotFoundException;
import com.syscho.grpc.stub.ProductOuterClass;
import com.syscho.grpc.stub.productGrpc;
import io.grpc.stub.StreamObserver;

public class ProductAdaptor extends productGrpc.productImplBase {


    private DummyDB dummyDB = new DummyDB();

    @Override
    public void getProducts(ProductOuterClass.Empty request, StreamObserver<ProductOuterClass.Products> responseObserver) {

        responseObserver.onNext(dummyDB.getProducts());
        responseObserver.onCompleted();
    }

    @Override
    public void getProductById(ProductOuterClass.ProductId request, StreamObserver<ProductOuterClass.Product> responseObserver) {

        ProductOuterClass.Product product = dummyDB.getProductById(request.getProductId());
        if (null == product) throw new ProductNotFoundException("Invalid Product Id " + request.getProductId());

        responseObserver.onNext(product);
        responseObserver.onCompleted();

    }

    @Override
    public void deleteProductById(ProductOuterClass.ProductId request, StreamObserver<ProductOuterClass.StringMsg> responseObserver) {

        int productId = request.getProductId();
        boolean isDeleted = dummyDB.deleteById(productId);
        String msg = "Product deleted successfully ";
        if (!isDeleted) {
            msg = String.format("Failed to delete product with productId %s ", productId);
        }

        responseObserver.onNext(ProductOuterClass.StringMsg.newBuilder().setMsg(msg).build());
        responseObserver.onCompleted();
    }

    @Override
    public void saveProduct(ProductOuterClass.Product request, StreamObserver<ProductOuterClass.Product> responseObserver) {
        ProductOuterClass.Product addedProduct = dummyDB.save(request);
        responseObserver.onNext(addedProduct);
        responseObserver.onCompleted();
    }
}






