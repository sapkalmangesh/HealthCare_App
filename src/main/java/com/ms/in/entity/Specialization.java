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
	@Column(
			name = "specId_col"
			)
	private Long specId;
	
	@Column(
			name = "specCode_col",
			length = 15,
			nullable = false,
			unique = true
			)
	private String specCode;
	
	@Column(
			name = "specName_col",
			length = 50,
			nullable = false,
			unique = true
			)
	private String specName;
	
	@Column(
			name= "specNotes_col",
			length = 250,
			nullable = false,
			unique = true
			)
	private String specNotes;

}
