package com.dennkk.aiod.controllers;

import com.dennkk.aiod.domain.dto.CardDto;
import com.dennkk.aiod.domain.entity.CardEntity;
import com.dennkk.aiod.domain.entity.UserEntity;
import com.dennkk.aiod.service.contracts.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public String cards(@RequestParam(required = false) String tags, Model model) {
        List<CardEntity> allCards;
        if (tags != null && !tags.isEmpty()) {
            List<String> tagsList = Arrays.asList(tags.split(" "));
            allCards = cardService.findCardsByTags(tagsList);
        } else {
            allCards = cardService.findAllCards();
        }
        model.addAttribute("cards", allCards);
        return "cards_view";
    }

    @GetMapping("/create")
    public String createCard() {
        return "createCard_view";
    }

    @PostMapping("create")
    public String createCard(@AuthenticationPrincipal UserEntity author, @ModelAttribute CardDto cardDto) {
        cardService.createCard(cardDto, author);
        return "redirect:/cards";
    }

    @GetMapping("/{id}")
    public String getCard(@PathVariable Long id, Model model) {
        CardEntity card = cardService.findCardById(id);
        if (card != null) {
            model.addAttribute("card", card);
            return "cardProfile_view";
        }
        return "redirect:/cards";
    }

    @GetMapping("/like-card")
    public String likeCard(@AuthenticationPrincipal UserEntity user, @RequestParam("cardId") Long cardId, RedirectAttributes redirectAttributes) {
        try {
            cardService.likeCard(user.getId(), cardId);
            redirectAttributes.addFlashAttribute("successMessage", "Card liked successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error liking card: " + e.getMessage());
        }
        return "redirect:/cards/" + cardId;
    }
}
