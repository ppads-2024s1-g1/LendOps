package com.lendops.lendops.service;

import com.lendops.lendops.orm.Media;
import com.lendops.lendops.orm.User;
import com.lendops.lendops.repository.MediaRepository;
import com.lendops.lendops.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class MediaService {

    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;

    @Transactional
    public Media insertMedia(String type, String mediaId, User user) {

            Media media = Media.builder()
                    .type(type)
                    .mediaId(mediaId)
                    .users(Collections.emptyList())
                    .reviews(Collections.emptyList())
                    .build();
            // Save the media
            Media mediaSaved = mediaRepository.save(media);
            user.getMedias().add(media);
            userRepository.save(user);
            return mediaSaved;
    }

    public void deleteMediaById(Long id) {
        try {
            Optional<Media> mediaOptional = mediaRepository.findById(id);
            if (mediaOptional.isPresent()) {
                mediaRepository.delete(mediaOptional.get());
            } else {
                throw new RuntimeException("Media not found with mediaId: " + id);
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("Error deleting media", e) {
            };
        }
    }

    public Media getMediaById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found with mediaId: " + id));
    }

    public List<Media> getMediasByType(String type) {
        return mediaRepository.findByType(type);
    }

    public List<Media> getAllMedia() {
        Iterable<Media> userIterable = mediaRepository.findAll();
        return StreamSupport.stream(userIterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}