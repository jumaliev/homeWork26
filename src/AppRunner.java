import fileUtils.FileUtils;
import model.Movie;

import java.util.*;
import java.util.stream.Collectors;

public class AppRunner {
    private Map<String, List<Movie>> castMoviesMap;
    private Map<String, List<Movie>> directorMoviesMap;
    private Map<Integer, List<Movie>> yearMoviesMap;
    private Map<String, List<String>> actorRolesMap;
    private Map<String, Set<String>> actorNamesRolesMap;

    public AppRunner() {
        this.castMoviesMap = new HashMap<>();
        this.directorMoviesMap = new HashMap<>();
        this.yearMoviesMap = new HashMap<>();
        this.actorRolesMap = new HashMap<>();
        this.actorNamesRolesMap = new TreeMap<>();
        fillingMoviesByActor();
        fillingDirectorToMoviesMap();
        fillingYearMoviesmap();

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
        searchForFilmsWhereTheActorParticipated("David Tennant");
        System.out.println("============================================================");
        searchForFilmsDirectedByTheDirector("Peter Jackson");
        System.out.println("============================================================");
        findMoviesByYearOfRelease(2019);
        System.out.println("============================================================");
        searchForRoleInMovies("David Tennant");
        System.out.println("============================================================");
        fillActorNamesRolesMap();


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

    private void fillingMoviesByActor() {
        List<Movie> movies = FileUtils.readFile();
        for (Movie movie : movies) {
            for (Movie.Cast actor : movie.getCast()) {
                String actorName = actor.getFullName();
                castMoviesMap.computeIfAbsent(actorName, k -> new ArrayList<>()).add(movie);
            }
        }
    }

    public void searchForFilmsWhereTheActorParticipated(String actorName) { // Всех фильмов, в который снимался тот или иной актёр.
        List<Movie> movies = castMoviesMap.getOrDefault(actorName, Collections.emptyList());
        for (Movie movie : movies) {
            System.out.println(movie.getName());
        }
    }
    private void fillingDirectorToMoviesMap() {
        List<Movie> movies = FileUtils.readFile();
        for (Movie movie : movies) {
            String directorName = movie.getDirector().getFullName();
            directorMoviesMap.computeIfAbsent(directorName, k -> new ArrayList<>()).add(movie);
        }
    }

    public void searchForFilmsDirectedByTheDirector(String directorFullName) { //Всех фильмов, которые режиссировал тот или иной режиссер.
        List<Movie> movies = directorMoviesMap.getOrDefault(directorFullName, Collections.emptyList());
        for (Movie movie : movies) {
            System.out.println(movie.getName());
        }
    }
    public void fillingYearMoviesmap() {
        List<Movie> movies = FileUtils.readFile();
        for (Movie movie : movies) {
            int year = movie.getYear();
            yearMoviesMap.computeIfAbsent(year, k -> new ArrayList<>()).add(movie);
        }
    }
    public void findMoviesByYearOfRelease(int year) { //Всех фильмов, которые были выпущены в определенном году.
        List<Movie> movies = yearMoviesMap.getOrDefault(year, Collections.emptyList());
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).getName());
        }
    }

    public void searchForRoleInMovies(String actorName) { //Списка фильмов и роль того или иного актера в этом фильме.
        List<Movie> movies = FileUtils.readFile();
        for (Movie movie : movies) {
            for (Movie.Cast cast : movie.getCast()) {
                if (cast.getFullName().equalsIgnoreCase(actorName)) {
                    actorRolesMap.computeIfAbsent(movie.getName(), k -> new ArrayList<>()).add(cast.getRole());
                    System.out.println(movie.getName());
                    System.out.println(cast.getRole());
                    break;
                }
            }
        }
    }
    public void fillActorNamesRolesMap() { // Список всех актёров из всех фильмов с указанием их ролей.
        List<Movie> movies = FileUtils.readFile();
        for (Movie movie : movies) {
            for (Movie.Cast cast : movie.getCast()) {
                String actorName = cast.getFullName();
                actorNamesRolesMap.computeIfAbsent(actorName, k -> new HashSet<>()).add(movie.getName() + ": " + cast.getRole());
                System.out.printf("Имя актёра: %s\nРоль актёра: %s\n",actorName,cast.getRole()) ;
                System.out.println("============================================================");
            }
        }
    }






}
