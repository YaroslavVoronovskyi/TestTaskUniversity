package com.gmail.voronovskyi.yaroslav.testtaskuniversity.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.CourseDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Course;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.exception.UniversityServiceException;

@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
    
    private CourseDAO courseDAO;
    
    @Autowired
    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Course getCourse(int id) throws UniversityServiceException {
        LOGGER.debug("Try get course {}", id);
        try {
            LOGGER.debug("Course was successfully got {}", id);
            return courseDAO.getById(id);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Course " + id + " couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Course> getAllCourses() throws UniversityServiceException {
        LOGGER.debug("Try get all courses");
        try {
            LOGGER.debug("All courses was successfully got");
            return courseDAO.findAll();
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Courses list couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional
    public List<Course> saveCourse(List<Course> coursesList) throws UniversityServiceException {
        LOGGER.debug("Try save courses list");
        try {
            LOGGER.debug("All courses was successfully saved");
            return courseDAO.saveAll(coursesList);          
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Courses list " + coursesList + " couldn't be saved", exception);
        }
    }
    
    @Override
    @Transactional
    public Course updateCourse(Course course) throws UniversityServiceException {
        LOGGER.debug("Try update course {}", course);
        try {
            LOGGER.debug("Course was successfully updated {}", course);
            return courseDAO.save(course);          
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Course " + course + " couldn't be updated", exception);
        }
    }
    
    @Override
    @Transactional
    public void deleteCourse(Course course) throws UniversityServiceException {
        LOGGER.debug("Try delete course {}", course);
        try {
            courseDAO.deleteById(course.getId());
            LOGGER.debug("Course was successfully deleted {}", course);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Course " + course + " couldn't be deleted", exception);
        }
    } 
}
