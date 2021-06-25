package com.syscho.grpc.controller;

import com.syscho.grpc.dto.UserDto;
import com.syscho.grpc.service.AggregateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregatorController {

    @Autowired
    private AggregateService aggregateService;

    @PostMapping("/user")
    public UserDto getUserInfo(@RequestBody UserDto userDto) {
        return aggregateService.getUserMovieSuggestions(userDto);
    }

    @PutMapping("/user")
    public UserDto updateUserInfo(@RequestBody UserDto userDto)
    {
        return aggregateService.updateInfo(userDto);
    }


}
