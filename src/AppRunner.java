import fileUtils.FileUtils;
import model.Movie;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppRunner {
    public AppRunner() {
        run();
        System.out.println(searchForMovieByFullTitle("Carnival Row"));
        System.out.println(searchForFovieByPartialTitleMatch("G"));
    }



    public void run () {
        for (Movie e : FileUtils.readFile()) {
            System.out.println(e);
        }
    }
    public List<Movie> searchForMovieByFullTitle(String movieName) {
        List<Movie> movies = FileUtils.readFile();
        return movies.stream().filter(movie -> movie.getName().equalsIgnoreCase(movieName)).collect(Collectors.toList());
    }
    public List<Movie> searchForFovieByPartialTitleMatch(String partialName) {
        List<Movie> movies = FileUtils.readFile();
        return movies.stream().filter(movie -> movie.getName().toLowerCase().contains(partialName.toLowerCase())).collect(Collectors.toList());
    }
}




