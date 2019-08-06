package com.sinaungoding.web.demo.controller;

import com.sinaungoding.web.demo.dao.MahasiswaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MahasiswaController {
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
}
