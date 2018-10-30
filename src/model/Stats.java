package model;

public class Stats {
    String nationality;
    int count;

    public Stats(String nationality, int count) {
        this.nationality = nationality;
        this.count = count;
    }

    public Stats() {

    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
