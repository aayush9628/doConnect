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

import com.example.doConnect.entity.Answer;
import com.example.doConnect.service.AnswerService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
	
	private final AnswerService service;
	
	@GetMapping("/getallanswers")
	public List<Answer> getAllAnswers(){
		return service.getAllAnswers();
	}
	
	@GetMapping("/getallanswersfalse")
	public List<Answer> getAllAnswersFalse(){
		return service.getAllAnswersFalse();
	}
	
	@GetMapping("/getallanswerstrue")
	public List<Answer> getAllAnswersTrue(){
		return service.getAllAnswersTrue();
	}
	
	@PostMapping("/addanswer")
	public Answer addAnswer(@RequestBody Answer answer) {
		answer.setStatus(false);
		return service.addAnswer(answer);
	}
	
	@GetMapping("/getanswerbyid/{id}")
	public Answer getAnswerById(@PathVariable int id) {
		return service.getAnswerById(id);
	}
	
	@PutMapping("/updateanswer")
	public Answer updateanswer(@RequestBody Answer answer) {
		return service.updateAnswer(answer);
	}
	
	@DeleteMapping("/deleteanswerbyid/{id}")
	public void deleteAnswerById(@PathVariable int id) {
		service.deleteAnswerById(id);
	}
	
	@GetMapping("/getanswerbyquestionid/{qid}")
	public List<Answer> getAnswerByQuestionId(@PathVariable int qid) {
		return service.getAnswerByQuestionId(qid);
	}
	
	@GetMapping("/setStatus/{id}")
	public void setStatus(@PathVariable int id) {
		service.setStatus(id);
	}
}
