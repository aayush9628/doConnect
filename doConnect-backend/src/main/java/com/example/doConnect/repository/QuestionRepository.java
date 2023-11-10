package com.example.doConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.doConnect.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	Question findByTopic(String topic);
	Question findByStatus(boolean status);
	List<Question> findAllByStatus(boolean status);
}
