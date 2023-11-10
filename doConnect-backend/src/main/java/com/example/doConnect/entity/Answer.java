package com.example.doConnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "answer")
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(columnDefinition = "TEXT")
	private String description_answer;
	@Column
	private String img_src;
	@Column
	private boolean status;
	@Column
	private String datetime;
	
	@ManyToOne
	@JoinColumn(name = "qid", nullable = false)
	private Question question;
	
	@Column
	private String approved_by;
	@Column
	private String created_by;
}
