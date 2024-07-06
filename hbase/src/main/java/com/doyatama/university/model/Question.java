package com.doyatama.university.model;


public class Question {
    private String id;
    private String title;
    private String description;
    private QuestionType question_type;
    private AnswerType answer_type;
    private String file_path;
    private RPSDetail rps_detail;

    public enum QuestionType {
        IMAGE,
        AUDIO,
        VIDEO,
        NORMAL,
    }

    public enum AnswerType {
        MULTIPLE_CHOICE,
        BOOLEAN,
        COMPLETION,

        ESSAY,
        MATCHING,
    }

    public Question() {
    }

    public Question(String id, String title, String description, QuestionType questionType, AnswerType answerType, String file_path, RPSDetail rps_detail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.question_type = questionType;
        this.answer_type = answerType;
        this.file_path = (file_path != null && !file_path.isEmpty()) ? file_path : null;
        this.rps_detail = rps_detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionType getQuestionType() {
        return question_type;
    }

    public void setQuestionType(QuestionType questionType) {
        this.question_type = questionType;
    }

    public AnswerType getAnswerType() {
        return answer_type;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answer_type = answerType;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public RPSDetail getRps_detail() {
        return rps_detail;
    }

    public void setRps_detail(RPSDetail rps_detail) {
        this.rps_detail = rps_detail;
    }

    public boolean isValid() {
        return this.id != null && this.title != null && this.description != null && this.question_type != null && this.answer_type != null ;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
            case "title":
                this.title = value;
                break;
            case "description":
                this.description = value;
                break;
            case "question_type":
                this.question_type = QuestionType.valueOf(value);
                break;
            case "answer_type":
                this.answer_type = AnswerType.valueOf(value);
                break;
            case "file_path":
                this.file_path = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}