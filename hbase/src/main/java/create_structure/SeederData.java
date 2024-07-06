package create_structure;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.github.javafaker.Faker;

public class SeederData {
    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();
        HBaseCustomClient client = new HBaseCustomClient(conf);

        // time now
        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        Instant instant = zonedDateTime.toInstant();

        // define name all table;
        TableName tableSubject = TableName.valueOf("subjects");
        TableName tableChapter = TableName.valueOf("chapters");
        TableName tableSubjectGroup = TableName.valueOf("subject_groups");
        TableName tableLecture= TableName.valueOf("lectures");
        TableName tableStudent = TableName.valueOf("students");
        TableName tableRPS = TableName.valueOf("rps");
        TableName tableRPSDetail = TableName.valueOf("rps_details");
        TableName tableReference = TableName.valueOf("references");
        TableName tableLearningMedia = TableName.valueOf("learning_medias");
        TableName tableReligion = TableName.valueOf("religions");
        TableName tableDepartment = TableName.valueOf("departments");
        TableName tableStudyProgram = TableName.valueOf("study_programs");
        TableName tableUser = TableName.valueOf("users");
        TableName tableAppraisalForm = TableName.valueOf("appraisal_forms");
        TableName tableAssessmentCriteria = TableName.valueOf("assessment_criterias");
        TableName tableFormLearning = TableName.valueOf("form_learnings");
        TableName tableLearningMethod = TableName.valueOf("learning_methods");
        TableName tableQuestion = TableName.valueOf("questions");
        TableName tableAnswer = TableName.valueOf("answers");
        TableName tableExam = TableName.valueOf("exams");
        TableName tableQuizzes = TableName.valueOf("quizzes");
        TableName tableExcercise = TableName.valueOf("exercises");

        // ==============================================================================================
        // INSERT DATA
        // ==============================================================================================

