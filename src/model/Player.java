package model;

public class Player {
    private int id;
    private String nome;
    private String url;
    private String nationality;

    public Player(int id, String nome, String url, String nationality) {
        this.id = id;
        this.nome = nome;
        this.url = url;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUrl() {
        return url;
    }

    public String getNationality() {
        return nationality;
    }
}
