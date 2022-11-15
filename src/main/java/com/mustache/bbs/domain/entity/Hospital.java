package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hospitals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Hospital {
    @Id
    private Integer id;
    @Column(name = "management_number")
    private String managementNumber;

    @Column(name = "business_status")
    private int businessStatus;

    @Column(name = "phone")
    private String phone;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "business_type_name")
    private String businessTypeName;

    @Column(name = "healthcare_provider_count")
    private int healthcareProviderCount;

    @Column(name = "patient_room_count")
    private int patientRoomCount;

    @Column(name = "total_number_of_beds")
    private int totalNumberOfBeds;

    @Column(name = "total_area_size")
    private float totalAreaSize;

    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(), hospital.getRoadNameAddress(), hospital.getHospitalName(),
                hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(), hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize());
    }
}
