package com.dennkk.aiod.service.contracts;

import com.dennkk.aiod.domain.dto.CardDto;
import com.dennkk.aiod.domain.entity.CardEntity;
import com.dennkk.aiod.domain.entity.UserEntity;

import java.util.List;

public interface CardService {
    List<CardEntity> findAllCards();

    List<CardEntity> findCardsByTags(List<String> tags);

    CardEntity createCard(CardDto cardDto, UserEntity author);

    CardEntity findCardById(Long id);

    void likeCard(Long userId, Long cardId);
}
