package com.clickaway.service.dto;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
abstract class AbstractDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
}
