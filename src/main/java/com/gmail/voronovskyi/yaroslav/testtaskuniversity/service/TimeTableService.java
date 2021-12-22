package com.gmail.voronovskyi.yaroslav.testtaskuniversity.service;

import java.util.List;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Auditory;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.TimeTable;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.exception.UniversityServiceException;

public interface TimeTableService {
    public TimeTable getTimeTable(int id) throws UniversityServiceException;
    
    public List<TimeTable> getAllTimeTables() throws UniversityServiceException;
    
    public List<TimeTable> saveTimeTable(List<TimeTable> timeTablesList) throws UniversityServiceException;
    
    public TimeTable updateTimeTable(TimeTable timeTable) throws UniversityServiceException;
    
    public void delete(TimeTable timeTable) throws UniversityServiceException;
    
    public Auditory getAuditory(int id) throws UniversityServiceException;
    
    public List<Auditory> getAllAuditories() throws UniversityServiceException;
    
    public List<Auditory> saveAuditory(List<Auditory> auditoriesList) throws UniversityServiceException;
    
    public Auditory updateAuditory(Auditory auditory) throws UniversityServiceException;
    
    public void deleteAuditory(Auditory auditory) throws UniversityServiceException;
}
