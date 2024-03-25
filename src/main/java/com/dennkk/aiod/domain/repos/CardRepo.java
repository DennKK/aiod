package com.dennkk.aiod.domain.repos;

import com.dennkk.aiod.domain.CardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepo extends CrudRepository<CardEntity, Long> {
    List<CardEntity> findByTagsIn(List<String> tags);
}
