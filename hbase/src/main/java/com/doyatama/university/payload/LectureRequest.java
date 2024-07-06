package com.doyatama.university.payload;

import com.doyatama.university.model.Religion;

public class LectureRequest {
    private String nidn;
    private String name;
    private String place_born;
    private String date_born;
    private String gender;
    private String status;
    private String religion_id;
    private String user_id;
    private String study_program_id;
    private String address;
    private String phone;

    public LectureRequest() {
    }

    public LectureRequest(String nidn, String name, String place_born, String date_born, String gender, String status, String religion_id, String user_id, String study_program_id, String address, String phone) {
        this.nidn = nidn;
        this.name = name;
        this.place_born = place_born;
        this.date_born = date_born;
        this.gender = gender;
        this.status = status;
        this.religion_id = religion_id;
        this.user_id = user_id;
        this.study_program_id = study_program_id;
        this.address = address;
        this.phone = phone;
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

    public void set(String fieldName, String value) {
        switch (fieldName) {
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
            case "religion_id":
                this.religion_id = value;
                break;
            case "user_id":
                this.religion_id = value;
                break;
            case "study_program_id":
                this.study_program_id = value;
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
