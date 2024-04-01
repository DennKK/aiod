package com.dennkk.aiod.service.implementations;

import com.dennkk.aiod.domain.dto.CardDto;
import com.dennkk.aiod.domain.entity.CardEntity;
import com.dennkk.aiod.domain.entity.UserEntity;
import com.dennkk.aiod.domain.repos.CardRepo;
import com.dennkk.aiod.domain.repos.UserRepo;
import com.dennkk.aiod.service.contracts.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepo cardRepo;
    private final UserRepo userRepo;

    @Override
    public List<CardEntity> findAllCards() {
        Iterable<CardEntity> iterable = cardRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<CardEntity> findCardsByTags(List<String> tags) {
        return cardRepo.findByTagsIn(tags);
    }

    @Override
    public CardEntity createCard(CardDto cardDto, UserEntity author) {
        CardEntity newCard = new CardEntity(
                cardDto.getName(), cardDto.getPreview(), cardDto.getDescription(),
                Arrays.asList(cardDto.getTags().split(" ")), cardDto.getLink(), author,
                cardDto.getCategory(), cardDto.getPrice(), cardDto.getPlatform(),
                cardDto.getRecommendations(), cardDto.getInstructions()
        );
        return cardRepo.save(newCard);
    }

    @Override
    public CardEntity findCardById(Long id) {
        return cardRepo.findById(id).orElse(null);
    }

    @Override
    public void likeCard(Long userId, Long cardId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        CardEntity card = cardRepo.findById(cardId).orElseThrow(() -> new RuntimeException("Card not found"));
        boolean isAlreadyLiked = user.getLikedCards().contains(card);

        if (!isAlreadyLiked) {
            user.getLikedCards().add(card);
            card.getLikedUsers().add(user);
            userRepo.save(user);
        }
    }
}
