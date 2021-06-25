package com.syscho.grpc.user.repository;

import com.syscho.grpc.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User,String> {
}
