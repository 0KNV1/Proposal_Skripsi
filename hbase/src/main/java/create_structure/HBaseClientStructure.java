package create_structure;

import com.github.javafaker.Faker;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class HBaseClientStructure {

    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();
        HBaseCustomClient client = new HBaseCustomClient(conf);

        // ==============================================================================================
        // CREATE COLLECTION
        // ==============================================================================================



        // Create Tabel Mata Kuliah
        TableName tableSubject = TableName.valueOf("subjects");
        String[] subjects = { "main", "study_program", "subject_group", "detail" };
        client.deleteTable(tableSubject);
        client.createTable(tableSubject, subjects);

        // Create Tabel Bab
        TableName tableChapter = TableName.valueOf("chapters");
        String[] chapters = { "main", "subject", "detail" };
        client.deleteTable(tableChapter);
        client.createTable(tableChapter, chapters);

        // Create Tabel Rumpun Mata Kuliah
        TableName tableSubjectGroup = TableName.valueOf("subject_groups");
        String[] subjectGroups = { "main", "detail" };
        client.deleteTable(tableSubjectGroup);
        client.createTable(tableSubjectGroup, subjectGroups);

        // Create Tabel Dosen
        TableName tableLecture= TableName.valueOf("lectures");
        String[] lectures = { "main", "study_program", "religion", "user", "detail" };
        client.deleteTable(tableLecture);
        client.createTable(tableLecture, lectures);

        // Create Tabel Mahasiswa
        TableName tableStudent = TableName.valueOf("students");
        String[] students = { "main", "study_program", "religion", "user", "detail" };
        client.deleteTable(tableStudent);
        client.createTable(tableStudent, students);

        // Create Tabel RPS
        TableName tableRPS = TableName.valueOf("rps");
        String[] RPS = { "main", "learning_media_softwares", "learning_media_hardwares", "requirement_subjects", "study_program", "subject", "dev_lecturers", "teaching_lecturers", "coordinator_lecturers", "ka_study_program", "detail" };
        client.deleteTable(tableRPS);
        client.createTable(tableRPS, RPS);

        // Create Tabel Detail RPS
        TableName tableRPSDetail = TableName.valueOf("rps_details");
        String[] RPSDetails = { "main", "rps", "learning_materials", "form_learning", "learning_methods", "assignments", "estimated_times", "student_learning_experiences", "assessment_criterias", "appraisal_forms", "assessment_indicators", "detail" };
        client.deleteTable(tableRPSDetail);
        client.createTable(tableRPSDetail, RPSDetails);

        // Create Table Pustaka
        TableName tableReference = TableName.valueOf("references");
        String[] references = { "main", "detail" };
        client.deleteTable(tableReference);
        client.createTable(tableReference, references);

        // Create Table Media Pembelajaran
        TableName tableLearningMedia = TableName.valueOf("learning_medias");
        String[] learningMedias = { "main", "detail" };
        client.deleteTable(tableLearningMedia);
        client.createTable(tableLearningMedia, learningMedias);

        // Create Table Agama
        TableName tableReligion = TableName.valueOf("religions");
        String[] religions = { "main", "detail" };
        client.deleteTable(tableReligion);
        client.createTable(tableReligion, religions);

        // Create Table Jurusan
        TableName tableDepartment = TableName.valueOf("departments");
        String[] departments = { "main", "detail" };
        client.deleteTable(tableDepartment);
        client.createTable(tableDepartment, departments);

        // Create Table Prodi
        TableName tableStudyProgram = TableName.valueOf("study_programs");
        String[] studyPrograms = { "main", "department", "detail" };
        client.deleteTable(tableStudyProgram);
        client.createTable(tableStudyProgram, studyPrograms);

        // Create Table Users
        TableName tableUser = TableName.valueOf("users");
        String[] users = { "main", "detail" };
        client.deleteTable(tableUser);
        client.createTable(tableUser, users);

        // Create Table Bentuk Penilaian
        TableName tableAppraisalForm = TableName.valueOf("appraisal_forms");
        String[] appraisalForms = { "main", "detail" };
        client.deleteTable(tableAppraisalForm);
        client.createTable(tableAppraisalForm, appraisalForms);

        // Create Tabel Kriteria Penilaian
        TableName tableAssessmentCriteria = TableName.valueOf("assessment_criterias");
        String[] assessmentCriterias = { "main", "detail" };
        client.deleteTable(tableAssessmentCriteria);
        client.createTable(tableAssessmentCriteria, assessmentCriterias);

        // Create Tabel Bentuk Pembelajaran
        TableName tableFormLearning = TableName.valueOf("form_learnings");
        String[] formLearnings = { "main", "detail" };
        client.deleteTable(tableFormLearning);
        client.createTable(tableFormLearning, formLearnings);

        // Create Tabel Metode Pembelajaran
        TableName tableLearningMethod = TableName.valueOf("learning_methods");
        String[] learningMethods = { "main", "detail" };
        client.deleteTable(tableLearningMethod);
        client.createTable(tableLearningMethod, learningMethods);

        // Create Tabel Pertanyaan
        TableName tableQuestion = TableName.valueOf("questions");
        String[] questions = { "main", "rps_detail", "detail" };
        client.deleteTable(tableQuestion);
        client.createTable(tableQuestion, questions);

        // Create Tabel Jawaban
        TableName tableAnswer = TableName.valueOf("answers");
        String[] answers = { "main", "question", "detail" };
        client.deleteTable(tableAnswer);
        client.createTable(tableAnswer, answers);

        // Create Tabel Ujian
        TableName tableExam = TableName.valueOf("exams");
        String[] exams = { "main", "rps", "questions", "detail" };
        client.deleteTable(tableExam);
        client.createTable(tableExam, exams);

        // Create Tabel Kuis
        TableName tableQuizzes = TableName.valueOf("quizzes");
        String[] quizzes = { "main", "rps", "questions", "detail" };
        client.deleteTable(tableQuizzes);
        client.createTable(tableQuizzes, quizzes);

        // Create Tabel Latihan
        TableName tableExcercise = TableName.valueOf("exercises");
        String[] exercises = { "main", "rps", "questions", "detail" };
        client.deleteTable(tableExcercise);
        client.createTable(tableExcercise, exercises);

        // Create Tabel Percobaan pengumpulan Ujian
        TableName tableExamAttempts = TableName.valueOf("exam_attempts");
        String[] examAttempts = { "main", "exam", "user", "student", "student_answers", "detail" };
        client.deleteTable(tableExamAttempts);
        client.createTable(tableExamAttempts, examAttempts);

        // Create Tabel Percobaan pengumpulan Kuis
        TableName tableQuizAttempts = TableName.valueOf("quiz_attempts");
        String[] quizAttempts = { "main", "quiz", "user", "student", "student_answers", "detail" };
        client.deleteTable(tableQuizAttempts);
        client.createTable(tableQuizAttempts, quizAttempts);

        // Create Tabel Percobaan pengumpulan Latihan
        TableName tableExerciseAttempts = TableName.valueOf("exercise_attempts");
        String[] exerciseAttempts = { "main", "exercise", "user", "student", "student_answers", "detail" };
        client.deleteTable(tableExerciseAttempts);
        client.createTable(tableExerciseAttempts, exerciseAttempts);

        // Create Tabel Krireria Penilaian Soal

        TableName tableQuestionCriteria = TableName.valueOf("question_criterias");
        String[] questionCriterias = { "main", "detail" };
        client.deleteTable(tableQuestionCriteria);
        client.createTable(tableQuestionCriteria, questionCriterias);

        // Create Tabel Nilai Linguistic

        TableName tableLinguisticValue = TableName.valueOf("linguistic_values");
        String[] linguisticValues = { "main", "detail" };
        client.deleteTable(tableLinguisticValue);
        client.createTable(tableLinguisticValue, linguisticValues);

        // Create Tabel  Team Teaching
        TableName tableTeamTeaching = TableName.valueOf("team_teachings");
        String[] teamTeachings = {"main","detail","lecture","lecture2","lecture3"};
        client.deleteTable(tableTeamTeaching);
        client.createTable(tableTeamTeaching, teamTeachings);

        // Create Tabel Penilaian Soal
        TableName tableCriteriaValue = TableName.valueOf("criteria_values");
        String[] criteriaValues = { "main","detail","team_teaching","question","value1","value2","value3","value4","value5","value6","value7","value8","value9"};
        client.deleteTable(tableCriteriaValue);
        client.createTable(tableCriteriaValue, criteriaValues);

        // seeder
        // time now
        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        // Insert Jurusan
        client.insertRecord(tableDepartment, "DP001", "main", "id", "DP001");
        client.insertRecord(tableDepartment, "DP001", "main", "name", "Jurusan Teknologi Informasi");
        client.insertRecord(tableDepartment, "DP001", "main", "description", "Ini merupakan jurusan untuk mahasiswa informatika");
        client.insertRecord(tableDepartment, "DP001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableDepartment, "DP002", "main", "id", "DP002");
        client.insertRecord(tableDepartment, "DP002", "main", "name", "Jurusan Teknik Mesin");
        client.insertRecord(tableDepartment, "DP002", "main", "description", "Ini merupakan jurusan untuk mahasiswa teknik mesin");
        client.insertRecord(tableDepartment, "DP002", "detail", "created_by", "Doyatama");

        // Insert Prodi
        client.insertRecord(tableStudyProgram, "SP001", "main", "id", "SP001");
        client.insertRecord(tableStudyProgram, "SP001", "main", "name", "D4 Teknik Informatika");
        client.insertRecord(tableStudyProgram, "SP001", "main", "description", "Ini merupakan prodi untuk mahasiswa informatika");
        client.insertRecord(tableStudyProgram, "SP001", "department", "id", "DP001");
        client.insertRecord(tableStudyProgram, "SP001", "department", "name", "Jurusan Teknologi Informasi");
        client.insertRecord(tableStudyProgram, "SP001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableStudyProgram, "SP002", "main", "id", "SP002");
        client.insertRecord(tableStudyProgram, "SP002", "main", "name", "D3 Manajemen Informatika");
        client.insertRecord(tableStudyProgram, "SP002", "main", "description", "Ini merupakan prodi untuk mahasiswa informatika");
        client.insertRecord(tableStudyProgram, "SP002", "department", "id", "DP002");
        client.insertRecord(tableStudyProgram, "SP002", "department", "name", "Jurusan Teknik Mesin");
        client.insertRecord(tableStudyProgram, "SP002", "detail", "created_by", "Doyatama");

        // Insert Users
        client.insertRecord(tableUser, "USR001", "main", "id", "USR001");
        client.insertRecord(tableUser, "USR001", "main", "email", "admin@gmail.com");
        client.insertRecord(tableUser, "USR001", "main", "username", "admin");
        client.insertRecord(tableUser, "USR001", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
        client.insertRecord(tableUser, "USR001", "main", "roles", "1");
        client.insertRecord(tableUser, "USR001", "main", "created_at", "2023-05-14T04:56:23.174Z");
        client.insertRecord(tableUser, "USR001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableUser, "USR002", "main", "id", "USR002");
        client.insertRecord(tableUser, "USR002", "main", "email", "dosen@gmail.com");
        client.insertRecord(tableUser, "USR002", "main", "username", "dosen");
        client.insertRecord(tableUser, "USR002", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
        client.insertRecord(tableUser, "USR002", "main", "roles", "2");
        client.insertRecord(tableUser, "USR002", "main", "created_at", "2023-05-14T04:56:23.174Z");
        client.insertRecord(tableUser, "USR002", "detail", "created_by", "Doyatama");

        client.insertRecord(tableUser, "USR003", "main", "id", "USR003");
        client.insertRecord(tableUser, "USR003", "main", "email", "dosen2@gmail.com");
        client.insertRecord(tableUser, "USR003", "main", "username", "dosen2");
        client.insertRecord(tableUser, "USR003", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
        client.insertRecord(tableUser, "USR003", "main", "roles", "2");
        client.insertRecord(tableUser, "USR003", "main", "created_at", "2023-05-14T04:56:23.174Z");
        client.insertRecord(tableUser, "USR003", "detail", "created_by", "Doyatama");

        // Insert Religions
        client.insertRecord(tableReligion, "RLG001", "main", "id", "RLG001");
        client.insertRecord(tableReligion, "RLG001", "main", "name", "Islam");
        client.insertRecord(tableReligion, "RLG001", "main", "description", "deskripsi agama islam");
        client.insertRecord(tableReligion, "RLG001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableReligion, "RLG002", "main", "id", "RLG002");
        client.insertRecord(tableReligion, "RLG002", "main", "name", "Kristen");
        client.insertRecord(tableReligion, "RLG002", "main", "description", "deskripsi agama kristen");
        client.insertRecord(tableReligion, "RLG002", "detail", "created_by", "Doyatama");

        client.insertRecord(tableReligion, "RLG003", "main", "id", "RLG003");
        client.insertRecord(tableReligion, "RLG003", "main", "name", "Katolik");
        client.insertRecord(tableReligion, "RLG003", "main", "description", "deskripsi agama katolik");
        client.insertRecord(tableReligion, "RLG003", "detail", "created_by", "Doyatama");

        client.insertRecord(tableReligion, "RLG004", "main", "id", "RLG004");
        client.insertRecord(tableReligion, "RLG004", "main", "name", "Hindu");
        client.insertRecord(tableReligion, "RLG004", "main", "description", "deskripsi agama hindu");
        client.insertRecord(tableReligion, "RLG004", "detail", "created_by", "Doyatama");

        client.insertRecord(tableReligion, "RLG005", "main", "id", "RLG005");
        client.insertRecord(tableReligion, "RLG005", "main", "name", "Buddha");
        client.insertRecord(tableReligion, "RLG005", "main", "description", "deskripsi agama budha");
        client.insertRecord(tableReligion, "RLG005", "detail", "created_by", "Doyatama");

        client.insertRecord(tableReligion, "RLG006", "main", "id", "RLG006");
        client.insertRecord(tableReligion, "RLG006", "main", "name", "Kong Hu Chu");
        client.insertRecord(tableReligion, "RLG006", "main", "description", "deskripsi agama kong hu chu");
        client.insertRecord(tableReligion, "RLG006", "detail", "created_by", "Doyatama");

        // Insert Bentuk Pembelajaran
        client.insertRecord(tableFormLearning, "BP001", "main", "id", "BP001");
        client.insertRecord(tableFormLearning, "BP001", "main", "name", "Daring");
        client.insertRecord(tableFormLearning, "BP001", "main", "description", "Pembelajaran dilakukan secara dalam jaringan / online");
        client.insertRecord(tableFormLearning, "BP001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableFormLearning, "BP002", "main", "id", "BP002");
        client.insertRecord(tableFormLearning, "BP002", "main", "name", "Luring");
        client.insertRecord(tableFormLearning, "BP002", "main", "description", "Pembelajaran dilakukan secara diluar jaringan / offline");
        client.insertRecord(tableFormLearning, "BP002", "detail", "created_by", "Doyatama");

        // Insert Metode Pembelajaran
        client.insertRecord(tableLearningMethod, "MP001", "main", "id", "MP001");
        client.insertRecord(tableLearningMethod, "MP001", "main", "name", "Contextual Teaching and Learning (CTL)");
        client.insertRecord(tableLearningMethod, "MP001", "main", "description", "Pengertian dari CTL");
        client.insertRecord(tableLearningMethod, "MP001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableLearningMethod, "MP002", "main", "id", "MP002");
        client.insertRecord(tableLearningMethod, "MP002", "main", "name", "Problem Based Learning");
        client.insertRecord(tableLearningMethod, "MP002", "main", "description", "Pengertian dari PBL");
        client.insertRecord(tableLearningMethod, "MP002", "detail", "created_by", "Doyatama");

        // Insert Kriteria Penilaian
        client.insertRecord(tableAssessmentCriteria, "KP001", "main", "id", "KP001");
        client.insertRecord(tableAssessmentCriteria, "KP001", "main", "name", "Ketepatan");
        client.insertRecord(tableAssessmentCriteria, "KP001", "main", "description", "Ketepatan dalam pengumpulan tugas dan kehadiran di kelas");
        client.insertRecord(tableAssessmentCriteria, "KP001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableAssessmentCriteria, "KP002", "main", "id", "KP002");
        client.insertRecord(tableAssessmentCriteria, "KP002", "main", "name", "Penugasan");
        client.insertRecord(tableAssessmentCriteria, "KP002", "main", "description", "Penilaian didasarkan dari penugasan yang diberikan dosen");
        client.insertRecord(tableAssessmentCriteria, "KP002", "detail", "created_by", "Doyatama");

        // Insert Bentuk Penilaian
        client.insertRecord(tableAppraisalForm, "AF001", "main", "id", "AF001");
        client.insertRecord(tableAppraisalForm, "AF001", "main", "name", "Keaktifan diskusi kelompok meliputi bertanya dan menjawab");
        client.insertRecord(tableAppraisalForm, "AF001", "main", "description", "Keaktifan diskusi kelompok");
        client.insertRecord(tableAppraisalForm, "AF001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableAppraisalForm, "AF002", "main", "id", "AF002");
        client.insertRecord(tableAppraisalForm, "AF002", "main", "name", "Ketepatan jawaban tugas");
        client.insertRecord(tableAppraisalForm, "AF002", "main", "description", "Ketepatan jawaban yang diberikan dari tugas");
        client.insertRecord(tableAppraisalForm, "AF002", "detail", "created_by", "Doyatama");

        // Insert Subject Group
        client.insertRecord(tableSubjectGroup, "SG001", "main", "id", "SG001");
        client.insertRecord(tableSubjectGroup, "SG001", "main", "name", "Big Data");
        client.insertRecord(tableSubjectGroup, "SG001", "main", "description", "ini kelompok big data");
        client.insertRecord(tableSubjectGroup, "SG001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableSubjectGroup, "SG002", "main", "id", "SG002");
        client.insertRecord(tableSubjectGroup, "SG002", "main", "name", "Artificial Inteligent");
        client.insertRecord(tableSubjectGroup, "SG002", "main", "description", "ini kelompok AI");
        client.insertRecord(tableSubjectGroup, "SG002", "detail", "created_by", "Doyatama");

        client.insertRecord(tableSubjectGroup, "SG003", "main", "id", "SG003");
        client.insertRecord(tableSubjectGroup, "SG003", "main", "name", "Sistem Pendukung Keputusan");
        client.insertRecord(tableSubjectGroup, "SG003", "main", "description", "ini kelompok SPK");
        client.insertRecord(tableSubjectGroup, "SG003", "detail", "created_by", "Doyatama");

        // Insert Subject
        client.insertRecord(tableSubject, "SB001", "main", "id", "SB001");
        client.insertRecord(tableSubject, "SB001", "main", "name", "Pemrograman Berbasis Object");
        client.insertRecord(tableSubject, "SB001", "main", "description", "ini deskripsi dari mata kuliah");
        client.insertRecord(tableSubject, "SB001", "main", "credit_point", "4");
        client.insertRecord(tableSubject, "SB001", "main", "year_commenced", "2023");
        client.insertRecord(tableSubject, "SB001", "study_program", "id", "SP001");
        client.insertRecord(tableSubject, "SB001", "study_program", "name", "D4 Teknik Informatika");
        client.insertRecord(tableSubject, "SB001", "subject_group", "id", "SG001");
        client.insertRecord(tableSubject, "SB001", "subject_group", "name", "Big Data");
        client.insertRecord(tableSubject, "SB001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableSubject, "SB002", "main", "id", "SB002");
        client.insertRecord(tableSubject, "SB002", "main", "name", "Pemrograman Lanjut");
        client.insertRecord(tableSubject, "SB002", "main", "description", "ini deskripsi dari mata kuliah");
        client.insertRecord(tableSubject, "SB002", "main", "credit_point", "4");
        client.insertRecord(tableSubject, "SB002", "main", "year_commenced", "2023");
        client.insertRecord(tableSubject, "SB002", "study_program", "id", "SP001");
        client.insertRecord(tableSubject, "SB002", "study_program", "name", "D4 Teknik Informatika");
        client.insertRecord(tableSubject, "SB002", "subject_group", "id", "SG001");
        client.insertRecord(tableSubject, "SB002", "subject_group", "name", "Big Data");
        client.insertRecord(tableSubject, "SB002", "detail", "created_by", "Doyatama");

        // Insert Lectures
        client.insertRecord(tableLecture, "LEC001", "main", "id", "LEC001");
        client.insertRecord(tableLecture, "LEC001", "main", "address", "dosen@gmail.com");
        client.insertRecord(tableLecture, "LEC001", "main", "date_born", "dosen");
        client.insertRecord(tableLecture, "LEC001", "main", "gender", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
        client.insertRecord(tableLecture, "LEC001", "main", "name", "2");
        client.insertRecord(tableLecture, "LEC001", "main", "nidn", "2");
        client.insertRecord(tableLecture, "LEC001", "main", "phone", "2");
        client.insertRecord(tableLecture, "LEC001", "main", "place_born", "2");
        client.insertRecord(tableLecture, "LEC001", "main", "status", "Dosen");
        client.insertRecord(tableLecture, "LEC001", "religion", "id", "RLG001");
        client.insertRecord(tableLecture, "LEC001", "religion", "name", "Islam");
        client.insertRecord(tableLecture, "LEC001", "study_program", "id", "SP001");
        client.insertRecord(tableLecture, "LEC001", "study_program", "name", "D4 Teknik Informatika");
        client.insertRecord(tableLecture, "LEC001", "user", "id", "USR001");
        client.insertRecord(tableLecture, "LEC001", "user", "name", "dosen");
        client.insertRecord(tableLecture, "LEC001", "user", "email", "dosen@gmail.com");
        client.insertRecord(tableLecture, "LEC001", "user", "username", "dosen");
        client.insertRecord(tableLecture, "LEC001", "detail", "created_by", "Doyatama");

        client.insertRecord(tableLecture, "LEC002", "main", "id", "LEC002");
        client.insertRecord(tableLecture, "LEC002", "main", "address", "dosen2@gmail.com");
        client.insertRecord(tableLecture, "LEC002", "main", "date_born", "dosen2");
        client.insertRecord(tableLecture, "LEC002", "main", "gender", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
        client.insertRecord(tableLecture, "LEC002", "main", "name", "2");
        client.insertRecord(tableLecture, "LEC002", "main", "nidn", "2");
        client.insertRecord(tableLecture, "LEC002", "main", "phone", "2");
        client.insertRecord(tableLecture, "LEC002", "main", "place_born", "2");
        client.insertRecord(tableLecture, "LEC002", "main", "status", "Dosen");
        client.insertRecord(tableLecture, "LEC002", "religion", "id", "RLG001");
        client.insertRecord(tableLecture, "LEC002", "religion", "name", "Islam");
        client.insertRecord(tableLecture, "LEC002", "study_program", "id", "SP001");
        client.insertRecord(tableLecture, "LEC002", "study_program", "name", "D4 Teknik Informatika");
        client.insertRecord(tableLecture, "LEC002", "user", "id", "USR002");
        client.insertRecord(tableLecture, "LEC002", "user", "name", "dosen");
        client.insertRecord(tableLecture, "LEC002", "user", "email", "dosen@gmail.com");
        client.insertRecord(tableLecture, "LEC002", "user", "username", "dosen");
        client.insertRecord(tableLecture, "LEC002", "detail", "created_by", "Doyatama");

        // Learning Media
        // Software
        client.insertRecord(tableLearningMedia, "LM001", "main", "id", "LM001");
        client.insertRecord(tableLearningMedia, "LM001", "main", "name", "Netbeans");
        client.insertRecord(tableLearningMedia, "LM001", "main", "description", "ini deskripsi netbeans");
        client.insertRecord(tableLearningMedia, "LM001", "main", "type", "1");
        client.insertRecord(tableLearningMedia, "LM001", "detail", "created_by", "Doyatama");

        // Hardware
        client.insertRecord(tableLearningMedia, "LM002", "main", "id", "LM002");
        client.insertRecord(tableLearningMedia, "LM002", "main", "name", "PC / Komputer");
        client.insertRecord(tableLearningMedia, "LM002", "main", "description", "ini deskripsi pc / komputer");
        client.insertRecord(tableLearningMedia, "LM002", "main", "type", "2");
        client.insertRecord(tableLearningMedia, "LM002", "detail", "created_by", "Doyatama");

        // RPS
        client.insertRecord(tableRPS, "RPS001", "main", "id", "RPS001");
        client.insertRecord(tableRPS, "RPS001", "main", "name", "Dummy RPS");
        client.insertRecord(tableRPS, "RPS001", "main", "sks", "3");
        client.insertRecord(tableRPS, "RPS001", "main", "semester", "3");
        client.insertRecord(tableRPS, "RPS001", "main", "cpl_prodi", "Dummy CPL Prodi");
        client.insertRecord(tableRPS, "RPS001", "main", "cpl_mk", "Dummy CPL MK");
        client.insertRecord(tableRPS, "RPS001", "study_program", "id", "SP001");
        client.insertRecord(tableRPS, "RPS001", "study_program", "name", "D4 Teknik Informatika");
        client.insertRecord(tableRPS, "RPS001", "subject", "id", "SB001");
        client.insertRecord(tableRPS, "RPS001", "subject", "name", "Pemrograman Berbasis Object");
        client.insertRecord(tableRPS, "RPS001", "ka_study_program", "id", "LEC001");
        client.insertRecord(tableRPS, "RPS001", "ka_study_program", "name", "2");
        client.insertRecord(tableRPS, "RPS001", "detail", "created_by", "Doyatama");
        client.insertRecord(tableRPS, "RPS001", "detail", "created_at", instant.toString());

        // RPS Detail
        client.insertRecord(tableRPSDetail, "RPSD001", "main", "id", "RPSD001");
        client.insertRecord(tableRPSDetail, "RPSD001", "main", "week", "2");
        client.insertRecord(tableRPSDetail, "RPSD001", "rps", "id", "RPS001");
        client.insertRecord(tableRPSDetail, "RPSD001", "rps", "name", "Dummy RPS");
        client.insertRecord(tableRPSDetail, "RPSD001", "main", "sub_cp_mk", "Dummy Sub CP MK");
        client.insertRecord(tableRPSDetail, "RPSD001", "learning_materials", "lm_1", "Dummy Learning Material");
        client.insertRecord(tableRPSDetail, "RPSD001", "form_learning", "id", "BP001");
        client.insertRecord(tableRPSDetail, "RPSD001", "form_learning", "name", "Daring");
        client.insertRecord(tableRPSDetail, "RPSD001", "assignments", "lm_1",  "Dummy Penugasan");
        client.insertRecord(tableRPSDetail, "RPSD001", "estimated_times", "et_1", "Dummy Estimasi Waktu");
        client.insertRecord(tableRPSDetail, "RPSD001", "student_learning_experiences", "sle_1", "Dummy Pengalaman Mahasiswa");
        client.insertRecord(tableRPSDetail, "RPSD001", "assessment_indicators", "ai_1", "Dummy Assessment Indikator");
        client.insertRecord(tableRPSDetail, "RPSD001", "main", "weight", "3");
        client.insertRecord(tableRPSDetail, "RPSD001", "detail", "created_by", "Doyatama");
        client.insertRecord(tableRPSDetail, "RPSD001", "detail", "created_at", instant.toString());

        for (int i = 0; i <100; i++) {
            Faker faker = new Faker();
            String[] typeQuestion = {"VIDEO", "AUDIO", "IMAGE", "NORMAL"};
            String[] typeAnswer = {"MULTIPLE_CHOICE", "BOOLEAN", "COMPLETION", "MATCHING", "ESSAY"};
            String typeQ = typeQuestion[faker.random().nextInt(typeQuestion.length)];
            String path = "";
            client.insertRecord(tableQuestion, "QST"+i, "main", "id", "QST"+i);
            client.insertRecord(tableQuestion, "QST"+i, "main", "title", faker.lorem().sentence());
            client.insertRecord(tableQuestion, "QST"+i, "main", "description", faker.lorem().sentence());
            client.insertRecord(tableQuestion, "QST"+i, "main", "question_type", typeQ);
            client.insertRecord(tableQuestion, "QST"+i, "main", "answer_type", typeAnswer[faker.random().nextInt(typeAnswer.length)]);
            switch (typeQ) {
                case "VIDEO":
                    path = "webhdfs/v1/questions/video_dummy.mp4?op=OPEN";
                    break;
                case "AUDIO":
                    path = "webhdfs/v1/questions/audio_dummy.mp3?op=OPEN";
                    break;
                case "IMAGE":
                    path = "webhdfs/v1/questions/image_dummy.png?op=OPEN";
                    break;
                case "NORMAL":
                    path = "none";
                    break;
            }
            client.insertRecord(tableQuestion, "QST"+i, "main", "file_path", path);
            client.insertRecord(tableQuestion, "QST"+i, "rps_detail", "id", "RPSD001");
            client.insertRecord(tableQuestion, "QST"+i, "rps_detail", "sub_cp_mk", "Dummy Sub CP MK");
            client.insertRecord(tableQuestion, "QST"+i, "detail", "rps_id", "RPS001");
            client.insertRecord(tableQuestion, "QST"+i, "detail", "created_by", "Doyatama");
        }
    }
}