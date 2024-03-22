package org.example.playmysongs_backend.repository;

import org.example.playmysongs_backend.entity.Song;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongRepository {
    private final List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> getAllSongs() {
        return songs;
    }

    public List<Song> findSongs(String filter) {
        return songs.stream()
                .filter(song -> song.getName().toLowerCase().contains(filter.toLowerCase()) ||
                        song.getArtist().toLowerCase().contains(filter.toLowerCase()) ||
                        song.getStyle().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());
    }
}
