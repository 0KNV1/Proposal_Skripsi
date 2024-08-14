package com.doyatama.university.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lecture {
    private String id;
    private String nidn;
    private String name;
    private String place_born;
    private String date_born;
    private String gender;
    private String status;
    private Religion religion;
    private User user;
    private StudyProgram study_program;
    private String address;
    private String phone;

    public Lecture() {
    }

    public Lecture(String id, String nidn, String name, String place_born, String date_born, String gender, String status, Religion religion, User user, StudyProgram study_program, String address, String phone) {
        this.id = id;
        this.nidn = nidn;
        this.name = name;
        this.place_born = place_born;
        this.date_born = date_born;
        this.gender = gender;
        this.status = status;
        this.religion = religion;
        this.user = user;
        this.study_program = study_program;
        this.address = address;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_born() {
        return place_born;
    }

    public void setPlace_born(String place_born) {
        this.place_born = place_born;
    }

    public String getDate_born() {
        return date_born;
    }

    public void setDate_born(String date_born) {
        this.date_born = date_born;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isValid() {
        return this.id != null && this.nidn != null && this.name != null && this.place_born != null && this.date_born != null && this.gender != null && this.status != null && this.address != null && this.phone != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
            case "nidn":
                this.nidn = value;
                break;
            case "name":
                this.name = value;
                break;
            case "place_born":
                this.place_born = value;
                break;
            case "date_born":
                this.date_born = value;
                break;
            case "gender":
                this.gender = value;
                break;
            case "status":
                this.status = value;
                break;
            case "address":
                this.address = value;
                break;
            case "phone":
                this.phone = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
