package com.gmail.voronovskyi.yaroslav.testtaskuniversity.controller.web.rest.dto.input;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AuditoryDtoIn implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    
    @Min(value = 20, message = "Capacity should be in range 20 and 50")
    @Max(value = 50, message = "Capacity should be in range 20 and 50")
    private int capacity;
}
