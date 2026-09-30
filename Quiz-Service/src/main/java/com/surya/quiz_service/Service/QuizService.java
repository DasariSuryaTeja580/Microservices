package com.surya.quiz_service.Service;
import com.netflix.discovery.converters.Auto;
import com.surya.quiz_service.Feign.QuizInterface;
import com.surya.quiz_service.Model.Question;
import com.surya.quiz_service.Model.QuestionWrapper;
import com.surya.quiz_service.Model.Quiz;
import com.surya.quiz_service.Model.Response;
import com.surya.quiz_service.dao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

//    @Autowired
//    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        //call the generate URL -RestTemplate http://localhost:8080/question/generate
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionsIds = quiz.getQuestionsIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionById(questionsIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        ResponseEntity<Integer> score=quizInterface.getScore(responses);
        return score;
   }
}
