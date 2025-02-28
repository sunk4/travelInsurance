package com.roman.Insurance.ageCategories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgeCategoryRepository extends JpaRepository<AgeCategoryEntity, UUID> {
}
