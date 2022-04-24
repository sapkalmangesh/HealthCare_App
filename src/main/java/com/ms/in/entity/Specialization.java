package com.ms.in.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="specialization_tab_col")
public class Specialization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="specId_col")
	private Long specId;
	
	@Column(name="specCode_col")
	private String specCode;
	
	@Column(name="specName_col")
	private String specName;
	
	@Column(name="speckNotes_col")
	private String specNotes;

}
