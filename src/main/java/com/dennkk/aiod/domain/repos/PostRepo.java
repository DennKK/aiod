package com.dennkk.aiod.domain.repos;

import com.dennkk.aiod.domain.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends CrudRepository<PostEntity, Long> {
    List<PostEntity> findByTagsIn(List<String> tags);
}
