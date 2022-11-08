package com.eminimal.backend.repository;

import com.eminimal.backend.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, String> {
}