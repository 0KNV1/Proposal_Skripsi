package com.doyatama.university.controller;

import com.doyatama.university.model.StudyProgram;
import com.doyatama.university.model.Quiz;
import com.doyatama.university.payload.ApiResponse;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.QuizRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.service.QuizService;
import com.doyatama.university.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private QuizService quizService = new QuizService();

    @GetMapping
    public PagedResponse<Quiz> getQuiz(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
        return quizService.getAllQuiz(page, size);
    }

    @PostMapping
    public ResponseEntity<?> createQuiz(@Valid @RequestBody QuizRequest quizRequest) throws IOException {
        Quiz quiz = quizService.createQuiz(quizRequest);

        if(quiz == null){
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Please check relational ID"));
        }else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{quizId}")
                    .buildAndExpand(quiz.getId()).toUri();

            return ResponseEntity.created(location)
                    .body(new ApiResponse(true, "Quiz Created Successfully"));
        }
    }

    @GetMapping("/{quizId}")
    public DefaultResponse<Quiz> getQuizById(@PathVariable String quizId) throws IOException {
        return quizService.getQuizById(quizId);
    }


    @PutMapping("/{quizId}")
    public ResponseEntity<?> updateQuiz(@PathVariable String quizId,
                                            @Valid @RequestBody QuizRequest quizRequest) throws IOException {
        Quiz quiz = quizService.updateQuiz(quizId, quizRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{quizId}")
                .buildAndExpand(quiz.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Quiz Updated Successfully"));
    }

    @DeleteMapping("/{quizId}")
    public HttpStatus deleteQuiz(@PathVariable (value = "quizId") String quizId) throws IOException {
        quizService.deleteQuizById(quizId);
        return HttpStatus.FORBIDDEN;
    }
}
