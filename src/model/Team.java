package model;

public class Team {
    private int id;
    private String name;
    private String url;
    private String nationality;

    public Team(int id, String name, String url, String nationality) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getNationality() {
        return nationality;
    }
}