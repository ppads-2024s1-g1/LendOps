package com.lendops.lendops.controller;

import com.lendops.lendops.orm.Media;
import com.lendops.lendops.orm.User;
import com.lendops.lendops.service.MediaService;
import com.lendops.lendops.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/medias")
public class MediaController {

    private final MediaService mediaService;
    private final UserService userService;

    @PostMapping("/insert")
    public ResponseEntity<Media> insertMedia(@RequestParam String type, @RequestParam String mediaApiId, @RequestParam Long userId) {
        if (!isValidMediaType(type)) {
            throw new IllegalArgumentException("Invalid media type. Only 'movie', 'series', or 'book' are allowed.");
        }
        User user = userService.findUserById(userId);
        Media media = mediaService.insertMedia(type, mediaApiId, user);
        return new ResponseEntity<>(media, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMediaById(@PathVariable Long id) {
        mediaService.deleteMediaById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> getMediaByMediaId(@PathVariable Long id) {
        Media media = mediaService.getMediaById(id);
        return new ResponseEntity<>(media, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Media>> getMediasByType(@PathVariable String type) {
        List<Media> medias = mediaService.getMediasByType(type);
        return new ResponseEntity<>(medias, HttpStatus.OK);
    }

    private boolean isValidMediaType(String type) {
        return type.equals("movie") || type.equals("series") || type.equals("book");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Media>> getAllMedias() {
        List<Media> medias = mediaService.getAllMedia();
        return new ResponseEntity<>(medias, HttpStatus.OK);
    }
}