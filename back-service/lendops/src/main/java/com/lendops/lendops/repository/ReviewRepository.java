package com.lendops.lendops.repository;

import com.lendops.lendops.orm.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByMedia_Id(Long mediaId);
    List<Review> findByUser_Id(Long userId);
}