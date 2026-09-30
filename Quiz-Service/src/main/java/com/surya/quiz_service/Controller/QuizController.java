package com.surya.quiz_service.Controller;

import com.surya.quiz_service.Model.QuestionWrapper;
import com.surya.quiz_service.Model.QuizDto;
import com.surya.quiz_service.Model.Response;
import com.surya.quiz_service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQ(),quizDto.getTitle());
    }

    @GetMapping("get/{id}")
   public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

   @PostMapping("submit/{id}")
   public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
   }
}
