package fileUtils;

import com.google.gson.reflect.TypeToken;
import model.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileUtils {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("src/data/movies.json");


    public static List<Movie> readFile(){
        Type token = new TypeToken<Map<String, List<Movie>>>() {
        }.getType();
        try {
            String str = Files.readString(PATH);
            Map<String, List<Movie>> fromJson = GSON.fromJson(str, token);
            return fromJson.getOrDefault("movies", new ArrayList<>());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
