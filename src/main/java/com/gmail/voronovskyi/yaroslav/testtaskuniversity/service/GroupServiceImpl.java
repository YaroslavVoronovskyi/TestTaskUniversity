package com.gmail.voronovskyi.yaroslav.testtaskuniversity.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.GroupDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.ProfessorDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.StudentDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.SubjectDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Group;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Professor;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Student;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Subject;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.exception.UniversityServiceException;

@Service
public class GroupServiceImpl implements GroupService {   
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupServiceImpl.class);
    
    private GroupDAO groupDAO;
    private StudentDAO studentDAO;
    private ProfessorDAO professorDAO;
    private SubjectDAO subjectDAO;
    
    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO, StudentDAO studentDAO, ProfessorDAO professorDAO,
            SubjectDAO subjectDAO) {
        this.groupDAO = groupDAO;
        this.studentDAO = studentDAO;
        this.professorDAO = professorDAO;
        this.subjectDAO = subjectDAO;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Group getGroup(int id) throws UniversityServiceException {
        LOGGER.debug("Try get group {}", id);
        try {
            LOGGER.debug("Group was successfully got {}", id);
            return groupDAO.getById(id);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Group " + id + " couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Group> getAllGroups() throws UniversityServiceException {
        LOGGER.debug("Try get all groups");
        try {
            LOGGER.debug("All groups was successfully got");
            return groupDAO.findAll();
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Groups list  couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional
    public List<Group> saveGroup(List<Group> groupsList) throws UniversityServiceException {
        LOGGER.debug("Try save groups list");
        try {
            LOGGER.debug("All groups was successfully saved");
            return groupDAO.saveAll(groupsList);           
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Groups list " + groupsList + " couldn't be saved",
                    exception);
        }
    }
    
    @Override
    @Transactional
    public Group updateGroup(Group group) throws UniversityServiceException {
        LOGGER.debug("Try update group {}", group);
        try {
            LOGGER.debug("Group was successfully updated {}", group);
            return groupDAO.save(group);            
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Course " + group + " couldn't be updated", exception);
        }
    }
    
    @Override
    @Transactional
    public void deleteGroup(Group group) throws UniversityServiceException {
        LOGGER.debug("Try delete group {}", group);
        try {
            groupDAO.deleteById(group.getId());
            LOGGER.debug("Group was successfully deleted {}", group);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Group " + group + " couldn't be deleted", exception);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Student getStudent(int id) throws UniversityServiceException {
        LOGGER.debug("Try get student {}", id);
        try {
            LOGGER.debug("Student was successfully got {}", id);
            return studentDAO.getById(id);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Student " + id + " couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() throws UniversityServiceException {
        LOGGER.debug("Try get all students");
        try {
            LOGGER.debug("All students was successfully got");
            return studentDAO.findAll();
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Students list  couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional
    public List<Student> saveStudent(List<Student> studentsList) throws UniversityServiceException {
        LOGGER.debug("Try save students list");
        try {
            LOGGER.debug("All students was successfully saved");
            return studentDAO.saveAll(studentsList);           
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Students list " + studentsList + " couldn't be saved",
                    exception);
        }
    }
    
    @Override
    @Transactional
    public Student updateStudent(Student student) throws UniversityServiceException {
        LOGGER.debug("Try update student {}", student);
        try {
            LOGGER.debug("Student was successfully updated {}", student);
            return studentDAO.save(student);           
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Student " + student + " couldn't be updated", exception);
        }
    }
    
    @Override
    @Transactional
    public void deleteStudent(Student student) throws UniversityServiceException {
        LOGGER.debug("Try delete student {}", student);
        try {
            studentDAO.deleteById(student.getId());
            LOGGER.debug("Student was successfully deleted {}", student);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Student " + student + " couldn't be deleted", exception);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Professor getProfessor(int id) throws UniversityServiceException {
        LOGGER.debug("Try get professor {}", id);
        try {
            LOGGER.debug("Professor was successfully got {}", id);
            return professorDAO.getById(id);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Professor " + id + " couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Professor> getAllProfessors() throws UniversityServiceException {
        LOGGER.debug("Try get all professors");
        try {
            LOGGER.debug("All professors was successfully got");
            return professorDAO.findAll();
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Professors list  couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional
    public List<Professor> saveProfessor(List<Professor> professorsList) throws UniversityServiceException {
        LOGGER.debug("Try save professors list");
        try {
            LOGGER.debug("All professors was successfully saved");
            return professorDAO.saveAll(professorsList);            
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Professors list " + professorsList + " couldn't be saved",
                    exception);
        }
    }
    
    @Override
    @Transactional
    public Professor updateProfessor(Professor professor) throws UniversityServiceException {
        LOGGER.debug("Try update professor {}", professor);
        try {
            LOGGER.debug("Professor was successfully updated {}", professor);
            return professorDAO.save(professor);           
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Professor " + professor + " couldn't be updated", exception);
        }
    }
    
    @Override
    @Transactional
    public void deleteProfessor(Professor professor) throws UniversityServiceException {
        LOGGER.debug("Try delete professor {}", professor);
        try {
            professorDAO.deleteById(professor.getId());
            LOGGER.debug("Professor was successfully deleted {}", professor);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Professor " + professor + " couldn't be deleted", exception);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Subject getSubject(int id) throws UniversityServiceException {
        LOGGER.debug("Try get subject {}", id);
        try {
            LOGGER.debug("Subject was successfully got {}", id);
            return subjectDAO.getById(id);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Subject " + id + " couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Subject> getAllSubjects() throws UniversityServiceException {
        LOGGER.debug("Try get all subjects");
        try {
            LOGGER.debug("All subjects was successfully got");
            return subjectDAO.findAll();
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Subject list  couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional
    public List<Subject> saveSubject(List<Subject> subjectsList) throws UniversityServiceException {
        LOGGER.debug("Try save subjects list");
        try {
            LOGGER.debug("All subjects was successfully saved");
            return subjectDAO.saveAll(subjectsList);            
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Subjects list " + subjectsList + " couldn't be saved",
                    exception);
        }
    }
    
    @Override
    @Transactional
    public Subject updateSubject(Subject subject) throws UniversityServiceException {
        LOGGER.debug("Try update subject {}", subject);
        try {
            LOGGER.debug("Subject was successfully updated {}", subject);
            return subjectDAO.save(subject);        
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Subject " + subject + " couldn't be updated", exception);
        }
    }
    
    @Override
    @Transactional
    public void deleteSubject(Subject subject) throws UniversityServiceException {
        LOGGER.debug("Try delete subject {}", subject);
        try {
            subjectDAO.deleteById(subject.getId());
            LOGGER.debug("Subject was successfully deleted {}", subject);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Subject " + subject + " couldn't be deleted", exception);
        }
    }
}
