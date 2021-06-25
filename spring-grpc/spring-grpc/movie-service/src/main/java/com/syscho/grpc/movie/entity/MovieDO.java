package com.syscho.grpc.movie.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "MOVIE")
@ToString
public class MovieDO {

    @Id
    private int id;
    private String title;
    private int year;
    private String genre;
}
