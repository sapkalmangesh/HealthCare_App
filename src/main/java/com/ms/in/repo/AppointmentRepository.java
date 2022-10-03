package com.ms.in.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.in.entity.Appointment;

public interface AppointmentRepository extends JpaRepository< Appointment, Long> {

}
