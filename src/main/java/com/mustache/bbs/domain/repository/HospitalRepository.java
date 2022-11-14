package com.mustache.bbs.domain.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByRoadNameAddressContaining(String keyword);
    List<Hospital> findByHospitalNameStartsWith(String keyword);
    List<Hospital> findByHospitalNameEndingWith(String keyword);
    List<Hospital> findByHealthcareProviderCountBetween(Integer from, Integer to);



}
