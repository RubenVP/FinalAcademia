package com.academia.appForum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.academia.appForum.model.QuestionEntity;
import com.academia.appForum.model.Questions;
import com.academia.appForum.service.MessageService;
import com.academia.appForum.service.QuestionsService;

@Controller
@RequestMapping(value = "/data")
public class QuestionsController {

	@Autowired
	private QuestionsService questionsService;

	@Autowired
	private MessageService messageService;

	/**
	 * get all questions when page loads at the beginning of the session with
	 * JSON object (rest)
	 * 
	 * @return List of questions - ResponseEntity<Questions>
	 */
	@RequestMapping(value = "/fullQuestionsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Questions> getAllQuestions() {

		final List<QuestionEntity> questionsList = questionsService.getAllQuestions();

		final Questions questions = new Questions();
		questions.setQuestions(questionsList);

		return new ResponseEntity<Questions>(questions, HttpStatus.OK);
	}

	// /**
	// * get View of find questions by category
	// *
	// * @param category
	// * @param modelAndView
	// * @return view "app/forum"
	// * @throws Exception
	// */
	// @RequestMapping(value = "/listByCategory", method = RequestMethod.GET)
	// public @ResponseBody ResponseEntity<Questions>
	// findQuestionByCategory(@RequestParam("category") String category,
	// ModelAndView modelAndView) throws Exception {
	// System.out.println("Find Question By category: " + category);
	//
	// final List<QuestionEntity> questionsList = questionsService
	// .getQuestionsByCategory(CategoryEnum.valueOf(category));
	//
	// final Questions questions = new Questions();
	// questions.setQuestions(questionsList);
	//
	// return new ResponseEntity<Questions>(questions, HttpStatus.OK);
	// }

}
