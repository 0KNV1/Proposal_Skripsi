
package com.doyatama.university.repository;

import com.doyatama.university.controller.DepartmentController;
import com.doyatama.university.helper.HBaseCustomClient;
import com.doyatama.university.model.Department;
import com.doyatama.university.model.Lecture;
import com.doyatama.university.model.Lecture;
import com.doyatama.university.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;
import java.util.*;

public class LectureRepository {
    Configuration conf = HBaseConfiguration.create();
    String tableName = "lectures";

    public List<Lecture> findAll(int size) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("nidn", "nidn");
        columnMapping.put("name", "name");
        columnMapping.put("place_born", "place_born");
        columnMapping.put("date_born", "date_born");
        columnMapping.put("gender", "gender");
        columnMapping.put("status", "status");
        columnMapping.put("address", "address");
        columnMapping.put("phone", "phone");
        columnMapping.put("religion", "religion");
        columnMapping.put("user", "user");
        columnMapping.put("study_program", "study_program");
        return client.showListTable(tableUsers.toString(), columnMapping, Lecture.class, size);
    }

    public Lecture save(Lecture lecture) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        String rowKey = UUID.randomUUID().toString().substring(0, 5);

        TableName tableLecture = TableName.valueOf(tableName);
        client.insertRecord(tableLecture, rowKey, "main", "id", rowKey);
        client.insertRecord(tableLecture, rowKey, "main", "nidn", lecture.getNidn());
        client.insertRecord(tableLecture, rowKey, "main", "name", lecture.getName());
        client.insertRecord(tableLecture, rowKey, "main", "place_born", lecture.getPlace_born());
        client.insertRecord(tableLecture, rowKey, "main", "date_born", lecture.getDate_born());
        client.insertRecord(tableLecture, rowKey, "main", "gender", lecture.getGender());
        client.insertRecord(tableLecture, rowKey, "main", "status", lecture.getStatus());
        client.insertRecord(tableLecture, rowKey, "main", "phone", lecture.getPhone());
        client.insertRecord(tableLecture, rowKey, "main", "address", lecture.getAddress());
        client.insertRecord(tableLecture, rowKey, "religion", "id", lecture.getReligion().getId());
        client.insertRecord(tableLecture, rowKey, "religion", "name", lecture.getReligion().getName());
        client.insertRecord(tableLecture, rowKey, "study_program", "id", lecture.getStudyProgram().getId());
        client.insertRecord(tableLecture, rowKey, "study_program", "name", lecture.getStudyProgram().getName());
        client.insertRecord(tableLecture, rowKey, "user", "id", lecture.getUser().getId());
        client.insertRecord(tableLecture, rowKey, "user", "name", lecture.getUser().getName());
        client.insertRecord(tableLecture, rowKey, "user", "username", lecture.getUser().getUsername());
        client.insertRecord(tableLecture, rowKey, "user", "email", lecture.getUser().getEmail());
        client.insertRecord(tableLecture, rowKey, "detail", "created_by", "Doyatama");
        return lecture;
    }

    public Lecture findById(String lectureId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("nidn", "nidn");
        columnMapping.put("name", "name");
        columnMapping.put("place_born", "place_born");
        columnMapping.put("date_born", "date_born");
        columnMapping.put("gender", "gender");
        columnMapping.put("status", "status");
        columnMapping.put("address", "address");
        columnMapping.put("phone", "phone");
        columnMapping.put("religion", "religion");
        columnMapping.put("user", "user");
        columnMapping.put("study_program", "study_program");

        return client.showDataTable(tableUsers.toString(), columnMapping, lectureId, Lecture.class);
    }

    public List<Lecture> findAllById(List<String> lectureIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("nidn", "nidn");
        columnMapping.put("name", "name");
        columnMapping.put("place_born", "place_born");
        columnMapping.put("date_born", "date_born");
        columnMapping.put("gender", "gender");
        columnMapping.put("status", "status");
        columnMapping.put("address", "address");
        columnMapping.put("phone", "phone");
        columnMapping.put("religion", "religion");
        columnMapping.put("user", "user");
        columnMapping.put("study_program", "study_program");

        List<Lecture> lectures = new ArrayList<>();
        for (String lectureId : lectureIds) {
            Lecture lecture = client.showDataTable(table.toString(), columnMapping, lectureId, Lecture.class);
            if (lecture != null) {
                lectures.add(lecture);
            }
        }

        return lectures;
    }

    public List<Lecture> findRelationById(List<String> lectureIds) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName table = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();
        // Add the mappings to the HashMap
        columnMapping.put("id", "id");
        columnMapping.put("nidn", "nidn");
        columnMapping.put("name", "name");
        columnMapping.put("place_born", "place_born");
        columnMapping.put("date_born", "date_born");
        columnMapping.put("gender", "gender");
        columnMapping.put("status", "status");
        columnMapping.put("address", "address");
        columnMapping.put("phone", "phone");

        List<Lecture> lectures = new ArrayList<>();
        for (String lectureId : lectureIds) {
            Lecture lecture = client.showDataTable(table.toString(), columnMapping, lectureId, Lecture.class);
            if (lecture != null) {
                lectures.add(lecture);
            }
        }

        return lectures;
    }


    public Lecture update(String lectureId, Lecture lecture) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableLecture = TableName.valueOf(tableName);
        client.insertRecord(tableLecture, lectureId, "main", "nidn", lecture.getNidn());
        client.insertRecord(tableLecture, lectureId, "main", "name", lecture.getName());
        client.insertRecord(tableLecture, lectureId, "main", "place_born", lecture.getPlace_born());
        client.insertRecord(tableLecture, lectureId, "main", "date_born", lecture.getDate_born());
        client.insertRecord(tableLecture, lectureId, "main", "gender", lecture.getGender());
        client.insertRecord(tableLecture, lectureId, "main", "status", lecture.getStatus());
        client.insertRecord(tableLecture, lectureId, "main", "phone", lecture.getPhone());
        client.insertRecord(tableLecture, lectureId, "main", "address", lecture.getAddress());
        client.insertRecord(tableLecture, lectureId, "religion", "id", lecture.getReligion().getId());
        client.insertRecord(tableLecture, lectureId, "religion", "name", lecture.getReligion().getName());
        client.insertRecord(tableLecture, lectureId, "study_program", "id", lecture.getStudyProgram().getId());
        client.insertRecord(tableLecture, lectureId, "study_program", "name", lecture.getStudyProgram().getName());
        client.insertRecord(tableLecture, lectureId, "user", "id", lecture.getUser().getId());
        client.insertRecord(tableLecture, lectureId, "user", "name", lecture.getUser().getName());
        client.insertRecord(tableLecture, lectureId, "user", "username", lecture.getUser().getUsername());
        client.insertRecord(tableLecture, lectureId, "user", "email", lecture.getUser().getEmail());
        return lecture;
    }

    public boolean existsByUserID(String UID) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);

        TableName tableUsers = TableName.valueOf(tableName);
        Map<String, String> columnMapping = new HashMap<>();

        // Add the mappings to the HashMap
        columnMapping.put("id", "id");

        Lecture lecture = client.getDataByColumn(tableUsers.toString(), columnMapping, "user", "id", UID, Lecture.class);
        if(lecture.getId() != null){
            return true;
        }else{
            return false;
        }
    }


    public boolean deleteById(String lectureId) throws IOException {
        HBaseCustomClient client = new HBaseCustomClient(conf);
        client.deleteRecord("lectures", lectureId);
        return true;
    }
}
