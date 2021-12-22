package com.gmail.voronovskyi.yaroslav.testtaskuniversity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.CourseDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.GroupDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.SubjectDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.dao.TimeTableDAO;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Course;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Group;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Subject;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.TimeTable;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.exception.UniversityServiceException;

public class CourseServiceImplTest extends AbstractServiceImplTest {
    private static final DateTimeFormatter TIME_FORMATT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Mock
    private CourseDAO courseMock;
    @Mock
    private GroupDAO groupMock;
    @Mock
    private SubjectDAO subjectMock;
    @Mock
    private TimeTableDAO timeTableMock;
    @InjectMocks
    private CourseServiceImpl courseServiceImpl;
    
    @Test
    public void checkMethodGet() throws UniversityServiceException {
        initMocksForOneObject();
        assertEquals(courseServiceImpl.getCourse(1), createFakeCourse());
    }
    
    @Test
    public void checkMethodGetAll() throws UniversityServiceException {
        initMocksForManyObjects();
        List<Course> expectedCoursesList = courseServiceImpl.getAllCourses();
        List<Course> actualCoursesList = createFakeCoursesList();
        assertEquals(expectedCoursesList.get(0), actualCoursesList.get(0));
        assertEquals(expectedCoursesList.get(1), actualCoursesList.get(1));
        assertEquals(expectedCoursesList.get(2), actualCoursesList.get(2));
    }
    
    @Test
    public void checkMethodDelete() throws UniversityServiceException {
        initMocksForManyObjects();
        Course course = Course.builder().id(1).build();
        courseServiceImpl.deleteCourse(course);
        assertFalse(courseServiceImpl.getAllCourses().contains(course));
    }
    
    @Test
    public void checkMethodUpdate() throws UniversityServiceException {
        initMocksForOneObject();
        Course course = Course.builder().id(1).name("SEVENTH").build();
        courseServiceImpl.updateCourse(course);
        assertEquals(course.getName(), "SEVENTH");
    }
    
    @Test
    public void checkMethodSave() throws UniversityServiceException {
        initMocksForManyObjects();
        Course course = Course.builder().id(4).name("FOURTH").build();
        List<Course> coursesList = courseServiceImpl.getAllCourses();
        coursesList.add(course);
        courseServiceImpl.saveCourse(coursesList);     
        assertTrue(courseServiceImpl.getAllCourses().contains(course));
    }
  
    private void initMocksForOneObject() {
        Mockito.when(courseMock.getById(1)).thenReturn(createFakeCourse());
        Mockito.when(groupMock.getById(1)).thenReturn(createFakeGroup());
        Mockito.when(subjectMock.getById(1)).thenReturn(createFakeSubject());
        Mockito.when(timeTableMock.getById(1)).thenReturn(createFakeTimeTable());
    }
    
    private void initMocksForManyObjects() {
        Mockito.when(courseMock.findAll()).thenReturn(createFakeCoursesList());
        Mockito.when(groupMock.findAll()).thenReturn(createFakeGroupsList());
        Mockito.when(subjectMock.findAll()).thenReturn(createFakeSubjectsList());
        Mockito.when(timeTableMock.findAll()).thenReturn(createFakeTimeTablesList());
    }
    
    private List<Course> createFakeCoursesList() {
        List<Course> coursesList = new ArrayList<>();
        Course courseFirst = Course.builder().id(1).name("FIRST")
                .timeTableList(Arrays
                        .asList(TimeTable.builder().id(1).startTime(LocalTime.parse("07:00:00", TIME_FORMATT))
                                .endTime(LocalTime.parse("08:30:00", TIME_FORMATT))
                                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build()))
                .build();
        Course courseSecond = Course.builder().id(2).name("SECOND")
                .timeTableList(Arrays
                        .asList(TimeTable.builder().id(1).startTime(LocalTime.parse("09:00:00", TIME_FORMATT))
                                .endTime(LocalTime.parse("10:30:00", TIME_FORMATT))
                                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build()))
                .build();
        Course courseThird = Course.builder().id(3).name("THIRD")
                .timeTableList(Arrays
                        .asList(TimeTable.builder().id(1).startTime(LocalTime.parse("11:00:00", TIME_FORMATT))
                                .endTime(LocalTime.parse("12:30:00", TIME_FORMATT))
                                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build()))
                .build();
        coursesList.add(courseFirst);
        coursesList.add(courseSecond);
        coursesList.add(courseThird);
        return coursesList;
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
    
    private Course createFakeCourse() {
        return Course.builder().id(1).name("FIRST")
                .groupList(Arrays.asList(Group.builder().id(1).name("QW-11").build()))
                .timeTableList(Arrays
                        .asList(TimeTable.builder().id(1).startTime(LocalTime.parse("07:00:00", TIME_FORMATT))
                                .endTime(LocalTime.parse("08:30:00", TIME_FORMATT))
                                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build()))
                .build();
    }

    private Group createFakeGroup() {
        return Group.builder().id(1).name("QW-11").build();
    }
    
    private TimeTable createFakeTimeTable() {
        return TimeTable.builder().id(1).startTime(LocalTime.parse("07:00:00", TIME_FORMATT))
                .endTime(LocalTime.parse("08:30:00", TIME_FORMATT))
                .date(LocalDate.parse("2021-04-15", DATE_FORMATT)).build();
    }
    
    private Subject createFakeSubject() {
        return Subject.builder().id(1).name("Math").description(
                "Math includes the study of such topics as number theory, algebra, geometry and mathematical analysis")
                .build();
    }
}
