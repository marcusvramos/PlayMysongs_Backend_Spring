package org.example.playmysongs_backend.controller;

import org.example.playmysongs_backend.entity.Song;
import org.example.playmysongs_backend.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadSong(
            @RequestParam("name") String name,
            @RequestParam("style") String style,
            @RequestParam("artist") String artist,
            @RequestParam("file") MultipartFile file) {

        name = name.replaceAll("\\s+", "");
        artist = artist.replaceAll("\\s+", "");
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcherName = pattern.matcher(name);
        Matcher matcherArtist = pattern.matcher(artist);

        if (matcherName.matches() && matcherArtist.matches()) {
            String filename = name + "_" + style + "_" + artist + ".mp3";

            songService.uploadSong(new Song(name, style, artist, "/musics/" + filename));

            Path root = Paths.get("src/main/resources/static/musics");
            try {
                if (!Files.exists(root)) {
                    Files.createDirectories(root);
                }
                Files.copy(file.getInputStream(), root.resolve(filename));
            } catch (IOException e) {
                return ResponseEntity.badRequest().body("Erro ao salvar o arquivo: " + e.getMessage());
            }
            return ResponseEntity.ok("Música adicionada com sucesso");
        }
        return ResponseEntity.badRequest().body("Falha ao adicionar música. Verifique o nome e o artista.");
    }

    @GetMapping
    public ResponseEntity<?> getSongs(@RequestParam(value = "filter", required = false) String filter) {
        if (filter != null && !filter.isEmpty()) {
            return ResponseEntity.ok(songService.searchSongs(filter).stream().map(song -> Map.of(
                    "name", song.getName(),
                    "style", song.getStyle(),
                    "artist", song.getArtist(),
                    "url", song.getUrl()
            )).collect(Collectors.toList()));
        } else {
            return ResponseEntity.ok(songService.getAllSongs().stream().map(song -> Map.of(
                    "name", song.getName(),
                    "style", song.getStyle(),
                    "artist", song.getArtist(),
                    "url", song.getUrl()
            )).collect(Collectors.toList()));
        }
    }
}
