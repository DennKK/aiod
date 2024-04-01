package com.dennkk.aiod.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card_table")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "card_id")
    private Long id;
    private String name;
    private String preview;
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags;
    private String link;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity author;
    private String category;
    private Long price;
    private String platform;
    private String recommendations;
    private String instructions;
    @ManyToMany(mappedBy = "likedCards")
    private Set<UserEntity> likedUsers = new HashSet<>();

    public CardEntity(
            String name, String preview, String description,
            List<String> tags, String link, UserEntity author,
            String category, Long price, String platform,
            String recommendations, String instructions
    ) {
        this.name = name;
        this.description = description;
        this.preview = preview;
        this.tags = tags;
        this.author = author;
        this.link = link;
        this.category = category;
        this.price = price;
        this.platform = platform;
        this.recommendations = recommendations;
        this.instructions = instructions;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
}
