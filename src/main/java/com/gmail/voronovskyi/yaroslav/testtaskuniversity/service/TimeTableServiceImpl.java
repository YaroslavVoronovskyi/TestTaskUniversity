package com.gmail.voronovskyi.yaroslav.testtaskuniversity.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.AuditoryDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.TimeTableDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Auditory;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.TimeTable;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.exception.UniversityServiceException;

@Service
public class TimeTableServiceImpl implements TimeTableService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTableServiceImpl.class);
    
    private TimeTableDAO timeTableDAO;
    private AuditoryDAO auditoryDAO;
    
    @Autowired
    public TimeTableServiceImpl(TimeTableDAO timeTableDAO, AuditoryDAO auditoryDAO) {
        this.timeTableDAO = timeTableDAO;
        this.auditoryDAO = auditoryDAO;
    }
    
    @Override
    @Transactional(readOnly = true)
    public TimeTable getTimeTable(int id) throws UniversityServiceException {
        LOGGER.debug("Try get timeTable {}", id);
        try {
            LOGGER.debug("TimeTable was successfully got {}", id);
            return timeTableDAO.getById(id);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("TimeTable " + id + " couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TimeTable> getAllTimeTables() throws UniversityServiceException {
        LOGGER.debug("Try get all timeTables");
        try {
            LOGGER.debug("All timeTables was successfully got");
            return timeTableDAO.findAll();
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("TimeTables list couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional
    public List<TimeTable> saveTimeTable(List<TimeTable> timeTablesList) throws UniversityServiceException {
        LOGGER.debug("Try save timeTables list");
        try {
            LOGGER.debug("All timeTables was successfully saved");
            return timeTableDAO.saveAll(timeTablesList);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("TimeTables list " + timeTablesList + " couldn't be saved", exception);
        }
    }
    
    @Override
    @Transactional
    public TimeTable updateTimeTable(TimeTable timeTable) throws UniversityServiceException {
        LOGGER.debug("Try update timeTable {}", timeTable);
        try {
            LOGGER.debug("TimeTable was successfully updated {}", timeTable);
            return  timeTableDAO.save(timeTable);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("TimeTable " + timeTable + " couldn't be updated", exception);
        }
    }
    
    @Override
    @Transactional
    public void delete(TimeTable timeTable) throws UniversityServiceException {
        LOGGER.debug("Try delete timeTable {}", timeTable);
        try {
            timeTableDAO.deleteById(timeTable.getId());
            LOGGER.debug("TimeTable was successfully deleted {}", timeTable);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("TimeTable " + timeTable + " couldn't be deleted", exception);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Auditory getAuditory(int id) throws UniversityServiceException {
        LOGGER.debug("Try get auditory {}", id);
        try {
            LOGGER.debug("Auditory was successfully got {}", id);
            return auditoryDAO.getById(id);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Auditory " + id + " couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Auditory> getAllAuditories() throws UniversityServiceException {
        LOGGER.debug("Try get all auditories");
        try {
            LOGGER.debug("All auditories was successfully got");
            return auditoryDAO.findAll();
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Auditories list couldn't be got", exception);
        }
    }
    
    @Override
    @Transactional
    public List<Auditory> saveAuditory(List<Auditory> auditoriesList) throws UniversityServiceException {
        LOGGER.debug("Try save courses list");
        try {
            LOGGER.debug("All auditories was successfully saved");
            return auditoryDAO.saveAll(auditoriesList);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Auditories list " + auditoriesList + " couldn't be saved", exception);
        }
    }
    
    @Override
    @Transactional
    public Auditory updateAuditory(Auditory auditory) throws UniversityServiceException {
        LOGGER.debug("Try update auditory {}", auditory);
        try {
            LOGGER.debug("Auditory was successfully updated {}", auditory);
            return auditoryDAO.save(auditory);            
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Auditory " + auditory + " couldn't be updated", exception);
        }
    }
    
    @Override
    @Transactional
    public void deleteAuditory(Auditory auditory) throws UniversityServiceException {
        LOGGER.debug("Try delete auditory {}", auditory);
        try {
            auditoryDAO.deleteById(auditory.getId());
            LOGGER.debug("Auditory was successfully deleted {}", auditory);
        } catch (PersistenceException exception) {
            throw new UniversityServiceException("Auditory " + auditory + " couldn't be deleted", exception);
        }
    }
}
