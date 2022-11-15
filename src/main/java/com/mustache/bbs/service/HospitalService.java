package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }
    public HospitalResponse getHospital(Integer id){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        Hospital hospital = optionalHospital.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital);
        if(hospital.getBusinessStatus()==1)hospitalResponse.setBusinessStatusName("영업중");
        else if(hospital.getBusinessStatus()==3)hospitalResponse.setBusinessStatusName("폐업");
        else hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        return hospitalResponse;
    }

}
