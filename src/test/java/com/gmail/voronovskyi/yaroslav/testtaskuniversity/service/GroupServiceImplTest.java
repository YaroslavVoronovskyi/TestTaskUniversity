package com.gmail.voronovskyi.yaroslav.testtaskuniversity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.GroupDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.ProfessorDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.StudentDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.SubjectDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Group;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Professor;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Student;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Subject;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.exception.UniversityServiceException;

public class GroupServiceImplTest extends AbstractServiceImplTest {
    @Mock
    private GroupDAO groupMock;
    @Mock
    private StudentDAO studentMock;
    @Mock
    private ProfessorDAO professorMock;
    @Mock
    private SubjectDAO subjectMock;
    @InjectMocks
    private GroupServiceImpl groupServiceImpl;
    
    @Test
    public void checkMethodGet() throws UniversityServiceException {
        initMocksForOneObject();
        assertEquals(groupServiceImpl.getGroup(1), createFakeGroup());
        assertEquals(groupServiceImpl.getStudent(1), createFakeStudent());
        assertEquals(groupServiceImpl.getProfessor(1), createFakeProfessor());
        assertEquals(groupServiceImpl.getSubject(1), createFakeSubject());
    }
    
    @Test
    public void checkMethodGetAll() throws UniversityServiceException {
        initMocksForManyObjects();
        List<Group> expectedGroupsList = groupServiceImpl.getAllGroups();
        List<Group> actualGroupsList = createFakeGroupsList();
        List<Student> expectedStudentsList = groupServiceImpl.getAllStudents();
        List<Student> actualStudentsList = createFakeStudentsList();
        List<Professor> expectedProfessorsList = groupServiceImpl.getAllProfessors();
        List<Professor> actualProfessorsList = createFakeProfessorsList();
        List<Subject> expectedSubjectsList = groupServiceImpl.getAllSubjects();
        List<Subject> actualSubjectsList = createFakeSubjectsList();
        assertEquals(expectedGroupsList.get(0), actualGroupsList.get(0));
        assertEquals(expectedGroupsList.get(1), actualGroupsList.get(1));
        assertEquals(expectedGroupsList.get(2), actualGroupsList.get(2));
        assertEquals(expectedStudentsList.get(0), actualStudentsList.get(0));
        assertEquals(expectedStudentsList.get(1), actualStudentsList.get(1));
        assertEquals(expectedStudentsList.get(2), actualStudentsList.get(2));
        assertEquals(expectedProfessorsList.get(0), actualProfessorsList.get(0));
        assertEquals(expectedProfessorsList.get(1), actualProfessorsList.get(1));
        assertEquals(expectedProfessorsList.get(2), actualProfessorsList.get(2));
        assertEquals(expectedSubjectsList.get(0), actualSubjectsList.get(0));
        assertEquals(expectedSubjectsList.get(1), actualSubjectsList.get(1));
        assertEquals(expectedSubjectsList.get(2), actualSubjectsList.get(2));
    }
    
    @Test
    public void checkMethodDelete() throws UniversityServiceException {
        initMocksForManyObjects();
        Group group = Group.builder().id(1).build();
        groupServiceImpl.deleteGroup(group);
        Student student = Student.builder().id(1).build();
        groupServiceImpl.deleteStudent(student);
        Professor professor = Professor.builder().id(1).build();
        groupServiceImpl.deleteProfessor(professor);
        Subject subject = Subject.builder().id(1).build();
        groupServiceImpl.deleteSubject(subject);
        assertFalse(groupServiceImpl.getAllGroups().contains(group));
        assertFalse(groupServiceImpl.getAllStudents().contains(student));
        assertFalse(groupServiceImpl.getAllProfessors().contains(professor));
        assertFalse(groupServiceImpl.getAllSubjects().contains(subject));
    }
    
    @Test
    public void checkMethodUpdate() throws UniversityServiceException {
        initMocksForOneObject();
        Group group = Group.builder().id(1).name("FF-22").build();
        groupServiceImpl.updateGroup(group);
        Student student = Student.builder().id(1).firstName("Yaroslav").lastName("Voronovskyi").build();
        groupServiceImpl.updateStudent(student);
        Professor professor = Professor.builder().id(1).firstName("Yaroslav").lastName("Voronovskyi").build();
        groupServiceImpl.updateProfessor(professor);
        Subject subject = Subject.builder().id(1).name("Java").description("Programming language").build();
        groupServiceImpl.updateSubject(subject);
        assertEquals(groupMock.findAll(), groupServiceImpl.getAllGroups());
        assertEquals(studentMock.findAll(), groupServiceImpl.getAllStudents());
        assertEquals(professorMock.findAll(), groupServiceImpl.getAllProfessors());
        assertEquals(subjectMock.findAll(), groupServiceImpl.getAllSubjects());
    }
    
