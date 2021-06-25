package com.syscho.grpc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendedMovieDto {
    private int id;
    private String title;
    private int year;
    private String genre;
}
