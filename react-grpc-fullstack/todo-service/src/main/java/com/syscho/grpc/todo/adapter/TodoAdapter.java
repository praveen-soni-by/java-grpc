package com.syscho.grpc.todo.adapter;

import com.syscho.grpc.todo.auto.EmptyRequest;
import com.syscho.grpc.todo.auto.Todo;
import com.syscho.grpc.todo.auto.TodoServiceGrpc;
import com.syscho.grpc.todo.auto.Todos;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import javax.annotation.PostConstruct;

@GrpcService
public class TodoAdapter extends TodoServiceGrpc.TodoServiceImplBase {

    private Todos.Builder todos = Todos.newBuilder();

    @PostConstruct
    private void setData() {
        todos.addTodo(Todo.newBuilder().setTextValue("When life gives you melons, you might be dyslexic").build());
        todos.addTodo(Todo.newBuilder().setTextValue("Never trust atoms; they make up everything.").build());
        todos.addTodo(Todo.newBuilder().setTextValue("It takes a lot of balls to golf the way I do").build());
    }

    @Override
    public void getTodos(EmptyRequest request, StreamObserver<Todos> responseObserver) {
        responseObserver.onNext(todos.build());
        responseObserver.onCompleted();
    }

    @Override
    public void addTodo(Todo addTodo, StreamObserver<Todo> responseObserver) {
        todos.addTodo(addTodo);
        responseObserver.onNext(addTodo);
        responseObserver.onCompleted();
    }
}