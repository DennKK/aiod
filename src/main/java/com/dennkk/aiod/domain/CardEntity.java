package com.dennkk.aiod.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "card_table")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    public CardEntity(String name, String preview, String description, List<String> tags, String link, UserEntity author) {
        this.name = name;
        this.description = description;
        this.preview = preview;
        this.tags = tags;
        this.author = author;
        this.link = link;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
}