    @Test
    public void checkMethodSave() throws UniversityServiceException {
        initMocksForManyObjects();
        Group group = Group.builder().id(4).name("FF-22").build();
        List<Group> groupsList = groupServiceImpl.getAllGroups();
        groupsList.add(group);
        groupServiceImpl.saveGroup(groupsList);
        Student student = Student.builder().id(4).firstName("Yaroslav").lastName("Voronovskyi").build();
        List<Student> studentsList = groupServiceImpl.getAllStudents();
        studentsList.add(student);
        groupServiceImpl.saveStudent(studentsList);
        Professor professor = Professor.builder().id(4).firstName("Yaroslav").lastName("Voronovskyi").build();
        List<Professor> professorsList = groupServiceImpl.getAllProfessors();
        professorsList.add(professor);
        groupServiceImpl.saveProfessor(professorsList);
        Subject subject = Subject.builder().id(4).name("Java").description("Programming language").build();
        List<Subject> subjectsList = groupServiceImpl.getAllSubjects();
        subjectsList.add(subject);
        groupServiceImpl.saveSubject(subjectsList);
        assertTrue(groupServiceImpl.getAllGroups().contains(group));
        assertTrue(groupServiceImpl.getAllStudents().contains(student));
        assertTrue(groupServiceImpl.getAllProfessors().contains(professor));
        assertTrue(groupServiceImpl.getAllSubjects().contains(subject));
    }
   
    private void initMocksForOneObject() {
        Mockito.when(groupMock.getById(1)).thenReturn(createFakeGroup());
        Mockito.when(studentMock.getById(1)).thenReturn(createFakeStudent());
        Mockito.when(professorMock.getById(1)).thenReturn(createFakeProfessor());
        Mockito.when(subjectMock.getById(1)).thenReturn(createFakeSubject());
    }
    
    private void initMocksForManyObjects() {
        Mockito.when(groupMock.findAll()).thenReturn(createFakeGroupsList());
        Mockito.when(studentMock.findAll()).thenReturn(createFakeStudentsList());
        Mockito.when(professorMock.findAll()).thenReturn(createFakeProfessorsList());
        Mockito.when(subjectMock.findAll()).thenReturn(createFakeSubjectsList());
    }
    
    private Group createFakeGroup() {
        return Group.builder().id(1).name("QW-11")
                .studentList(Arrays.asList(Student.builder().id(1).firstName("Viktor").lastName("Tsygankov").build()))
                .build();
    }
    
    private Student createFakeStudent() {
        return Student.builder().id(1).firstName("Viktor").lastName("Tsygankov").build();
    }
    
    private Professor createFakeProfessor() {
        return Professor.builder().id(1).firstName("Viktor").lastName("Rebrov")
                .subjectList(Arrays.asList(Subject.builder().id(1).name("Math").description(
                "Math includes the study of such topics as number theory, algebra, geometry and mathematical analysis")
                .build()))
                .build();
    }
    
    private Subject createFakeSubject() {
        return Subject.builder().id(1).name("Math").description(
                "Math includes the study of such topics as number theory, algebra, geometry and mathematical analysis")
                .build();
    }
    
    private List<Group> createFakeGroupsList() {
        List<Group> groupsList = new ArrayList<>();
        Group groupFirst = Group.builder().id(1).name("QW-11").build();
        Group groupSecond = Group.builder().id(2).name("ER-12").build();
        Group groupThird = Group.builder().id(3).name("TY-21").build();
        groupsList.add(groupFirst);
        groupsList.add(groupSecond);
        groupsList.add(groupThird);
        return groupsList;
    }
    
    private List<Student> createFakeStudentsList() {
        List<Student> studentsList = new ArrayList<>();
        Student studentFirst = Student.builder().id(1).firstName("Viktor").lastName("Tsygankov").build();
        Student studentSecond = Student.builder().id(2).firstName("Denys").lastName("Tsygankov").build();
        Student studentThird = Student.builder().id(3).firstName("Olena").lastName("Tsygankov").build();
        studentsList.add(studentFirst);
        studentsList.add(studentSecond);
        studentsList.add(studentThird);
        return studentsList;
    }
    
    private List<Professor> createFakeProfessorsList() {
        List<Professor> professorsList = new ArrayList<>();
        Professor professorFirst = Professor.builder().id(1).firstName("Viktor").lastName("Rebrov").build();
        Professor professorSecond = Professor.builder().id(1).firstName("Denys").lastName("Rebrov").build();
        Professor professorThird = Professor.builder().id(1).firstName("Olena").lastName("Rebrov").build();
        professorsList.add(professorFirst);
        professorsList.add(professorSecond);
        professorsList.add(professorThird);
        return professorsList;
    }
    
    private List<Subject> createFakeSubjectsList() {
        List<Subject> subjectsList = new ArrayList<>();
        Subject subjectFirst = Subject.builder().id(1).name("Math").description(
                "Math includes the study of such topics as number theory, algebra, geometry and mathematical analysis")
                .build();
        Subject subjectSecond = Subject.builder().id(2).name("Physics").description(
                "Physics is the natural science that studies matter, its motion and behavior through space and time, and the related entities of energy and force")
                .build();
        Subject subjectThird = Subject.builder().id(3).name("Chemistry").description(
                "Chemistry is the scientific discipline involved with elements and compounds composed of atoms, molecules and ions: their composition, structure, properties, behavior and the changes they undergo during a reaction with other substances")
                .build();
        subjectsList.add(subjectFirst);
        subjectsList.add(subjectSecond);
        subjectsList.add(subjectThird);
        return subjectsList;
    }
}
