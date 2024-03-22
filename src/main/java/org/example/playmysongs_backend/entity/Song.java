package org.example.playmysongs_backend.entity;

public class Song {
    private String name;
    private String style;
    private String artist;
    private String filePath;

    public Song() {
    }

    public Song(String name, String style, String artist, String filePath) {
        this.name = name;
        this.style = style;
        this.artist = artist;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public String getArtist() {
        return artist;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
