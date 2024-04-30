import fileUtils.FileUtils;
import model.Movie;

import java.util.*;
import java.util.stream.Collectors;

public class AppRunner {
    private Map<String, List<Movie>> castMoviesMap;

    public AppRunner() {
        this.castMoviesMap = new HashMap<>();
        searchForFilmsWhereTheActorParticipated();

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
        findMoviesByActor("David Tennant");


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

    public List<Movie> searchForFilmsWhereTheActorParticipated(String actorName) {
            List<Movie> movies = FileUtils.readFile();
            return movies.stream().filter(movie -> movie.getCast().stream().anyMatch(actor -> actor.getFullName().equalsIgnoreCase(actorName))).collect(Collectors.toList());

    }
    private void searchForFilmsWhereTheActorParticipated() {
        List<Movie> movies = FileUtils.readFile();
        for (Movie movie : movies) {
            for (Movie.Cast actor : movie.getCast()) {
                String actorName = actor.getFullName();
                castMoviesMap.computeIfAbsent(actorName, k -> new ArrayList<>()).add(movie);
            }
        }
    }

    public void findMoviesByActor(String actorName) {
        List<Movie> movies = castMoviesMap.getOrDefault(actorName, Collections.emptyList());
        for (Movie movie : movies) {
            System.out.println(movie.getName());
        }
    }
    




}
