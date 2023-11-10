package com.example.doConnect.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="qid")
	private int id;
	@Column(columnDefinition = "TEXT")
	private String description_question;
	@Column
	private String image_src;
	@Column
	private String datetime;
	@Column
	private boolean status;
	@Column
	private String topic;
	@Column
	private String title;
	
	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Answer> answers;
	
	@Column
	private String qcreated_by;
	@Column
	private String qapproved_by;
	
}
