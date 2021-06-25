package com.syscho.grpc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userId;
    private String emailId;
    private String name;
    private int age;
    private String gender;
    private String genre;
    private List<RecommendedMovieDto> recommendedMovies;
}
