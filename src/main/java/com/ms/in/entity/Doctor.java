package com.ms.in.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor_tab")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doc_Id_col")
	private Long id;
	
	@Column(name = "doc_fn_col")
	private String firstName;
	
	@Column(name = "doc_ln_col")
	private String lastName;
	
	@Column(name = "doc_mail_col")
	private String email;
	
	@Column(name = "doc_mob_col")
	private Long mob;
	
	@Column(name = "doc_addr_col")
	private String addr;
	
	@Column(name = "doc_gen_col")
	private String gender;
	
	@Column(name = "doc_note_col")
	private String note;
	
	@Column(name= "doc_img_loc_col")
	private String photoLoc;
	
	//---------------Association Mapping--------------
	@ManyToOne
	@JoinColumn(name= "spec_id_fk_col")
	private Specialization specializations;


}
