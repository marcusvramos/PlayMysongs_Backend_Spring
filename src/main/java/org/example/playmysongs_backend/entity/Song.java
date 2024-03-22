package org.example.playmysongs_backend.entity;

public class Song {
    private String name;
    private String style;
    private String artist;
    private String filePath;
    private String url;

    public Song(String name, String style, String artist, String filePath) {
        this.name = name;
        this.style = style;
        this.artist = artist;
        this.filePath = filePath;
        this.url = "http://localhost:8080" + filePath;
    }

    public String getUrl() {
        return url;
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
}
