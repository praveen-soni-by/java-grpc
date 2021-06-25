package com.syscho.grpc.user.service;

import com.syscho.grpc.auto.common.Gender;
import com.syscho.grpc.auto.common.Genre;
import com.syscho.grpc.auto.user.UserRequest;
import com.syscho.grpc.auto.user.UserResponse;
import com.syscho.grpc.auto.user.UserServiceGrpc;
import com.syscho.grpc.auto.user.UserUpdateRequest;
import com.syscho.grpc.user.entity.User;
import com.syscho.grpc.user.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Optional;

@GrpcService
@Slf4j
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void getUserInfo(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder userBuilder = UserResponse.newBuilder();
        userRepository.findById(request.getUserId().toUpperCase())
                .ifPresent(user -> userBuilder.setAge(user.getAge())
                        .setEmailId(user.getEmailId())
                        .setUserId(user.getUserId())
                        .setGenre(Genre.valueOf(user.getGenre()))
                        .setGender(Gender.valueOf(user.getGender().toUpperCase()))
                        .setName(user.getName()).build());
        responseObserver.onNext(userBuilder.build());
        responseObserver.onCompleted();
    }


    @Override
    @Transactional
    public void updateInfo(UserUpdateRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder userBuilder = UserResponse.newBuilder();
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isPresent()) {
            save(request, user.get());
            buildResponse(request, userBuilder);
        }
        responseObserver.onNext(userBuilder.build());
        responseObserver.onCompleted();

    }

    private void save(UserUpdateRequest request, User userDo) {
        userDo.setEmailId(request.getEmailId());
        userDo.setAge(request.getAge());
        userDo.setUserId(request.getUserId());
        userDo.setGenre(request.getGenre().name());
        userRepository.save(userDo);
    }

    private void buildResponse(UserUpdateRequest request, UserResponse.Builder userBuilder) {
        this.userRepository.findById(request.getUserId())
                .ifPresent(updateUser ->
                        userBuilder.setName(updateUser.getName())
                                .setEmailId(updateUser.getEmailId())
                                .setGenre(Genre.valueOf(updateUser.getGenre()))
                                .setAge(updateUser.getAge())
                                .setGender(Gender.valueOf(updateUser.getGender()))
                );
    }
}

