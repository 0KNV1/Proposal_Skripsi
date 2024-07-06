package com.doyatama.university.model;

import java.time.LocalDate;

public class Student {
    private String id;
    private String nim;
    private String name;
    private String gender;
    private String phone;
    private Religion religion;
    private User user;
    private StudyProgram study_program;
    private String birth_date;
    private String place_born;
    private String address;

    public Student() {
    }

    public Student(String id, String nim, String name, String gender, String phone, Religion religion, User user, StudyProgram study_program, String birth_date, String place_born, String address) {
        this.id = id;
        this.nim = nim;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.religion = religion;
        this.user = user;
        this.study_program = study_program;
        this.birth_date = birth_date;
        this.place_born = place_born;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StudyProgram getStudyProgram() {
        return study_program;
    }

    public void setStudyProgram(StudyProgram study_program) {
        this.study_program = study_program;
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

    public boolean isValid() {
        return this.id != null &&
                this.nim != null &&
                this.name != null &&
                this.gender != null &&
                this.phone != null &&
                this.religion != null &&
                this.user != null &&
                this.study_program != null &&
                this.birth_date != null &&
                this.place_born != null &&
                this.address != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
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
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
