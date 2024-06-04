package com.lendops.lendops.repository;

import com.lendops.lendops.orm.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {
       List<Media> findByType(String type);
}
