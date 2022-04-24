package com.ms.in.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="specialization_tab")
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
