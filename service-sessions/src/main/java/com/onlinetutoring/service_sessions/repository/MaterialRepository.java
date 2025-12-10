package com.onlinetutoring.service_sessions.repository;

import com.onlinetutoring.service_sessions.domain.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MaterialRepository extends JpaRepository<Material, Long> {

    Optional<Material> findByName(String name);

    boolean existsByName(String name);

    Page<Material> findAllByNameContainingIgnoreCase(String namePart, Pageable pageable);

    Page<Material> findAllByTypeIgnoreCase(String type, Pageable pageable);

    Page<Material> findAllBySessions_Id(Long sessionId, Pageable pageable);

    long countByIdIn(List<Long> ids);
}

