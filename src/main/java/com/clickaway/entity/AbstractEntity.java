package com.clickaway.entity;

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

// https://stackoverflow.com/a/37727206/2966857
//@Data
@Getter
@Setter
@MappedSuperclass
public class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(nullable = false, length = 55, unique = true)
    private String title;
    @CreatedDate
    private Instant createdDate = Instant.now();
    @LastModifiedDate
    private Instant updatedDate = Instant.now();
}
