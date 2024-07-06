package com.doyatama.university.model;

import java.time.Instant;
import java.util.List;

public class RPSDetail {
    private String id;
    private Integer week;
    private RPS rps;
    private String sub_cp_mk;
    private List<String> learning_materials;
    private FormLearning form_learning;
    private List<LearningMethod> learning_methods;
    private List<String> assignments;
    private List<String> estimated_times;
    private List<String> student_learning_experiences;
    private List<AssessmentCriteria> assessment_criterias;
    private List<AppraisalForm> appraisal_forms;
    private List<String> assessment_indicators;
    private Float weight;
    private Instant created_at;
    private String weekLabel;

    public RPSDetail() {
    }

    public RPSDetail(String id, Integer week, RPS rps, String sub_cp_mk, List<String> learning_materials, FormLearning form_learning, List<LearningMethod> learning_methods, List<String> assignments, List<String> estimated_times, List<String> student_learning_experiences, List<AssessmentCriteria> assessment_criterias, List<AppraisalForm> appraisal_forms, List<String> assessment_indicators, Float weight,String weekLabel, Instant created_at) {
        this.id = id;
        this.week = week;
        this.rps = rps;
        this.sub_cp_mk = sub_cp_mk;
        this.learning_materials = learning_materials;
        this.form_learning = form_learning;
        this.learning_methods = learning_methods;
        this.assignments = assignments;
        this.estimated_times = estimated_times;
        this.student_learning_experiences = student_learning_experiences;
        this.assessment_criterias = assessment_criterias;
        this.appraisal_forms = appraisal_forms;
        this.assessment_indicators = assessment_indicators;
        this.weight = weight;
        this.weekLabel = weekLabel;
        this.created_at = created_at;
        
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeekLabel() {
        if (week == null) {
            return "No Week";
        } else if (week >= 1 && week <= 4) {
            return "quiz_1";
        } else if (week >= 5 && week <= 8) {
            return "quiz_2";
        } else {
            return "No Quiz";
        }
    }

    public void setWeekLabel(String weekLabel) {
        this.weekLabel = weekLabel;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }


    public RPS getRps() {
        return rps;
    }

    public void setRps(RPS rps) {
        this.rps = rps;
    }

    public String getSub_cp_mk() {
        return sub_cp_mk;
    }

    public void setSub_cp_mk(String sub_cp_mk) {
        this.sub_cp_mk = sub_cp_mk;
    }



    public List<String> getLearning_materials() {
        return learning_materials;
    }

    public void setLearning_materials(List<String> learning_materials) {
        this.learning_materials = learning_materials;
    }

    public FormLearning getForm_learning() {
        return form_learning;
    }

    public void setForm_learning(FormLearning form_learning) {
        this.form_learning = form_learning;
    }

    public List<LearningMethod> getLearning_methods() {
        return learning_methods;
    }

    public void setLearning_methods(List<LearningMethod> learning_methods) {
        this.learning_methods = learning_methods;
    }

    public List<String> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<String> assignments) {
        this.assignments = assignments;
    }

    public List<String> getEstimated_times() {
        return estimated_times;
    }

    public void setEstimated_times(List<String> estimated_times) {
        this.estimated_times = estimated_times;
    }

    public List<String> getStudent_learning_experiences() {
        return student_learning_experiences;
    }

    public void setStudent_learning_experiences(List<String> student_learning_experiences) {
        this.student_learning_experiences = student_learning_experiences;
    }

    public List<AssessmentCriteria> getAssessment_criterias() {
        return assessment_criterias;
    }

    public void setAssessment_criterias(List<AssessmentCriteria> assessment_criterias) {
        this.assessment_criterias = assessment_criterias;
    }

    public List<AppraisalForm> getAppraisal_forms() {
        return appraisal_forms;
    }

    public void setAppraisal_forms(List<AppraisalForm> appraisal_forms) {
        this.appraisal_forms = appraisal_forms;
    }

    public List<String> getAssessment_indicators() {
        return assessment_indicators;
    }

    public void setAssessment_indicators(List<String> assessment_indicators) {
        this.assessment_indicators = assessment_indicators;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
    
     


    public Instant getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Instant created_at) {
        this.created_at = created_at;
    }

    public boolean isValid() {
        return this.id != null &&
                this.weight != null;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "id":
                this.id = value;
                break;
            case "week":
                this.week = Integer.parseInt(value);
                break;
            case "weight":
                this.weight = Float.parseFloat(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
