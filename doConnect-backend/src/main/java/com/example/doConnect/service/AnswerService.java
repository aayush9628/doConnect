package com.example.doConnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.doConnect.entity.Answer;
import com.example.doConnect.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
	
	private final AnswerRepository ansRepo;
	
	public List<Answer> getAllAnswers(){
		return ansRepo.findAll();
	}
	
	public List<Answer> getAllAnswersFalse(){
		return ansRepo.findAllByStatus(false);
	}
	
	public Answer addAnswer(Answer answer) {
		return ansRepo.save(answer);
	}
	
	public Answer getAnswerById(int id) {
		return ansRepo.findById(id).get();
	}
	
	public Answer updateAnswer(Answer answer) {
		return ansRepo.save(answer);
	}
	
	public void deleteAnswerById(int id) {
		ansRepo.deleteById(id);
	}
	
	public List<Answer> getAnswerByQuestionId(int qid) {
		return ansRepo.findAnswerByQid(qid);
	}

	public void setStatus(int id) {
		Answer answer = getAnswerById(id);
		answer.setStatus(!answer.isStatus());
		ansRepo.save(answer);
	}

	public List<Answer> getAllAnswersTrue() {
		return ansRepo.findAllByStatus(true);
	}
}
