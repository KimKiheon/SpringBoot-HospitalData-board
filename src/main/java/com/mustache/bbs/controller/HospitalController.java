package com.mustache.bbs.controller;


import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hospital")
public class HospitalController {
    private final HospitalRepository hospitalRepository;

    @GetMapping("")
    public String Hello() {
        return "Connection Test";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Hospital> hospitals = hospitalRepository.findAll();
        model.addAttribute("hospitals",hospitals);
        return "hospitals/list";
    }

}
