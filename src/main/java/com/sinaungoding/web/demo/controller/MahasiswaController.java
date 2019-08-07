package com.sinaungoding.web.demo.controller;

import com.sinaungoding.web.demo.dao.MahasiswaDao;
import com.sinaungoding.web.demo.entity.Mahasiswa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
public class MahasiswaController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MahasiswaController.class.getName());

    @Autowired
    private MahasiswaDao mahasiswaDao;

    @GetMapping("/index")
    public ModelMap getAll(Pageable pageable) {
        return new ModelMap().addAttribute("mahasiswas", mahasiswaDao.findAll(pageable));
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @GetMapping("/mahasiswa/form")
    public ModelMap tampilFormedit(@RequestParam(required = false, value = "id") Mahasiswa mahasiswa) {
        if (mahasiswa == null) {
            mahasiswa = new Mahasiswa();
        }
        return new ModelMap().addAttribute("mahasiswa", mahasiswa);
    }

    @PostMapping("/mahasiswa/form")
    public String editMahasiswa(@ModelAttribute @Valid Mahasiswa mahasiswa, BindingResult errors, SessionStatus status) {
        LOGGER.info(mahasiswa.toString());
        LOGGER.info(errors.toString());
        LOGGER.info("" + errors.hasErrors());
        LOGGER.info("" + errors.hasGlobalErrors());
        if (errors.hasErrors())
            return "/mahasiswa/form";
        try {
            mahasiswaDao.save(mahasiswa);
            status.setComplete();
            return "redirect:/index";
        } catch (DataAccessException e) {
            errors.reject("error.object", e.getMessage());
            LOGGER.error(e.getMessage());
            return "/mahasiswa/form";
        }
    }

    @GetMapping("/mahasiswa/detail_form")
    public ModelMap tampilFormDetail(@RequestParam(required = false, value = "id") Mahasiswa mahasiswa) {
        if (mahasiswa == null) {
            mahasiswa = new Mahasiswa();
        }
        return new ModelMap().addAttribute("mahasiswa", mahasiswa);
    }

    @DeleteMapping("/mahasiswa/hapus")
    public String hapusMahasiswa(@RequestParam(name = "id") String id) {
        mahasiswaDao.deleteById(id);
        return "redirect:/index";
    }
}
