package com.roman.Insurance.ageCategories;

import com.roman.Insurance.ageCategories.response.AgeCategoryResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgeCategoryServiceImpl implements AgeCategoryService {
    private final AgeCategoryRepository ageCategoryRepository;
    private final AgeCategoryMapper ageCategoryMapper;

    @Override
    public List<AgeCategoryResponse> getAllAgeCategories () {
        return ageCategoryMapper.entityListToDto(ageCategoryRepository.findAll());
    }

    @Override
    public AgeCategoryResponse getAgeCategoryById (UUID id) {
        return ageCategoryMapper.toDto(ageCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Age category not found")));
    }

    @Override
    public AgeCategoryEntity getAgeCategoryEntityById (UUID id) {
        return ageCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Age category not found"));
    }
}
