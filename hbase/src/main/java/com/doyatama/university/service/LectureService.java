package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.*;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.LectureRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.*;
import com.doyatama.university.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@Service
public class LectureService {
    private LectureRepository lectureRepository = new LectureRepository();
    private ReligionRepository religionRepository = new ReligionRepository();
    private StudyProgramRepository studyProgramRepository = new StudyProgramRepository();
    private UserRepository userRepository = new UserRepository();

    private static final Logger logger = LoggerFactory.getLogger(LectureService.class);


    public PagedResponse<Lecture> getAllLecture(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Lecture
        List<Lecture> lectureResponse = lectureRepository.findAll(size);
        return new PagedResponse<>(lectureResponse, lectureResponse.size(), "Successfully get data", 200);
    }

    public Lecture createLecture(LectureRequest lectureRequest) throws IOException {
        Lecture lecture = new Lecture();
        if(lectureRepository.existsByUserID(lectureRequest.getUser_id())) {
            return null;
        }
        Religion religionResponse = religionRepository.findById(lectureRequest.getReligion_id() != "" ? lectureRequest.getReligion_id() : "0");
        User userResponse = userRepository.findById(lectureRequest.getUser_id() != "" ? lectureRequest.getUser_id() : "0");
        StudyProgram studyProgramResponse = studyProgramRepository.findById(lectureRequest.getStudy_program_id() != "" ? lectureRequest.getStudy_program_id() : "0");

        if (religionResponse.getName() != null && userResponse.getName() != null && studyProgramResponse.getName() != null) {
            lecture.setNidn(lectureRequest.getNidn());
            lecture.setName(lectureRequest.getName());
            lecture.setPlace_born(lectureRequest.getPlace_born());
            lecture.setDate_born(lectureRequest.getDate_born());
            lecture.setGender(lectureRequest.getGender());
            lecture.setStatus(lectureRequest.getStatus());
            lecture.setPhone(lectureRequest.getPhone());
            lecture.setAddress(lectureRequest.getAddress());
            lecture.setReligion(religionResponse);
            lecture.setUser(userResponse);
            lecture.setStudyProgram(studyProgramResponse);
            return lectureRepository.save(lecture);
        } else {
            return null;
        }
    }

    public DefaultResponse<Lecture> getLectureById(String lectureId) throws IOException {
        // Retrieve Lecture
        Lecture lectureResponse = lectureRepository.findById(lectureId);
        return new DefaultResponse<>(lectureResponse.isValid() ? lectureResponse : null, lectureResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public Lecture updateLecture(String lectureId, LectureRequest lectureRequest) throws IOException {
        Lecture lecture = new Lecture();
        Religion religionResponse = religionRepository.findById(lectureRequest.getReligion_id() != "" ? lectureRequest.getReligion_id() : "0");
        User userResponse = userRepository.findById(lectureRequest.getUser_id() != "" ? lectureRequest.getUser_id() : "0");
        StudyProgram studyProgramResponse = studyProgramRepository.findById(lectureRequest.getStudy_program_id() != "" ? lectureRequest.getStudy_program_id() : "0");

        if (religionResponse.getName() != null && userResponse.getName() != null && studyProgramResponse.getName() != null) {
            lecture.setNidn(lectureRequest.getNidn());
            lecture.setName(lectureRequest.getName());
            lecture.setPlace_born(lectureRequest.getPlace_born());
            lecture.setDate_born(lectureRequest.getDate_born());
            lecture.setGender(lectureRequest.getGender());
            lecture.setStatus(lectureRequest.getStatus());
            lecture.setPhone(lectureRequest.getPhone());
            lecture.setAddress(lectureRequest.getAddress());
            lecture.setReligion(religionResponse);
            lecture.setUser(userResponse);
            lecture.setStudyProgram(studyProgramResponse);
            return lectureRepository.update(lectureId, lecture);
        } else {
            return null;
        }
    }

    public void deleteLectureById(String lectureId) throws IOException {
        Lecture lectureResponse = lectureRepository.findById(lectureId);
        if(lectureResponse.isValid()){
            lectureRepository.deleteById(lectureId);
        }else{
            throw new ResourceNotFoundException("Lecture", "id", lectureId);
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