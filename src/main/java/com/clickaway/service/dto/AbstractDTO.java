package com.clickaway.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
abstract class AbstractDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "Unique ID of Data Transfer Object")
    private Long id;
    @ApiModelProperty(value = "Title of Data Transfer Object")
    private String title;
}
