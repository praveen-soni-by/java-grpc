package com.syscho.grpc.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.syscho.grpc.auto.common.Gender;
import com.syscho.grpc.auto.common.Genre;
import com.syscho.grpc.auto.movie.MovieResponses;
import com.syscho.grpc.auto.movie.MovieServiceGrpc;
import com.syscho.grpc.auto.movie.RecommendedMovieRequest;
import com.syscho.grpc.auto.user.UserRequest;
import com.syscho.grpc.auto.user.UserResponse;
import com.syscho.grpc.auto.user.UserServiceGrpc;
import com.syscho.grpc.auto.user.UserUpdateRequest;
import com.syscho.grpc.dto.RecommendedMovieDto;
import com.syscho.grpc.dto.UserDto;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AggregateService {


    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;


    @GrpcClient("movie-service")
    private MovieServiceGrpc.MovieServiceBlockingStub movieServiceBlockingStub;


    public UserDto getUserInfo(UserDto userDto) {
        UserResponse userInfo = userServiceStub.getUserInfo(
                UserRequest.newBuilder().setUserId(userDto.getUserId()).build());

        return convertResponseToDto(userInfo);
    }

    public UserDto getUserMovieSuggestions(UserDto userDto) {
        UserResponse userInfo = userServiceStub.getUserInfo(
                UserRequest.newBuilder().setUserId(userDto.getUserId()).build());
        List<RecommendedMovieDto> recommendedMovies =
                getRecommendedMovieByGenre(userInfo.getGenre().name().toUpperCase());

        UserDto userDto1 = convertResponseToDto(userInfo);
        userDto1.setRecommendedMovies(recommendedMovies);
        return userDto1;
    }

    public UserDto updateInfo(UserDto userDto) {
        UserResponse userResponse = null;
        try {
            userResponse = userServiceStub.updateInfo(UserUpdateRequest.newBuilder()
                    .setAge(userDto.getAge())
                    .setUserId(userDto.getUserId().toUpperCase())
                    .setGenre(Genre.valueOf(userDto.getGenre()))
                    .setGender(Gender.valueOf(userDto.getGender()))
                    .setEmailId(userDto.getEmailId()).buildPartial());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Please provide a valid Gender/Genre");
        }

        return convertResponseToDto(userResponse);
    }


    public List<RecommendedMovieDto> getRecommendedMovieByGenre(String genre) {

        MovieResponses recommendedMovies = movieServiceBlockingStub.getRecommendedMovies(RecommendedMovieRequest.newBuilder()
                .setGenre(Genre.valueOf(genre)).build());

        return recommendedMovies.getMovieList()
                .stream()
                .map(movie -> new RecommendedMovieDto(movie.getId(), movie.getTitle(), movie.getYear(), movie.getGenre().name()))
                .collect(Collectors.toList());

    }


    private UserDto convertResponseToDto(UserResponse userResponse) {
        UserDto userDto1 = new UserDto();
        BeanUtils.copyProperties(userResponse, userDto1);
        userDto1.setGenre(userResponse.getGenre().name());
        userDto1.setGender(userResponse.getGender().name());
        return userDto1;
    }


}
