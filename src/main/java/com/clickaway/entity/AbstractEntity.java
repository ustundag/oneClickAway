package com.clickaway.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique ID of Entity")
    private Long id;
    @ApiModelProperty(value = "Title of Entity")
    //@Column(nullable = false, length = 55, unique = true)
    private String title;
    @CreatedDate
    @ApiModelProperty(value = "Created Date of Entities")
    private Instant createdDate = Instant.now();
    @LastModifiedDate
    @ApiModelProperty(value = "Last Modified Date of Entities")
    private Instant updatedDate = Instant.now();
}
