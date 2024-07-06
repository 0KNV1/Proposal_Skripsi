package com.doyatama.university.service;

import com.doyatama.university.exception.BadRequestException;
import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.*;
import com.doyatama.university.model.Student;
import com.doyatama.university.payload.DefaultResponse;
import com.doyatama.university.payload.StudentRequest;
import com.doyatama.university.payload.StudentRequest;
import com.doyatama.university.payload.PagedResponse;
import com.doyatama.university.repository.*;
import com.doyatama.university.repository.StudentRepository;
import com.doyatama.university.util.AppConstants;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();
    private ReligionRepository religionRepository = new ReligionRepository();
    private StudyProgramRepository studyProgramRepository = new StudyProgramRepository();
    private UserRepository userRepository = new UserRepository();

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);


    public PagedResponse<Student> getAllStudent(int page, int size, String userID) throws IOException {
        validatePageNumberAndSize(page, size);
        List<Student> studentResponse = new ArrayList<>();

        if(userID.equalsIgnoreCase("*")) studentResponse = studentRepository.findAll(size);
        if(!userID.equalsIgnoreCase("*")) studentResponse = studentRepository.findAllByUserID(userID, size);

        return new PagedResponse<>(studentResponse, studentResponse.size(), "Successfully get data", 200);
    }

    public Student createStudent(StudentRequest studentRequest) throws IOException {
        Student student = new Student();
        if(studentRepository.existsByUserID(studentRequest.getUser_id())) {
            return null;
        }
        Religion religionResponse = religionRepository.findById(studentRequest.getReligion_id() != "" ? studentRequest.getReligion_id() : "0");
        User userResponse = userRepository.findById(studentRequest.getUser_id() != "" ? studentRequest.getUser_id() : "0");
        StudyProgram studyProgramResponse = studyProgramRepository.findById(studentRequest.getStudy_program_id() != "" ? studentRequest.getStudy_program_id() : "0");

        if (religionResponse.getName() != null && userResponse.getName() != null && studyProgramResponse.getName() != null) {
            student.setNim(studentRequest.getNim());
            student.setName(studentRequest.getName());
            student.setPlace_born(studentRequest.getPlace_born());
            student.setBirth_date(studentRequest.getBirth_date());
            student.setGender(studentRequest.getGender());
            student.setPhone(studentRequest.getPhone());
            student.setAddress(studentRequest.getAddress());
            student.setReligion(religionResponse);
            student.setUser(userResponse);
            student.setStudyProgram(studyProgramResponse);
            return studentRepository.save(student);
        } else {
            return null;
        }
    }

    public DefaultResponse<Student> getStudentById(String studentId) throws IOException {
        // Retrieve Student
        Student studentResponse = studentRepository.findById(studentId);
        return new DefaultResponse<>(studentResponse.isValid() ? studentResponse : null, studentResponse.isValid() ? 1 : 0, "Successfully get data");
    }

    public Student updateStudent(String studentId, StudentRequest studentRequest) throws IOException {
        Student student = new Student();
        Religion religionResponse = religionRepository.findById(studentRequest.getReligion_id() != "" ? studentRequest.getReligion_id() : "0");
        User userResponse = userRepository.findById(studentRequest.getUser_id() != "" ? studentRequest.getUser_id() : "0");
        StudyProgram studyProgramResponse = studyProgramRepository.findById(studentRequest.getStudy_program_id() != "" ? studentRequest.getStudy_program_id() : "0");

        if (religionResponse.getName() != null && userResponse.getName() != null && studyProgramResponse.getName() != null) {
            student.setNim(studentRequest.getNim());
            student.setNim(studentRequest.getNim());
            student.setName(studentRequest.getName());
            student.setPlace_born(studentRequest.getPlace_born());
            student.setBirth_date(studentRequest.getBirth_date());
            student.setGender(studentRequest.getGender());
            student.setPhone(studentRequest.getPhone());
            student.setAddress(studentRequest.getAddress());
            student.setReligion(religionResponse);
            student.setUser(userResponse);
            student.setStudyProgram(studyProgramResponse);
            return studentRepository.update(studentId, student);
        } else {
            return null;
        }
    }

    public void deleteStudentById(String studentId) throws IOException {
        Student studentResponse = studentRepository.findById(studentId);
        if(studentResponse.isValid()){
            studentRepository.deleteById(studentId);
        }else{
            throw new ResourceNotFoundException("Student", "id", studentId);
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
