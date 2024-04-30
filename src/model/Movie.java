package model;

import java.util.List;

public class Movie{

    private String name;
    private int year;
    private String description;
    private Director director;
    private List<Cast> cast;

    public String getName() {
        return name;
    }


    public Director getDirector() {
        return director;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }



    public static class Director {
        private String fullName;

        public String getFullName() {
            return fullName;
        }

        @Override
        public String toString() {
            return "Режиссер: " + fullName;
        }

    }

    static class Cast {
        private String fullName;
        private String role;

        public String getFullName() {
            return fullName;
        }

        public String getRole() {
            return role;
        }

        @Override
        public String toString() {
            return "\n     Актер: " + fullName + "\n     Роль: " + role;
        }
    }

    @Override
    public String toString() {
        return "\nНазвание фильма: '" + name +
                "'\n     Год выхода: " + year +
                "\n     Описание: " + description +
                "\n     " + director.toString() +
                "\n     " + cast.toString();
    }
}

