package model;

import java.util.List;

public class Movie {
    private String name;
    private int year;
    private String description;
    private Director director;
    private List<Cast> cast;

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public Director getDirector() {
        return director;
    }

    public List<Cast> getCast() {
        return cast;
    }

    class Director {
        private String fullName;

        public String getFullName() {
            return fullName;
        }
    }

    class Cast {
        private String fullName;
        private String role;

        public String getFullName() {
            return fullName;
        }

        public String getRole() {
            return role;
        }
    }
}

