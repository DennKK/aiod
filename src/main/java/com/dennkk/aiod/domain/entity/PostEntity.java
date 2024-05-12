package com.dennkk.aiod.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts_table")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity author;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    public PostEntity(String title, String content, List<String> tags,
                      UserEntity author, Date createdAt, Date updatedAt)
    {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
}