package com.dennkk.aiod.controllers;

import com.dennkk.aiod.domain.CardEntity;
import com.dennkk.aiod.domain.UserEntity;
import com.dennkk.aiod.domain.repos.CardRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class CardController {
    private final CardRepo cardRepo;

    public CardController(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    @GetMapping("/cards")
    public String cards(@RequestParam(required = false) String tags, Model model) {
        List<String> tagsList;
        Iterable<CardEntity> allPosts;

        if (tags != null && !tags.isEmpty()) {
            tagsList = Arrays.asList(tags.split(" "));
            allPosts = cardRepo.findByTagsIn(tagsList);
        } else {
            allPosts = cardRepo.findAll();
        }
        model.addAttribute("cards", allPosts);
        return "cards_view";
    }

    @GetMapping("/cards/create")
    public String createCard() {
        return "createCard_view";
    }

    @PostMapping("/cards/create")
    public String createCard(
            @AuthenticationPrincipal UserEntity author,
            @RequestParam String name, @RequestParam String preview,
            @RequestParam String description, @RequestParam String tags,
            @RequestParam String link, @RequestParam String category,
            @RequestParam Long price, @RequestParam String platform,
            @RequestParam String recommendations, @RequestParam String instructions
    ) {
        List<String> tagsList = Arrays.asList(tags.split(" "));

        CardEntity newCard = new CardEntity(
                name, preview, description,
                tagsList, link, author,
                category, price, platform,
                recommendations, instructions
        );

        cardRepo.save(newCard);
        return "redirect:/cards";
    }

    @GetMapping("/card/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        CardEntity card = cardRepo.findById(id).orElse(null);
        if (card != null) {
            model.addAttribute("card", card);
            return "cardProfile_view";
        }
        return "cards_view";
    }
}
