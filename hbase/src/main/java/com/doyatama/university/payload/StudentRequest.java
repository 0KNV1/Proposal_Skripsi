package com.doyatama.university.payload;

import com.doyatama.university.model.Religion;
import com.doyatama.university.model.StudyProgram;
import com.doyatama.university.model.User;

import java.time.LocalDate;

public class StudentRequest {
    private String nim;
    private String name;
    private String gender;
    private String phone;
    private String religion_id;
    private String user_id;
    private String study_program_id;
    private String birth_date;
    private String place_born;
    private String address;

    public StudentRequest() {
    }

    public StudentRequest(String nim, String name, String gender, String phone, String religion_id, String user_id, String study_program_id, String birth_date, String place_born, String address) {
        this.nim = nim;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.religion_id = religion_id;
        this.user_id = user_id;
        this.study_program_id = study_program_id;
        this.birth_date = birth_date;
        this.place_born = place_born;
        this.address = address;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReligion_id() {
        return religion_id;
    }

    public void setReligion_id(String religion_id) {
        this.religion_id = religion_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStudy_program_id() {
        return study_program_id;
    }

    public void setStudy_program_id(String study_program_id) {
        this.study_program_id = study_program_id;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPlace_born() {
        return place_born;
    }

    public void setPlace_born(String place_born) {
        this.place_born = place_born;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "nim":
                this.nim = value;
                break;
            case "name":
                this.name = value;
                break;
            case "gender":
                this.gender = value;
                break;
            case "phone":
                this.phone = value;
                break;
            case "birth_date":
                this.birth_date = value;
                break;
            case "place_born":
                this.place_born = value;
                break;
            case "address":
                this.address = value;
                break;
            case "user_id":
                this.user_id = value;
                break;
            case "religion_id":
                this.religion_id = value;
                break;
            case "study_program_id":
                this.study_program_id = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
