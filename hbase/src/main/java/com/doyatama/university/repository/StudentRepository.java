package com.doyatama.university.repository;

import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.Lecture;
import com.doyatama.university.model.Question;
import com.doyatama.university.model.Student;
import com.doyatama.university.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StudentRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "students";

    public List<Student> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("nim", "nim");
        columnMapping.put("name", "name");
        columnMapping.put("gender", "gender");
        columnMapping.put("phone", "phone");
        columnMapping.put("birth_date", "birth_date");
        columnMapping.put("place_born", "place_born");
        columnMapping.put("address", "address");
        columnMapping.put("user", "user");
        columnMapping.put("religion", "religion");
        columnMapping.put("study_program", "study_program");

        return client.showListTable(tableUsers.toString(), columnMapping, Student.class, size);
    }

    public List<Student> findAllByUserID(String userID, int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("nim", "nim");
        columnMapping.put("name", "name");
        columnMapping.put("gender", "gender");
        columnMapping.put("phone", "phone");
        columnMapping.put("birth_date", "birth_date");
        columnMapping.put("place_born", "place_born");
        columnMapping.put("address", "address");
        columnMapping.put("user", "user");
        columnMapping.put("religion", "religion");
        columnMapping.put("study_program", "study_program");

        return client.getDataListByColumn(tableUsers.toString(), columnMapping, "user", "id", userID, Student.class, size);
    }

    public Student save(Student student) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = UUID.randomUUID().toString();

        TableName tableStudent = TableName.valueOf(tableName);
        client.insertRecord(tableStudent, rowKey, "main", "id", rowKey);
        client.insertRecord(tableStudent, rowKey, "main", "nim", student.getNim());
        client.insertRecord(tableStudent, rowKey, "main", "name", student.getName());
        client.insertRecord(tableStudent, rowKey, "main", "gender", student.getGender().toString());
        client.insertRecord(tableStudent, rowKey, "main", "phone", student.getPhone());
        client.insertRecord(tableStudent, rowKey, "main", "birth_date", student.getBirth_date().toString());
        client.insertRecord(tableStudent, rowKey, "main", "place_born", student.getPlace_born().toString());
        client.insertRecord(tableStudent, rowKey, "main", "address", student.getAddress());
        client.insertRecord(tableStudent, rowKey, "religion", "id", student.getReligion().getId());
        client.insertRecord(tableStudent, rowKey, "religion", "name", student.getReligion().getName());
        client.insertRecord(tableStudent, rowKey, "study_program", "id", student.getStudyProgram().getId());
        client.insertRecord(tableStudent, rowKey, "study_program", "name", student.getStudyProgram().getName());
        client.insertRecord(tableStudent, rowKey, "user", "id", student.getUser().getId());
        client.insertRecord(tableStudent, rowKey, "user", "name", student.getUser().getName());
        client.insertRecord(tableStudent, rowKey, "user", "username", student.getUser().getUsername());
        client.insertRecord(tableStudent, rowKey, "user", "email", student.getUser().getEmail());
        client.insertRecord(tableStudent, rowKey, "detail", "created_by", "Doyatama");
        return student;
    }

    public Student findById(String studentId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("nim", "nim");
        columnMapping.put("name", "name");
        columnMapping.put("gender", "gender");
        columnMapping.put("phone", "phone");
        columnMapping.put("birth_date", "birth_date");
        columnMapping.put("place_born", "place_born");
        columnMapping.put("address", "address");
        columnMapping.put("user", "user");
        columnMapping.put("religion", "religion");
        columnMapping.put("study_program", "study_program");

        return client.showDataTable(tableUsers.toString(), columnMapping, studentId, Student.class);
    }

    public Student update(String studentId, Student student) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        TableName tableStudent = TableName.valueOf(tableName);
        client.insertRecord(tableStudent, studentId, "main", "nim", student.getNim());
        client.insertRecord(tableStudent, studentId, "main", "name", student.getName());
        client.insertRecord(tableStudent, studentId, "main", "gender", student.getGender().toString());
        client.insertRecord(tableStudent, studentId, "main", "phone", student.getPhone());
        client.insertRecord(tableStudent, studentId, "main", "birth_date", student.getBirth_date().toString());
        client.insertRecord(tableStudent, studentId, "main", "address", student.getAddress());
        client.insertRecord(tableStudent, studentId, "religion", "id", student.getReligion().getId());
        client.insertRecord(tableStudent, studentId, "religion", "name", student.getReligion().getName());
        client.insertRecord(tableStudent, studentId, "study_program", "id", student.getStudyProgram().getId());
        client.insertRecord(tableStudent, studentId, "study_program", "name", student.getStudyProgram().getName());
        client.insertRecord(tableStudent, studentId, "user", "id", student.getUser().getId());
        client.insertRecord(tableStudent, studentId, "user", "name", student.getUser().getName());
        client.insertRecord(tableStudent, studentId, "user", "username", student.getUser().getUsername());
        client.insertRecord(tableStudent, studentId, "user", "email", student.getUser().getEmail());
        client.insertRecord(tableStudent, studentId, "detail", "created_by", "Doyatama");
        return student;
    }

    public boolean existsByUserID(String UID) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");

        Student student = client.getDataByColumn(tableUsers.toString(), columnMapping, "user", "id", UID, Student.class);
        if(student.getId() != null){
            return true;
        }else{
            return false;
        }
    }


    public boolean deleteById(String studentId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord(tableName, studentId);
        return true;
    }
}
