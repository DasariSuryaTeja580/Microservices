package com.surya.quiz_service.Model;

import lombok.Data;

@Data
public class QuizDto {
    private String categoryName;
    private Integer numQ;
    private String title;
}
