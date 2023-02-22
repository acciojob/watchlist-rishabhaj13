package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String, Movie> mov = new HashMap<>();
    Map<String, Director> dir = new HashMap<>();
    Map<String, ArrayList<String>> map = new HashMap<>();


    public void addMovie(Movie movie) {
        mov.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        dir.put(director.getName(), director);
        map.put(director.getName(), new ArrayList<String>());
    }

    public void addMovieDirectorPair(String movie, String director) {
        if(mov.containsKey(movie) && dir.containsKey(director)){
            map.get(director).add(movie);
        }
    }

    public Movie getMovieByName(String name) {
        return mov.get(name);
    }

    public Director getDirectorByName(String name) {
        return dir.get(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        List<String> moviesList = new ArrayList<>();
        if(map.containsKey(director)) moviesList = map.get(director);
        return moviesList;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(mov.keySet());
    }

    public void deleteDirector(String director) {
        List<String> movies = new ArrayList<String>();
        if(map.containsKey(director)){
            movies = map.get(director);
            for(String movie: movies){
                if(mov.containsKey(movie)){
                    mov.remove(movie);
                }
            }

            map.remove(director);
        }

        if(dir.containsKey(director)){
            dir.remove(director);
        }
    }

    public void deleteAllDirectors() {
        HashSet<String> moviesSet = new HashSet<String>();


        for(String director: map.keySet()){
            for(String movie: map.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(mov.containsKey(movie)){
                mov.remove(movie);
            }
        }
    }
}
