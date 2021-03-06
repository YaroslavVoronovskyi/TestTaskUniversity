package com.gmail.voronovskyi.yaroslav.testtaskuniversity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.AuditoryDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.TimeTableDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Auditory;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.TimeTable;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.exception.UniversityServiceException;

public class TimeTableServiceImplTest extends AbstractServiceImplTest{
    private static final DateTimeFormatter TIME_FORMATT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Mock
    private TimeTableDAO timeTableMock;
    @Mock
    private AuditoryDAO auditoryMock;
    @InjectMocks
    private TimeTableServiceImpl timeTableServiceImpl;
    
    @Test
    public void checkMethodGet() throws UniversityServiceException {
        initMocksForOneObject();      
        assertEquals(timeTableServiceImpl.getTimeTable(1), createFakeTimeTable());
        assertEquals(timeTableServiceImpl.getAuditory(1), createFakeAuditory());
    }
    
    @Test
    public void checkMethodGetAll() throws UniversityServiceException {
        initMocksForManyObjects();
        List<TimeTable> expectedTimeTablesList = timeTableServiceImpl.getAllTimeTables();
        List<TimeTable> actualTimeTablesList = createFakeTimeTablesList();
        List<Auditory> expectedAuditoriesList = timeTableServiceImpl.getAllAuditories();
        List<Auditory> actualAuditoriesList = createFakeAuditoriesList();
        assertEquals(expectedTimeTablesList.get(0), actualTimeTablesList.get(0));
        assertEquals(expectedTimeTablesList.get(1), actualTimeTablesList.get(1));
        assertEquals(expectedTimeTablesList.get(2), actualTimeTablesList.get(2));
        assertEquals(expectedTimeTablesList.get(0), actualTimeTablesList.get(0));
        assertEquals(expectedTimeTablesList.get(1), actualTimeTablesList.get(1));
        assertEquals(expectedAuditoriesList.get(2), actualAuditoriesList.get(2));
        assertEquals(timeTableServiceImpl.getAllAuditories(), createFakeAuditoriesList());
    }
    
    @Test
    public void checkMethodDelete() throws UniversityServiceException {
        initMocksForManyObjects();
        TimeTable timeTable = TimeTable.builder().id(1).build();
        timeTableServiceImpl.delete(timeTable);
        Auditory auditory = Auditory.builder().id(1).build();
        timeTableServiceImpl.deleteAuditory(auditory);
        assertFalse(timeTableServiceImpl.getAllTimeTables().contains(timeTable));
        assertFalse(timeTableServiceImpl.getAllAuditories().contains(auditory));
    }
    
    @Test
    public void checkMethodUpdate() throws UniversityServiceException {
        initMocksForOneObject();
        TimeTable timeTable = TimeTable.builder().id(1).startTime(LocalTime.parse("07:15:00", TIME_FORMATT))
                .endTime(LocalTime.parse("08:45:00", TIME_FORMATT))
                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build();
        timeTableServiceImpl.updateTimeTable(timeTable);
        Auditory auditory = Auditory.builder().id(1).capacity(15).build();
        timeTableServiceImpl.updateAuditory(auditory);
        assertEquals(timeTableMock.findAll(), timeTableServiceImpl.getAllTimeTables());
        assertEquals(auditoryMock.findAll(), timeTableServiceImpl.getAllAuditories());
    }
    
    @Test
    public void checkMethodSave() throws UniversityServiceException {
        initMocksForManyObjects();
        TimeTable timeTable = TimeTable.builder().id(4).startTime(LocalTime.parse("07:15:00", TIME_FORMATT))
                .endTime(LocalTime.parse("08:45:00", TIME_FORMATT))
                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build();
        List<TimeTable> timeTablesList = timeTableServiceImpl.getAllTimeTables();
        timeTablesList.add(timeTable);
        timeTableServiceImpl.saveTimeTable(timeTablesList);
        Auditory auditory = Auditory.builder().id(4).capacity(25).build();
        List<Auditory> auditoriesList = timeTableServiceImpl.getAllAuditories();
        auditoriesList.add(auditory);
        timeTableServiceImpl.saveAuditory(auditoriesList);
        assertTrue(timeTableServiceImpl.getAllTimeTables().contains(timeTable));
        assertTrue(timeTableServiceImpl.getAllAuditories().contains(auditory));
    }
    
    private void initMocksForOneObject() {
        Mockito.when(timeTableMock.getById(1)).thenReturn(createFakeTimeTable());
        Mockito.when(auditoryMock.getById(1)).thenReturn(createFakeAuditory());
    }
    
    private void initMocksForManyObjects() {
        Mockito.when(timeTableMock.findAll()).thenReturn(createFakeTimeTablesList());
        Mockito.when(auditoryMock.findAll()).thenReturn(createFakeAuditoriesList());
    }
     
    private TimeTable createFakeTimeTable() {
        return TimeTable.builder().id(1).startTime(LocalTime.parse("07:00:00", TIME_FORMATT))
                .endTime(LocalTime.parse("08:30:00", TIME_FORMATT))
                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build();
    }
    
    private Auditory createFakeAuditory() {
        return Auditory.builder().id(1).capacity(10).build();
    }
    
    private List<TimeTable> createFakeTimeTablesList() {
        List<TimeTable> timeTablesList = new ArrayList<>();
        TimeTable timeTableFirst = TimeTable.builder().id(1)
                .startTime(LocalTime.parse("07:00:00", TIME_FORMATT))
                .endTime(LocalTime.parse("08:30:00", TIME_FORMATT))
                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build();
        TimeTable timeTableSecond = TimeTable.builder().id(1)
                .startTime(LocalTime.parse("09:00:00", TIME_FORMATT))
                .endTime(LocalTime.parse("10:30:00", TIME_FORMATT))
                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build();
        TimeTable timeTableThird = TimeTable.builder().id(1)
                .startTime(LocalTime.parse("11:00:00", TIME_FORMATT))
                .endTime(LocalTime.parse("12:30:00", TIME_FORMATT))
                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build();
        timeTablesList.add(timeTableFirst);
        timeTablesList.add(timeTableSecond);
        timeTablesList.add(timeTableThird);
        return timeTablesList;
    }
    
    private List<Auditory> createFakeAuditoriesList() {
        List<Auditory> auditoriesList = new ArrayList<>();
        Auditory auditoryFirst = Auditory.builder().id(1).capacity(10).build();
        Auditory auditorySecond = Auditory.builder().id(1).capacity(15).build();
        Auditory auditoryThird = Auditory.builder().id(1).capacity(20).build();
        auditoriesList.add(auditoryFirst);
        auditoriesList.add(auditorySecond);
        auditoriesList.add(auditoryThird);
        return auditoriesList;
    }
}
