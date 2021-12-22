package com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.rest.exception.UniversityRestControllerException;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.web.rest.dto.input.AuditoryDtoIn;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.web.rest.dto.output.AuditoryDtoOut;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.model.Auditory;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.TimeTableService;
import com.gmail.voronovskyi.yaroslav.testtaskuniversity.service.exception.UniversityServiceException;

@RestController
@RequestMapping("/rest/auditory")
public class AuditoriesRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoriesRestController.class);
    private static final String ID = "id";
    
    private TimeTableService timeTableService;
    private ModelMapper modelMapper;
    
    @Autowired
    public AuditoriesRestController(TimeTableService timeTableService, ModelMapper modelMapper) {
        this.timeTableService = timeTableService;
        this.modelMapper = modelMapper;
    }
    
    @GetMapping()
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public List<AuditoryDtoOut> getAllAuditories() throws UniversityRestControllerException, UniversityServiceException {
        LOGGER.debug("Try show all auditories");
        List<AuditoryDtoOut> auditoriesDtoOutList = new ArrayList<>();
        try {
        auditoriesDtoOutList = convertToDtoList(timeTableService.getAllAuditories());
        } catch (MappingException exception) {
            throw new UniversityRestControllerException("Auditories not found!", exception);
        }
        LOGGER.debug("All auditories was successfully showed");
        return auditoriesDtoOutList;
    }
    
    @GetMapping("/{id}")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public AuditoryDtoOut getAuditory(@PathVariable(ID) int id) throws UniversityRestControllerException, UniversityServiceException {
        LOGGER.debug("Try show auditory {}", id);
        AuditoryDtoOut auditoryDtoOut = null;
        try {
            auditoryDtoOut = convertToDto(timeTableService.getAuditory(id));
        } catch (MappingException exception) {
            throw new UniversityRestControllerException("Auditory with ID " + id + " not found!", exception);
        }
        LOGGER.debug("Auditory was successfully showed {}", id);
        return auditoryDtoOut;
    }
    
    @PostMapping()
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public List<AuditoryDtoOut> createAuditory(@RequestBody @Valid AuditoryDtoIn auditoryDtoIn) throws UniversityServiceException {
        LOGGER.debug("Try create new auditory");
        List<Auditory> auditoriesList = timeTableService.saveAuditory(convertToEntityList(
                    Arrays.asList(AuditoryDtoIn.builder().capacity(auditoryDtoIn.getCapacity()).build())));
        LOGGER.debug("New auditory was successfully saved from UI form to DB");
        return convertToDtoList(auditoriesList);
    }
       
    @PutMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE) 
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public AuditoryDtoOut updateAuditory(@PathVariable(ID) int id, Integer capacity, @RequestBody @Valid AuditoryDtoIn auditoryDtoIn)
            throws UniversityRestControllerException, UniversityServiceException {
        LOGGER.debug("Try update auditory");
        Auditory auditory = null;
        try {
            auditory = timeTableService.getAuditory(id);
            auditory = timeTableService.updateAuditory(convertToEntity(
                    AuditoryDtoIn.builder().id(id).capacity(auditoryDtoIn.getCapacity()).build()));
        } catch (EntityNotFoundException exception) {
            throw new UniversityRestControllerException("Auditory with " + id + " can't be update ", exception);
        }
        LOGGER.debug("Auditory was successfully updated from UI form to DB");
        return convertToDto(auditory);
    }
    
    @DeleteMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public void deleteAuditory(@PathVariable(ID) int id, AuditoryDtoIn auditoryDtoIn) throws  UniversityRestControllerException, UniversityServiceException {
        LOGGER.debug("Try delete auditory {}", id);
        try {
            timeTableService.deleteAuditory(
                    convertToEntity(AuditoryDtoIn.builder().id(auditoryDtoIn.getId()).build()));
        } catch (EmptyResultDataAccessException exception) {
            throw new UniversityRestControllerException("Auditory with " + id + " can't be delete ", exception);
        }
        LOGGER.debug("Auditory was successfully deleted from UI form to DB {}", id);
    }
    
    private AuditoryDtoOut convertToDto(Auditory auditory) {
        return modelMapper.map(auditory, AuditoryDtoOut.class);
    }
    
    private Auditory convertToEntity(AuditoryDtoIn auditoryDtoIn) {
        return modelMapper.map(auditoryDtoIn, Auditory.class);
    }
    
    private List<AuditoryDtoOut> convertToDtoList(List<Auditory> auditoriesList) {
        return auditoriesList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    
    private List<Auditory> convertToEntityList(List<AuditoryDtoIn> auditoriesDtoInList) {
        return auditoriesDtoInList.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
