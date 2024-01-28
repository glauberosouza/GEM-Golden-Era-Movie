package com.movie.golden.data.repository.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePaginatedRepository  extends JpaRepository<MovieEntity, Long> {
    Page<MovieEntity> findAll(Pageable pageable);
}
