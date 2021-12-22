package com.gmail.voronovskyi.yaroslav.testtaskuniversity.service;

import java.util.List;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Group;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Professor;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Student;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Subject;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.exception.UniversityServiceException;

public interface GroupService {
    public Group getGroup(int id) throws UniversityServiceException;
    
    public List<Group> getAllGroups() throws UniversityServiceException;
    
    public List<Group> saveGroup(List<Group> groupsList) throws UniversityServiceException;
    
    public Group updateGroup(Group group) throws UniversityServiceException;
    
    public void deleteGroup(Group group) throws UniversityServiceException;
    
    public Student getStudent(int id) throws UniversityServiceException;
    
    public List<Student> getAllStudents() throws UniversityServiceException;
    
    public List<Student> saveStudent(List<Student> studentsList) throws UniversityServiceException;
    
    public Student updateStudent(Student student) throws UniversityServiceException;
    
    public void deleteStudent(Student student) throws UniversityServiceException;
    
    public Professor getProfessor(int id) throws UniversityServiceException;
    
    public List<Professor> getAllProfessors() throws UniversityServiceException;
    
    public List<Professor> saveProfessor(List<Professor> professorsList) throws UniversityServiceException;
    
    public Professor updateProfessor(Professor professor) throws UniversityServiceException;
    
    public void deleteProfessor(Professor professor) throws UniversityServiceException;
    
    public Subject getSubject(int id) throws UniversityServiceException;
    
    public List<Subject> getAllSubjects() throws UniversityServiceException;
    
    public List<Subject> saveSubject(List<Subject> subjectsList) throws UniversityServiceException;
    
    public Subject updateSubject(Subject subject) throws UniversityServiceException;
    
    public void deleteSubject(Subject subject) throws UniversityServiceException;
}
