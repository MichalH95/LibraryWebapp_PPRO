package com.ppro.projekt.service;

import com.ppro.projekt.entity.University;

import java.util.List;

public interface UniversityService {

    University findById(long id);

    University findByName(String name);

    void saveUniversity(University University);

    void updateUniversity(University University);

    void deleteUniversityById(long id);

    List<University> findAllUniversitys();

    void deleteAllUniversitys();

    public boolean isUniversityExist(University University);

}