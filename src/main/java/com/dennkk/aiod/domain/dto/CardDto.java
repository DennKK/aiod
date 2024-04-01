package com.dennkk.aiod.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CardDto {
    private String name;
    private String preview;
    private String description;
    private String tags;
    private String link;
    private String category;
    private Long price;
    private String platform;
    private String recommendations;
    private String instructions;
}