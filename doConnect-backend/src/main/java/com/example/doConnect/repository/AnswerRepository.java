package com.example.doConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.doConnect.entity.Answer;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{
	Answer findByStatus(boolean status);
	List<Answer> findAllByStatus(boolean status);
	@Query(value = "select * from answer where qid = ?1", nativeQuery = true)
	List<Answer> findAnswerByQid(int qid);
}
