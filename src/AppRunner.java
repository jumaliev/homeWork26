import fileUtils.FileUtils;
import model.Movie;

import java.util.*;
import java.util.stream.Collectors;

public class AppRunner {
    private Map<String, List<String>> castMoviesMap;

    public AppRunner() {
        run();

    }

    public void run() {

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
        System.out.println("============================================================");
        searchForFilmsWhereTheActorParticipated("Orlando Bloom");
        System.out.println(castMoviesMap);

    }

    public List<Movie> searchForMovieByFullTitle(String movieName) {
        List<Movie> movies = FileUtils.readFile();
        return movies.stream().filter(movie -> movie.getName().equalsIgnoreCase(movieName)).collect(Collectors.toList());
    }

    public List<Movie> searchForMovieByPartialTitleMatch(String partialName) {
        List<Movie> movies = FileUtils.readFile();
        return movies.stream().filter(movie -> movie.getName().toLowerCase().contains(partialName.toLowerCase())).collect(Collectors.toList());
    }

    public List<Movie> sortByAscendingYearOfFilmRelease() { // год фильма по возрастанию
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparingInt(Movie::getYear);
        movies.sort(cmp);
        return movies;
    }

    public List<Movie> sortByDescendingYearOfFilmRelease() { // Год фильма по убыванию
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparingInt(Movie::getYear);
        movies.sort(cmp.reversed());
        return movies;
    }

    public List<Movie> sortByAscendingMovieTitle() { // Название фильма по возрастанию
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparing(Movie::getName);
        movies.sort(cmp);
        return movies;
    }

    public List<Movie> sortByMovieTitleInDescendingOrder() { // НАзвание фильма по убыванию
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparing(Movie::getName);
        movies.sort(cmp.reversed());
        return movies;
    }

    public List<Movie> sortByAscendingDirectorName() { // Директор по возрастанию
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparing(movie -> movie.getDirector().getFullName());
        movies.sort(cmp);
        return movies;
    }

    public List<Movie> sortByDescendingNameOfDirector() { // Директор по убыванию
        List<Movie> movies = FileUtils.readFile();
        Comparator<Movie> cmp = Comparator.comparing(movie -> movie.getDirector().getFullName());
        movies.sort(cmp.reversed());
        return movies;
    }

    public void searchForFilmsWhereTheActorParticipated(String actorName) {
        List<Movie> movies = FileUtils.readFile();
        castMoviesMap = new HashMap<>();
        for (Movie movie : movies) {
            castMoviesMap.computeIfAbsent(actorName, k -> new ArrayList<>()).add(movie.getName());
        }
    }



}
