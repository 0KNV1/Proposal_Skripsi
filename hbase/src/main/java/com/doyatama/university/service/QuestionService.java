package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.Exam;
import com.doyatama.university.model.RPSDetail;
import com.doyatama.university.model.Question;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.QuestionRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.RPSDetailRepository;
import com.doyatama.university.repository.QuestionRepository;
import com.doyatama.university.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository = new QuestionRepository();
    private RPSDetailRepository rpsDetailRepository = new RPSDetailRepository();

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);


    public PagedResponse<Question> getAllQuestion(int page, int size, String rpsDetailID, String rpsID) throws IOException {
        validatePageNumberAndSize(page, size);

        List<Question> questionResponse = new ArrayList<>();

        // jika semua *
        if(rpsDetailID.equalsIgnoreCase("*") && rpsID.equalsIgnoreCase("*")) questionResponse = questionRepository.findAll(size);

        // jika rpsDetail ada isinya
        if(!rpsDetailID.equalsIgnoreCase("*") && rpsID.equalsIgnoreCase("*")) questionResponse = questionRepository.findAllByRPSDetail(rpsDetailID, size);

        // jika rps ada isinya
        if(rpsDetailID.equalsIgnoreCase("*") && !rpsID.equalsIgnoreCase("*")) questionResponse = questionRepository.findAllByRPS(rpsID, size);


        // Retrieve Polls

        return new PagedResponse<>(questionResponse, questionResponse.size(), "Successfully get data", 200);
    }

    public PagedResponse<Question> getAllQuestionsByRPS(int page, int size, String rpsID) throws IOException {
        validatePageNumberAndSize(page, size);

        List<Question> questionResponse = new ArrayList<>();

        // if rpsID is "*"
        if(rpsID.equalsIgnoreCase("*")) {
            questionResponse = questionRepository.findAll(size);
        }

        // if rpsID is not "*"
        if(!rpsID.equalsIgnoreCase("*")) {
            questionResponse = questionRepository.findAllByRPS(rpsID, size);
        }

        return new PagedResponse<>(questionResponse, questionResponse.size(), "Successfully get data", 200);
    }

    public Question createQuestion(QuestionRequest questionRequest, String savePath) throws IOException {
        Question question = new Question();
        RPSDetail rpsDetailResponse = rpsDetailRepository.findById(questionRequest.getRps_detail_id().toString());
        if (rpsDetailResponse.getSub_cp_mk() != null) {
            question.setTitle(questionRequest.getTitle());
            question.setDescription(questionRequest.getDescription());
            question.setQuestionType(Question.QuestionType.valueOf(questionRequest.getQuestion_type()));
            question.setAnswerType(Question.AnswerType.valueOf(questionRequest.getAnswer_type()));
            question.setFile_path(savePath.isEmpty() ? null : savePath);
            question.setRps_detail(rpsDetailResponse);
            return questionRepository.save(question);
        } else {
            return null;
        }
    }


    public DefaultResponse<Question> getQuestionById(String questionId) throws IOException {
        // Retrieve Question
        Question questionResponse = questionRepository.findById(questionId);
        return new DefaultResponse<>(questionResponse.isValid() ? questionResponse : null, questionResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public Question updateQuestion(String questionId, QuestionRequest questionRequest, String savePath) throws IOException {
        Question question = new Question();
        RPSDetail rpsDetailResponse = rpsDetailRepository.findById(questionRequest.getRps_detail_id().toString());
        if (rpsDetailResponse.getSub_cp_mk() != null) {
            question.setTitle(questionRequest.getTitle());
            question.setDescription(questionRequest.getDescription());
            question.setQuestionType(Question.QuestionType.valueOf(questionRequest.getQuestion_type()));
            question.setAnswerType(Question.AnswerType.valueOf(questionRequest.getAnswer_type()));
            question.setFile_path(savePath);
            question.setRps_detail(rpsDetailResponse);
            return questionRepository.update(questionId, question);
        } else {
            return null;
        }
    }

    public void deleteQuestionById(String rpsDetailId) throws IOException {
        Question questionResponse = questionRepository.findById(rpsDetailId);
        if(questionResponse.isValid()){
            questionRepository.deleteById(rpsDetailId);
        }else{
            throw new ResourceNotFoundException("RPSDetail", "id", rpsDetailId);
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