package com.syscho.grpc.movie.service;

import com.syscho.grpc.auto.common.Genre;
import com.syscho.grpc.auto.movie.Movie;
import com.syscho.grpc.auto.movie.MovieResponses;
import com.syscho.grpc.auto.movie.MovieServiceGrpc;
import com.syscho.grpc.auto.movie.RecommendedMovieRequest;
import com.syscho.grpc.movie.entity.MovieDO;
import com.syscho.grpc.movie.repository.MovieRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@GrpcService
public class MovieAdaptor extends MovieServiceGrpc.MovieServiceImplBase {
    @Autowired
    private MovieRepository movieRepository;


    @Override
    public void getRecommendedMovies(RecommendedMovieRequest request,
                                     StreamObserver<MovieResponses> responseObserver) {
        MovieResponses.Builder movieBuilder = MovieResponses.newBuilder();

        movieRepository.findByGenre(request.getGenre().name().toUpperCase())
                .stream()
                .map(movie -> movieBuilder.addMovie(buildMovie(movie))).collect(Collectors.toList());

        responseObserver.onNext(movieBuilder.build());
        responseObserver.onCompleted();
    }

    private Movie buildMovie(MovieDO movie) {
        return Movie.newBuilder()
                .setTitle(movie.getTitle())
                .setGenre(Genre.valueOf(movie.getGenre()))
                .setId(movie.getId())
                .setYear(movie.getYear())
                .build();
    }


}
