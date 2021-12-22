package com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
    
}
