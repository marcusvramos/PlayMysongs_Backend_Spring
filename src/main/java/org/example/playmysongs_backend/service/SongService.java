package org.example.playmysongs_backend.service;

import org.example.playmysongs_backend.entity.Song;
import org.example.playmysongs_backend.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository = new SongRepository();

    public void uploadSong(Song song) {
        songRepository.addSong(song);
    }

    public List<Song> getAllSongs() {
        return songRepository.getAllSongs();
    }

    public List<Song> searchSongs(String filter) {
        return songRepository.findSongs(filter);
    }
}
