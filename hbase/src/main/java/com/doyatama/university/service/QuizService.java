package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.*;
import com.doyatama.university.model.Quiz;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.QuizRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.*;
import com.doyatama.university.util.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class QuizService {
    private QuizRepository quizRepository = new QuizRepository();
    private QuestionRepository questionRepository = new QuestionRepository();
    private RPSRepository rpsRepository = new RPSRepository();
    private String lastSavedRowKey;


    @Autowired
    public QuizService(QuizRepository quizRepository, RPSRepository rpsRepository) {
        this.quizRepository = quizRepository;
        this.rpsRepository = rpsRepository;
    }
    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

    public PagedResponse<Quiz> getAllQuiz(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<Quiz> quizResponse = quizRepository.findAll(size);


        return new PagedResponse<>(quizResponse, quizResponse.size(), "Successfully get data", 200);
    }



    public Quiz createQuiz(QuizRequest quizRequest) throws IOException {
        Quiz quiz = new Quiz();

        RPS rpsResponse = rpsRepository.findById(quizRequest.getRps_id());
        List<String> devLecturerIds = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        if (rpsResponse.getDev_lecturers() != null) {
            for (Object object : rpsResponse.getDev_lecturers()) {
                if (object instanceof LinkedHashMap) {
                    LinkedHashMap map = (LinkedHashMap) object;
                    Lecture lecture = mapper.convertValue(map, Lecture.class);
                    String lectureId = lecture.getId();
                    if (lectureId != null) {
                        devLecturerIds.add(lectureId);
                    }
                }
            }
        }

        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        if ( rpsResponse.getName() != null) {
            quiz.setName(quizRequest.getName());
            quiz.setDescription(quizRequest.getDescription());
            quiz.setMin_grade(quizRequest.getMin_grade());
            quiz.setDuration(quizRequest.getDuration());
            quiz.setDate_start(quizRequest.getDate_start());
            quiz.setDate_end(quizRequest.getDate_end());
            quiz.setDevLecturerIds(devLecturerIds);
            quiz.setRps(rpsResponse);
            quiz.setMessage(quizRequest.getMessage());
            quiz.setType_quiz(quizRequest.getType_quiz());
            quiz.setCreated_at(instant);
            
            return quizRepository.save(quiz);
//            lastSavedRowKey = quizRepository.save(quiz);
    
        } else {
            return null;
        }
    }

    public Quiz saveQuizWithQuestions(Quiz quiz) throws IOException {
        // Get the last saved rowKey from QuizRepository
        String rowKey = quizRepository.getLastSavedRowKey();

        // Save the quiz with questions using the rowKey
        quizRepository.saveWithQuestions(quiz, rowKey);

        return quiz;
    }


    public DefaultResponse<Quiz> getQuizById(String quizId) throws IOException {
        // Retrieve Quiz
        Quiz quizResponse = quizRepository.findById(quizId);
        return new DefaultResponse<>(quizResponse.isValid() ? quizResponse : null, quizResponse.isValid() ? 1 : 0, "Successfully get data");
    }



    public Quiz updateQuiz(String quizId, QuizRequest quizRequest) throws IOException {
        Quiz quiz = new Quiz();
        List<Question> questionList = questionRepository.findAllById(quizRequest.getQuestions());
        RPS rpsResponse = rpsRepository.findById(quizRequest.getRps_id());

        if (questionList.size() != 0 && rpsResponse.getName() != null) {
            quiz.setName(quizRequest.getName());
            quiz.setDescription(quizRequest.getDescription());
            quiz.setMin_grade(quizRequest.getMin_grade());
            quiz.setDuration(quizRequest.getDuration());
            quiz.setDate_start(quizRequest.getDate_start());
            quiz.setDate_end(quizRequest.getDate_end());
            quiz.setQuestions(questionList);
            quiz.setRps(rpsResponse);
            return quizRepository.update(quizId, quiz);
        } else {
            return null;
        }
    }

    public void deleteQuizById(String quizId) throws IOException {
        Quiz quizResponse = quizRepository.findById(quizId);
        if(quizResponse.isValid()){
            quizRepository.deleteById(quizId);
        }else{
            throw new ResourceNotFoundException("Quiz", "id", quizId);
        }
    }

    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

}
