import fileUtils.FileUtils;
import model.Movie;

import java.util.*;
import java.util.stream.Collectors;


public class AppRunner {
    public AppRunner() {
        run();

    }



    public void run () {
        for (Movie e : FileUtils.readFile()) {
            System.out.println(e);
        }
        System.out.println("============================================================");
        System.out.println(searchForMovieByFullTitle("Carnival Row"));
        System.out.println("============================================================");
        System.out.println(searchForMovieByPartialTitleMatch("Go"));
        System.out.println("============================================================");
        System.out.println(sortByAscendingYearOfFilmRelease());
        System.out.println("============================================================");
        System.out.println(sortByDescendingYearOfFilmRelease());
        System.out.println("============================================================");
        System.out.println(sortByAscendingMovieTitle());
        System.out.println("============================================================");
        System.out.println(sortByMovieTitleInDescendingOrder());
        System.out.println("============================================================");
        System.out.println(sortByAscendingDirectorName());
        System.out.println("============================================================");
        System.out.println(sortByDescendingNameOfDirector());

    }
    public List<Movie> searchForMovieByFullTitle(String movieName) {
        List<Movie> movies = FileUtils.readFile();
        return movies.stream().filter(movie -> movie.getName().equalsIgnoreCase(movieName)).collect(Collectors.toList());
    }
    public List<Movie> searchForMovieByPartialTitleMatch(String partialName) {
        List<Movie> movies = FileUtils.readFile();
        return movies.stream().filter(movie -> movie.getName().toLowerCase().contains(partialName.toLowerCase())).collect(Collectors.toList());
    }

    public List<Movie> sortByAscendingYearOfFilmRelease(){
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparingInt(Movie::getYear);
        movies.sort(cmp);
        return movies;
    }
    public List<Movie> sortByDescendingYearOfFilmRelease(){
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparingInt(Movie::getYear);
        movies.sort(cmp.reversed());
        return movies;
    }
    public List<Movie> sortByAscendingMovieTitle() {
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparing(Movie::getName);
        movies.sort(cmp);
        return movies;
    }
    public List<Movie> sortByMovieTitleInDescendingOrder() {
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparing(Movie::getName);
        movies.sort(cmp.reversed());
        return movies;
    }

    public List<Movie> sortByAscendingDirectorName() {
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparing(movie -> movie.getDirector().getFullName());
        movies.sort(cmp);
        return movies;
    }
    public List<Movie> sortByDescendingNameOfDirector() {
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparing(movie -> movie.getDirector().getFullName());
        movies.sort(cmp.reversed());
        return movies;
    }
}




