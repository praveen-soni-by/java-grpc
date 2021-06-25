package com.syscho.grpc.movie.repository;

import com.syscho.grpc.movie.entity.MovieDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MovieRepository extends CrudRepository<MovieDO, Integer> {
    List<MovieDO> findByGenre(String genre);

}
