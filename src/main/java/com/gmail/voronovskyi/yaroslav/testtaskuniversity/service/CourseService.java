package com.gmail.voronovskyi.yaroslav.testtaskuniversity.service;

import java.util.List;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Course;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.exception.UniversityServiceException;

public interface CourseService {
    public Course getCourse(int id) throws UniversityServiceException;
    
    public List<Course> getAllCourses() throws UniversityServiceException;
    
    public List<Course> saveCourse(List<Course> coursesList) throws UniversityServiceException;
    
    public Course updateCourse(Course course) throws UniversityServiceException;
    
    public void deleteCourse(Course course) throws UniversityServiceException;
}
