package com.example.doConnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.doConnect.entity.Question;
import com.example.doConnect.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	private final QuestionRepository quesRepo;
	
	public Question addQuestion(Question question) {
		return quesRepo.save(question);
	}
	
	public Question updateQuestion(Question question) {
		return quesRepo.save(question);
	}
	
	public void deleteQuestionById(int id) {
		quesRepo.deleteById(id);
	}
	
	public List<Question> getAllQuestion(){
		return quesRepo.findAll();
	}
	
	public List<Question> getAllQuestionFalse(){
		return quesRepo.findAllByStatus(false);
	}
	
	public Question getQuestionByTopic(String topic){
		return quesRepo.findByTopic(topic);
	}
	
	public Question getQuestionById(int id) {
		return quesRepo.findById(id).get();
	}

	public void setStatus(int id) {
		Question question = getQuestionById(id);
		question.setStatus(!question.isStatus());
		quesRepo.save(question);
	}

	public List<Question> getAllQuestionTrue() {
		return quesRepo.findAllByStatus(true);
	}
}
