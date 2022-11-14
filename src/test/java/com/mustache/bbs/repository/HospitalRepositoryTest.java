package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.repository.HospitalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;
    @Test
    @DisplayName("BusinessTypenName이 보건소, 보건지소, 보건진료소인 데이터가 잘 나오는가?")
    void findByBusinessTypeNameIn(){
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        System.out.println("inClues 11111111:"+ inClues.size());
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        System.out.println("inclus size:"+ inClues.size());
        for(Hospital hospital : hospitals){
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    void containing() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("송파구");
        for(Hospital hospital : hospitals){
            printHospitalName(hospital);
            printHospitalGetRoadNameAddress(hospital);
            System.out.println();
        }
    }
    @Test
    void startsWith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameStartsWith("경희");// 가톨릭 서울 연세 경희
        for(Hospital hospital : hospitals){
            printHospitalName(hospital);
            System.out.println();
        }
    }
    @Test
    void endsWith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameEndingWith("병원");// 의원, 병원, 이비인후과, 치과
        for(Hospital hospital : hospitals){
            printHospitalName(hospital);
            System.out.println();
        }
    }
    @Test
    void bedCount(){
        List<Hospital> hospitals=hospitalRepository.findByHealthcareProviderCountBetween(10,20);
        for(Hospital hospital : hospitals){
            printHospitalName(hospital);
            printHospitalGetRoadNameAddress(hospital);
            printHospitalHealthcareProviderCount(hospital);
            System.out.println();
        }
    }
    void printHospital(List<Hospital> hospitals) {
        for (Hospital hospital : hospitals) {
            System.out.printf("%s | %s %f %d\n", hospital.getHospitalName(), hospital.getRoadNameAddress(), hospital.getTotalAreaSize(),hospital.getHealthcareProviderCount());
        }
        System.out.println(hospitals.size());
    }
    void printHospitalName(Hospital hospital){
        System.out.printf(hospital.getHospitalName()+" | ");
    }
    void printHospitalGetRoadNameAddress(Hospital hospital){
        System.out.printf(hospital.getRoadNameAddress()+" ");
    }
    void printHospitalHealthcareProviderCount(Hospital hospital){
        System.out.printf(hospital.getHealthcareProviderCount()+" ");
    }
}