        // Insert Jurusan
//        client.insertRecord(tableDepartment, "DP001", "main", "id", "DP001");
//        client.insertRecord(tableDepartment, "DP001", "main", "name", "Jurusan Teknologi Informasi");
//        client.insertRecord(tableDepartment, "DP001", "main", "description", "Ini merupakan jurusan untuk mahasiswa informatika");
//        client.insertRecord(tableDepartment, "DP001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableDepartment, "DP002", "main", "id", "DP002");
//        client.insertRecord(tableDepartment, "DP002", "main", "name", "Jurusan Teknik Mesin");
//        client.insertRecord(tableDepartment, "DP002", "main", "description", "Ini merupakan jurusan untuk mahasiswa teknik mesin");
//        client.insertRecord(tableDepartment, "DP002", "detail", "created_by", "Doyatama");
//
//        // Insert Prodi
//        client.insertRecord(tableStudyProgram, "SP001", "main", "id", "SP001");
//        client.insertRecord(tableStudyProgram, "SP001", "main", "name", "D4 Teknik Informatika");
//        client.insertRecord(tableStudyProgram, "SP001", "main", "description", "Ini merupakan prodi untuk mahasiswa informatika");
//        client.insertRecord(tableStudyProgram, "SP001", "department", "id", "DP001");
//        client.insertRecord(tableStudyProgram, "SP001", "department", "name", "Jurusan Teknologi Informasi");
//        client.insertRecord(tableStudyProgram, "SP001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableStudyProgram, "SP002", "main", "id", "SP002");
//        client.insertRecord(tableStudyProgram, "SP002", "main", "name", "D3 Manajemen Informatika");
//        client.insertRecord(tableStudyProgram, "SP002", "main", "description", "Ini merupakan prodi untuk mahasiswa informatika");
//        client.insertRecord(tableStudyProgram, "SP002", "department", "id", "DP002");
//        client.insertRecord(tableStudyProgram, "SP002", "department", "name", "Jurusan Teknik Mesin");
//        client.insertRecord(tableStudyProgram, "SP002", "detail", "created_by", "Doyatama");
//
//        // Insert Users
//        client.insertRecord(tableUser, "USR001", "main", "id", "USR001");
//        client.insertRecord(tableUser, "USR001", "main", "email", "admin@gmail.com");
//        client.insertRecord(tableUser, "USR001", "main", "username", "admin");
//        client.insertRecord(tableUser, "USR001", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
//        client.insertRecord(tableUser, "USR001", "main", "roles", "1");
//        client.insertRecord(tableUser, "USR001", "main", "created_at", "2023-05-14T04:56:23.174Z");
//        client.insertRecord(tableUser, "USR001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableUser, "USR002", "main", "id", "USR002");
//        client.insertRecord(tableUser, "USR002", "main", "email", "dosen@gmail.com");
//        client.insertRecord(tableUser, "USR002", "main", "username", "dosen");
//        client.insertRecord(tableUser, "USR002", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
//        client.insertRecord(tableUser, "USR002", "main", "roles", "2");
//        client.insertRecord(tableUser, "USR002", "main", "created_at", "2023-05-14T04:56:23.174Z");
//        client.insertRecord(tableUser, "USR002", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableUser, "USR003", "main", "id", "USR003");
//        client.insertRecord(tableUser, "USR003", "main", "email", "dosen2@gmail.com");
//        client.insertRecord(tableUser, "USR003", "main", "username", "dosen2");
//        client.insertRecord(tableUser, "USR003", "main", "password", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
//        client.insertRecord(tableUser, "USR003", "main", "roles", "2");
//        client.insertRecord(tableUser, "USR003", "main", "created_at", "2023-05-14T04:56:23.174Z");
//        client.insertRecord(tableUser, "USR003", "detail", "created_by", "Doyatama");
//
//        // Insert Religions
//        client.insertRecord(tableReligion, "RLG001", "main", "id", "RLG001");
//        client.insertRecord(tableReligion, "RLG001", "main", "name", "Islam");
//        client.insertRecord(tableReligion, "RLG001", "main", "description", "deskripsi agama islam");
//        client.insertRecord(tableReligion, "RLG001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableReligion, "RLG002", "main", "id", "RLG002");
//        client.insertRecord(tableReligion, "RLG002", "main", "name", "Kristen");
//        client.insertRecord(tableReligion, "RLG002", "main", "description", "deskripsi agama kristen");
//        client.insertRecord(tableReligion, "RLG002", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableReligion, "RLG003", "main", "id", "RLG003");
//        client.insertRecord(tableReligion, "RLG003", "main", "name", "Katolik");
//        client.insertRecord(tableReligion, "RLG003", "main", "description", "deskripsi agama katolik");
//        client.insertRecord(tableReligion, "RLG003", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableReligion, "RLG004", "main", "id", "RLG004");
//        client.insertRecord(tableReligion, "RLG004", "main", "name", "Hindu");
//        client.insertRecord(tableReligion, "RLG004", "main", "description", "deskripsi agama hindu");
//        client.insertRecord(tableReligion, "RLG004", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableReligion, "RLG005", "main", "id", "RLG005");
//        client.insertRecord(tableReligion, "RLG005", "main", "name", "Buddha");
//        client.insertRecord(tableReligion, "RLG005", "main", "description", "deskripsi agama budha");
//        client.insertRecord(tableReligion, "RLG005", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableReligion, "RLG006", "main", "id", "RLG006");
//        client.insertRecord(tableReligion, "RLG006", "main", "name", "Kong Hu Chu");
//        client.insertRecord(tableReligion, "RLG006", "main", "description", "deskripsi agama kong hu chu");
//        client.insertRecord(tableReligion, "RLG006", "detail", "created_by", "Doyatama");
//
//        // Insert Bentuk Pembelajaran
//        client.insertRecord(tableFormLearning, "BP001", "main", "id", "BP001");
//        client.insertRecord(tableFormLearning, "BP001", "main", "name", "Daring");
//        client.insertRecord(tableFormLearning, "BP001", "main", "description", "Pembelajaran dilakukan secara dalam jaringan / online");
//        client.insertRecord(tableFormLearning, "BP001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableFormLearning, "BP002", "main", "id", "BP002");
//        client.insertRecord(tableFormLearning, "BP002", "main", "name", "Luring");
//        client.insertRecord(tableFormLearning, "BP002", "main", "description", "Pembelajaran dilakukan secara diluar jaringan / offline");
//        client.insertRecord(tableFormLearning, "BP002", "detail", "created_by", "Doyatama");
//
//        // Insert Metode Pembelajaran
//        client.insertRecord(tableLearningMethod, "MP001", "main", "id", "MP001");
//        client.insertRecord(tableLearningMethod, "MP001", "main", "name", "Contextual Teaching and Learning (CTL)");
//        client.insertRecord(tableLearningMethod, "MP001", "main", "description", "Pengertian dari CTL");
//        client.insertRecord(tableLearningMethod, "MP001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableLearningMethod, "MP002", "main", "id", "MP002");
//        client.insertRecord(tableLearningMethod, "MP002", "main", "name", "Problem Based Learning");
//        client.insertRecord(tableLearningMethod, "MP002", "main", "description", "Pengertian dari PBL");
//        client.insertRecord(tableLearningMethod, "MP002", "detail", "created_by", "Doyatama");
//
//        // Insert Kriteria Penilaian
//        client.insertRecord(tableAssessmentCriteria, "KP001", "main", "id", "KP001");
//        client.insertRecord(tableAssessmentCriteria, "KP001", "main", "name", "Ketepatan");
//        client.insertRecord(tableAssessmentCriteria, "KP001", "main", "description", "Ketepatan dalam pengumpulan tugas dan kehadiran di kelas");
//        client.insertRecord(tableAssessmentCriteria, "KP001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableAssessmentCriteria, "KP002", "main", "id", "KP002");
//        client.insertRecord(tableAssessmentCriteria, "KP002", "main", "name", "Penugasan");
//        client.insertRecord(tableAssessmentCriteria, "KP002", "main", "description", "Penilaian didasarkan dari penugasan yang diberikan dosen");
//        client.insertRecord(tableAssessmentCriteria, "KP002", "detail", "created_by", "Doyatama");
//
//        // Insert Bentuk Penilaian
//        client.insertRecord(tableAppraisalForm, "AF001", "main", "id", "AF001");
//        client.insertRecord(tableAppraisalForm, "AF001", "main", "name", "Keaktifan diskusi kelompok meliputi bertanya dan menjawab");
//        client.insertRecord(tableAppraisalForm, "AF001", "main", "description", "Keaktifan diskusi kelompok");
//        client.insertRecord(tableAppraisalForm, "AF001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableAppraisalForm, "AF002", "main", "id", "AF002");
//        client.insertRecord(tableAppraisalForm, "AF002", "main", "name", "Ketepatan jawaban tugas");
//        client.insertRecord(tableAppraisalForm, "AF002", "main", "description", "Ketepatan jawaban yang diberikan dari tugas");
//        client.insertRecord(tableAppraisalForm, "AF002", "detail", "created_by", "Doyatama");
//
//        // Insert Subject Group
//        client.insertRecord(tableSubjectGroup, "SG001", "main", "id", "SG001");
//        client.insertRecord(tableSubjectGroup, "SG001", "main", "name", "Big Data");
//        client.insertRecord(tableSubjectGroup, "SG001", "main", "description", "ini kelompok big data");
//        client.insertRecord(tableSubjectGroup, "SG001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableSubjectGroup, "SG002", "main", "id", "SG002");
//        client.insertRecord(tableSubjectGroup, "SG002", "main", "name", "Artificial Inteligent");
//        client.insertRecord(tableSubjectGroup, "SG002", "main", "description", "ini kelompok AI");
//        client.insertRecord(tableSubjectGroup, "SG002", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableSubjectGroup, "SG003", "main", "id", "SG003");
//        client.insertRecord(tableSubjectGroup, "SG003", "main", "name", "Sistem Pendukung Keputusan");
//        client.insertRecord(tableSubjectGroup, "SG003", "main", "description", "ini kelompok SPK");
//        client.insertRecord(tableSubjectGroup, "SG003", "detail", "created_by", "Doyatama");
//
//        // Insert Subject
//        client.insertRecord(tableSubject, "SB001", "main", "id", "SB001");
//        client.insertRecord(tableSubject, "SB001", "main", "name", "Pemrograman Berbasis Object");
//        client.insertRecord(tableSubject, "SB001", "main", "description", "ini deskripsi dari mata kuliah");
//        client.insertRecord(tableSubject, "SB001", "main", "credit_point", "4");
//        client.insertRecord(tableSubject, "SB001", "main", "year_commenced", "2023");
//        client.insertRecord(tableSubject, "SB001", "study_program", "id", "SP001");
//        client.insertRecord(tableSubject, "SB001", "study_program", "name", "D4 Teknik Informatika");
//        client.insertRecord(tableSubject, "SB001", "subject_group", "id", "SG001");
//        client.insertRecord(tableSubject, "SB001", "subject_group", "name", "Big Data");
//        client.insertRecord(tableSubject, "SB001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableSubject, "SB002", "main", "id", "SB002");
//        client.insertRecord(tableSubject, "SB002", "main", "name", "Pemrograman Lanjut");
//        client.insertRecord(tableSubject, "SB002", "main", "description", "ini deskripsi dari mata kuliah");
//        client.insertRecord(tableSubject, "SB002", "main", "credit_point", "4");
//        client.insertRecord(tableSubject, "SB002", "main", "year_commenced", "2023");
//        client.insertRecord(tableSubject, "SB002", "study_program", "id", "SP001");
//        client.insertRecord(tableSubject, "SB002", "study_program", "name", "D4 Teknik Informatika");
//        client.insertRecord(tableSubject, "SB002", "subject_group", "id", "SG001");
//        client.insertRecord(tableSubject, "SB002", "subject_group", "name", "Big Data");
//        client.insertRecord(tableSubject, "SB002", "detail", "created_by", "Doyatama");
//
//        // Insert Lectures
//        client.insertRecord(tableLecture, "LEC001", "main", "id", "LEC001");
//        client.insertRecord(tableLecture, "LEC001", "main", "address", "dosen@gmail.com");
//        client.insertRecord(tableLecture, "LEC001", "main", "date_born", "dosen");
//        client.insertRecord(tableLecture, "LEC001", "main", "gender", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
//        client.insertRecord(tableLecture, "LEC001", "main", "name", "2");
//        client.insertRecord(tableLecture, "LEC001", "main", "nidn", "2");
//        client.insertRecord(tableLecture, "LEC001", "main", "phone", "2");
//        client.insertRecord(tableLecture, "LEC001", "main", "place_born", "2");
//        client.insertRecord(tableLecture, "LEC001", "main", "status", "Dosen");
//        client.insertRecord(tableLecture, "LEC001", "religion", "id", "RLG001");
//        client.insertRecord(tableLecture, "LEC001", "religion", "name", "Islam");
//        client.insertRecord(tableLecture, "LEC001", "study_program", "id", "SP001");
//        client.insertRecord(tableLecture, "LEC001", "study_program", "name", "D4 Teknik Informatika");
//        client.insertRecord(tableLecture, "LEC001", "user", "id", "USR001");
//        client.insertRecord(tableLecture, "LEC001", "user", "name", "dosen");
//        client.insertRecord(tableLecture, "LEC001", "user", "email", "dosen@gmail.com");
//        client.insertRecord(tableLecture, "LEC001", "user", "username", "dosen");
//        client.insertRecord(tableLecture, "LEC001", "detail", "created_by", "Doyatama");
//
//        client.insertRecord(tableLecture, "LEC002", "main", "id", "LEC002");
//        client.insertRecord(tableLecture, "LEC002", "main", "address", "dosen2@gmail.com");
//        client.insertRecord(tableLecture, "LEC002", "main", "date_born", "dosen2");
//        client.insertRecord(tableLecture, "LEC002", "main", "gender", "$2a$10$SDRWMUk.2fnli0GTmqodJexjRksTw0En98dU8fdKsw7nTbZzMrj.2"); // password
//        client.insertRecord(tableLecture, "LEC002", "main", "name", "2");
//        client.insertRecord(tableLecture, "LEC002", "main", "nidn", "2");
//        client.insertRecord(tableLecture, "LEC002", "main", "phone", "2");
//        client.insertRecord(tableLecture, "LEC002", "main", "place_born", "2");
//        client.insertRecord(tableLecture, "LEC002", "main", "status", "Dosen");
//        client.insertRecord(tableLecture, "LEC002", "religion", "id", "RLG001");
//        client.insertRecord(tableLecture, "LEC002", "religion", "name", "Islam");
//        client.insertRecord(tableLecture, "LEC002", "study_program", "id", "SP001");
//        client.insertRecord(tableLecture, "LEC002", "study_program", "name", "D4 Teknik Informatika");
//        client.insertRecord(tableLecture, "LEC002", "user", "id", "USR002");
//        client.insertRecord(tableLecture, "LEC002", "user", "name", "dosen");
//        client.insertRecord(tableLecture, "LEC002", "user", "email", "dosen@gmail.com");
//        client.insertRecord(tableLecture, "LEC002", "user", "username", "dosen");
//        client.insertRecord(tableLecture, "LEC002", "detail", "created_by", "Doyatama");
//
//        // Learning Media
//        // Software
//        client.insertRecord(tableLearningMedia, "LM001", "main", "id", "LM001");
//        client.insertRecord(tableLearningMedia, "LM001", "main", "name", "Netbeans");
//        client.insertRecord(tableLearningMedia, "LM001", "main", "description", "ini deskripsi netbeans");
//        client.insertRecord(tableLearningMedia, "LM001", "main", "type", "1");
//        client.insertRecord(tableLearningMedia, "LM001", "detail", "created_by", "Doyatama");
//
//        // Hardware
//        client.insertRecord(tableLearningMedia, "LM002", "main", "id", "LM002");
//        client.insertRecord(tableLearningMedia, "LM002", "main", "name", "PC / Komputer");
//        client.insertRecord(tableLearningMedia, "LM002", "main", "description", "ini deskripsi pc / komputer");
//        client.insertRecord(tableLearningMedia, "LM002", "main", "type", "2");
//        client.insertRecord(tableLearningMedia, "LM002", "detail", "created_by", "Doyatama");
//
//        // RPS
//        client.insertRecord(tableRPS, "RPS001", "main", "id", "RPS001");
//        client.insertRecord(tableRPS, "RPS001", "main", "name", "Dummy RPS");
//        client.insertRecord(tableRPS, "RPS001", "main", "sks", "3");
//        client.insertRecord(tableRPS, "RPS001", "main", "semester", "3");
//        client.insertRecord(tableRPS, "RPS001", "main", "cpl_prodi", "Dummy CPL Prodi");
//        client.insertRecord(tableRPS, "RPS001", "main", "cpl_mk", "Dummy CPL MK");
//        client.insertRecord(tableRPS, "RPS001", "study_program", "id", "SP001");
//        client.insertRecord(tableRPS, "RPS001", "study_program", "name", "D4 Teknik Informatika");
//        client.insertRecord(tableRPS, "RPS001", "subject", "id", "SB001");
//        client.insertRecord(tableRPS, "RPS001", "subject", "name", "Pemrograman Berbasis Object");
//        client.insertRecord(tableRPS, "RPS001", "ka_study_program", "id", "LEC001");
//        client.insertRecord(tableRPS, "RPS001", "ka_study_program", "name", "2");
//        client.insertRecord(tableRPS, "RPS001", "detail", "created_by", "Doyatama");
//        client.insertRecord(tableRPS, "RPS001", "detail", "created_at", instant.toString());
//
//        // RPS Detail
//        client.insertRecord(tableRPSDetail, "RPSD001", "main", "id", "RPSD001");
//        client.insertRecord(tableRPSDetail, "RPSD001", "main", "week", "2");
//        client.insertRecord(tableRPSDetail, "RPSD001", "rps", "id", "RPS001");
//        client.insertRecord(tableRPSDetail, "RPSD001", "rps", "name", "Dummy RPS");
//        client.insertRecord(tableRPSDetail, "RPSD001", "main", "sub_cp_mk", "Dummy Sub CP MK");
//        client.insertRecord(tableRPSDetail, "RPSD001", "learning_materials", "lm_1", "Dummy Learning Material");
//        client.insertRecord(tableRPSDetail, "RPSD001", "form_learning", "id", "BP001");
//        client.insertRecord(tableRPSDetail, "RPSD001", "form_learning", "name", "Daring");
//        client.insertRecord(tableRPSDetail, "RPSD001", "assignments", "lm_1",  "Dummy Penugasan");
//        client.insertRecord(tableRPSDetail, "RPSD001", "estimated_times", "et_1", "Dummy Estimasi Waktu");
//        client.insertRecord(tableRPSDetail, "RPSD001", "student_learning_experiences", "sle_1", "Dummy Pengalaman Mahasiswa");
//        client.insertRecord(tableRPSDetail, "RPSD001", "assessment_indicators", "ai_1", "Dummy Assessment Indikator");
//        client.insertRecord(tableRPSDetail, "RPSD001", "main", "weight", "3");
//        client.insertRecord(tableRPSDetail, "RPSD001", "detail", "created_by", "Doyatama");
//        client.insertRecord(tableRPSDetail, "RPSD001", "detail", "created_at", instant.toString());

