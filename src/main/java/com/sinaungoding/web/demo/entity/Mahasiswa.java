package com.sinaungoding.web.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Mahasiswa {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String nim;
    @NotNull
    @NotBlank
    private String nama;
    private float ipk;
    @NotNull
    @NotBlank
    private String jurusan;
}
