package com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Course;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer> {

}
