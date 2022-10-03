package com.ms.in.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Data
@Table(name="patient_tab")
public class Patient {
	@Id
	@GeneratedValue
	@Column(name= "pat_id")
	private Long id;
	
	@Column(name="pat_fn_col")
	private String firstName;
	
	@Column(name="pat_ln_col")
	private String lastName;
	
	@Column(name="pat_gen_col")
	private String gender;
	
	@Column(name="pat_phone_col")
	private Long phone;
	
	@Column(name="pat_eMail_col")
	private String eMail;
	
	@Column(name="pat_dob_col")
	@DateTimeFormat(iso=ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name = "mar_status_col")
	private String maritalStatus;
	
	@Column(name="pat_presentAddr_col")
	private String presentAddr;
	
	@Column(name="pat_commAddr_col")
	private String communicationAddr;
	
	@ElementCollection
	@CollectionTable(
			name = "med_his_tab",
			joinColumns = @JoinColumn(name="med_his_fk_col"))
	@Column(name="med_his_col")
	private Set<String> medHistory;
	
	@Column(name= "ifOther_col")
	private String ifOther;
	
	@Column(name="pat_details_col")
	private String details;

}
