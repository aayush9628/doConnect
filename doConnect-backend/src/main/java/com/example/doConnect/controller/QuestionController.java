package com.example.doConnect.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doConnect.entity.Question;
import com.example.doConnect.service.QuestionService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
	
	private final QuestionService service;
	
	@PostMapping("/addquestion")
	public Question addQuestion(@RequestBody Question question) {
		question.setStatus(false);
		return service.addQuestion(question);
	}
	
	@PutMapping("/updatequestion")
	public Question updateQuestion(@RequestBody Question question) {
		return service.updateQuestion(question);
	}
	
	@DeleteMapping("/deletequestion/{id}")
	public void deleteQuestion(@PathVariable int id) {
		service.deleteQuestionById(id);
	}
	
	@GetMapping("/setStatus/{id}/")
	public void setStatus(@PathVariable int id, @PathVariable boolean status){
		service.setStatus(id);
	}
	
	@GetMapping("/getallquestion")
	public List<Question> getAllQuestion(){
		return service.getAllQuestion();
	}
	
	@GetMapping("/getallquestionfalse")
	public List<Question> getAllQuestionFalse(){
		return service.getAllQuestionFalse();
	}
	
	@GetMapping("/getallquestiontrue")
	public List<Question> getAllQuestionTrue(){
		return service.getAllQuestionTrue();
	}
	
	@GetMapping("/getquestionbytopic/{topic}")
	public Question getQuestionByTopic(@PathVariable String topic) {
		return service.getQuestionByTopic(topic);
	}
	
	@GetMapping("/getquestionbyid/{id}")
	public Question getQuestionById(@PathVariable int id) {
		return service.getQuestionById(id);
	}
	
}
