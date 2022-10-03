package com.ms.in.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Data
@Table(name="appointment_tab")
public class Appointment {
	
	@Id
	@Column(name="appoint_id_col")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "doc_fk_col")
	private Doctor doctor;
	
	@Column(name="appoint_date_col")
	@DateTimeFormat(iso=ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="appoint_slot_col")
	private Integer slots;
	
	@Column(name="appoint_details_col")
	private String details;
	
	@Column(name="appiont_fee_col")
	private Double fee;

}
