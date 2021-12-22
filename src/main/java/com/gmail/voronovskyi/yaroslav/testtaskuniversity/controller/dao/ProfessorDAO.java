package com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Professor;

@Repository
public interface ProfessorDAO extends JpaRepository<Professor, Integer> {
   
}
