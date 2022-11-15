package com.mustache.bbs.controller;


import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

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
    public String list(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        model.addAttribute("hospitals",hospitals);
        model.addAttribute("previous",pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        return "hospitals/list";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable(name="id") Integer id, Model model){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if(optionalHospital.isEmpty())return "error";
        model.addAttribute("hospital",optionalHospital.get());
        System.out.println(optionalHospital.get());
        return "hospitals/find";
    }

}