        // Question
        // Seeding soal dengan menggunakan audio, video, gambar dan soal biasa sebanyak 1 juta data
//        for (int i = 0; i < 10000; i++) {
        //                // Mendapatkan timestamp saat ini
//                String timestamp = String.valueOf(System.currentTimeMillis());
//
//                // Membuat UUID baru
//                String uuid = UUID.randomUUID().toString();
//
//                // Menggabungkan timestamp dan UUID
//                String newFileName = "file_" + timestamp + "_" + uuid;
//
//                String localPath = "F:/Skripsi/test.jpg";
//                String uri = "hdfs://hadoop-primary:9000";
//                String hdfsDir = "hdfs://hadoop-primary:9000/questions/"+newFileName+".jpg";
//                Configuration configuration = new Configuration();
//                FileSystem fs = FileSystem.get(URI.create(uri), configuration);
//                fs.copyFromLocalFile(new Path(localPath), new Path(hdfsDir));
//                String savePath = "webhdfs/v1/questions/"+ newFileName +".jpg?op=OPEN";

        // faker
//        Faker faker = new Faker();
//        String[] typeQuestion = {"VIDEO", "AUDIO", "IMAGE", "NORMAL"};
//        String[] typeAnswer = {"MULTIPLE_CHOICE", "BOOLEAN", "COMPLETION", "MATCHING", "ESSAY"};
//        client.insertRecord(tableQuestion, "QST"+1, "main", "id", "QST"+1);
//        client.insertRecord(tableQuestion, "QST"+1, "main", "title", faker.lorem().sentence());
//        client.insertRecord(tableQuestion, "QST"+1, "main", "description", faker.lorem().sentence());
//        client.insertRecord(tableQuestion, "QST"+1, "main", "question_type", typeQuestion[faker.random().nextInt(typeQuestion.length)]);
//        client.insertRecord(tableQuestion, "QST"+1, "main", "answer_type", typeAnswer[faker.random().nextInt(typeAnswer.length)]);
//        client.insertRecord(tableQuestion, "QST"+1, "main", "file_path", "webhdfs/v1/questions/QRcode_C1.png?op=OPEN");
//        client.insertRecord(tableQuestion, "QST"+1, "rps_detail", "id", "RPSD001");
//        client.insertRecord(tableQuestion, "QST"+1, "rps_detail", "sub_cp_mk", "Dummy Sub CP MK");
//        client.insertRecord(tableQuestion, "QST"+1, "detail", "created_by", "Doyatama");
        //        }
        for (int i = 1000000; i < 5000000; i++) {
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
