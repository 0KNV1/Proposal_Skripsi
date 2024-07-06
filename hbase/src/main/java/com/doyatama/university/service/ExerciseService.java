package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.*;
import com.doyatama.university.model.Exercise;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.ExerciseRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.*;
import com.doyatama.university.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ExerciseService {
    private ExerciseRepository exerciseRepository = new ExerciseRepository();
    private QuestionRepository questionRepository = new QuestionRepository();
    private RPSRepository rpsRepository = new RPSRepository();


    private static final Logger logger = LoggerFactory.getLogger(ExerciseService.class);

    public PagedResponse<Exercise> getAllExercise(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<Exercise> exerciseResponse = exerciseRepository.findAll(size);


        return new PagedResponse<>(exerciseResponse, exerciseResponse.size(), "Successfully get data", 200);
    }

    public Exercise createExercise(ExerciseRequest exerciseRequest) throws IOException {
        Exercise exercise = new Exercise();

        List<Question> questionList = questionRepository.findAllById(exerciseRequest.getQuestions());
        RPS rpsResponse = rpsRepository.findById(exerciseRequest.getRps_id());

        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        if (questionList.size() != 0 && rpsResponse.getName() != null) {

            exercise.setName(exerciseRequest.getName());
            exercise.setDescription(exerciseRequest.getDescription());
            exercise.setMin_grade(exerciseRequest.getMin_grade());
            exercise.setDuration(exerciseRequest.getDuration());
            exercise.setDate_start(exerciseRequest.getDate_start());
            exercise.setDate_end(exerciseRequest.getDate_end());
            exercise.setQuestions(questionList);
            exercise.setRps(rpsResponse);
            exercise.setCreated_at(instant);

            return exerciseRepository.save(exercise);
        } else {
            return null;
        }
    }

    public DefaultResponse<Exercise> getExerciseById(String exerciseId) throws IOException {
        // Retrieve Exercise
        Exercise exerciseResponse = exerciseRepository.findById(exerciseId);
        return new DefaultResponse<>(exerciseResponse.isValid() ? exerciseResponse : null, exerciseResponse.isValid() ? 1 : 0, "Successfully get data");
    }



    public Exercise updateExercise(String exerciseId, ExerciseRequest exerciseRequest) throws IOException {
        Exercise exercise = new Exercise();
        List<Question> questionList = questionRepository.findAllById(exerciseRequest.getQuestions());
        RPS rpsResponse = rpsRepository.findById(exerciseRequest.getRps_id());

        if (questionList.size() != 0 && rpsResponse.getName() != null) {
            exercise.setName(exerciseRequest.getName());
            exercise.setDescription(exerciseRequest.getDescription());
            exercise.setMin_grade(exerciseRequest.getMin_grade());
            exercise.setDuration(exerciseRequest.getDuration());
            exercise.setDate_start(exerciseRequest.getDate_start());
            exercise.setDate_end(exerciseRequest.getDate_end());
            exercise.setQuestions(questionList);
            exercise.setRps(rpsResponse);
            return exerciseRepository.update(exerciseId, exercise);
        } else {
            return null;
        }
    }

    public void deleteExerciseById(String exerciseId) throws IOException {
        Exercise exerciseResponse = exerciseRepository.findById(exerciseId);
        if(exerciseResponse.isValid()){
            exerciseRepository.deleteById(exerciseId);
        }else{
            throw new ResourceNotFoundException("Exercise", "id", exerciseId);
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
