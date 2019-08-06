package com.sinaungoding.web.demo.dao;

import com.sinaungoding.web.demo.entity.Mahasiswa;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MahasiswaDao extends PagingAndSortingRepository<Mahasiswa, String> {
}
