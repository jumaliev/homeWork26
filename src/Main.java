import fileUtils.FileUtils;
import model.Movie;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        for (Movie e : FileUtils.readFile()) {
            System.out.println(e);
        }
    }
}