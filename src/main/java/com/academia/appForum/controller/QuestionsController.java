package com.academia.appForum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.academia.appForum.model.CategoryEnum;
import com.academia.appForum.model.CommentEntity;
import com.academia.appForum.model.MessageEntity;
import com.academia.appForum.model.QuestionEntity;
import com.academia.appForum.model.Questions;
import com.academia.appForum.service.MessageService;
import com.academia.appForum.service.QuestionsService;

@Controller
@RequestMapping(value = "/forum")
public class QuestionsController {

	@Autowired
	private QuestionsService questionsService;

	@Autowired
	private MessageService messageService;

	// @RequestMapping(method = RequestMethod.GET)
	// public ModelAndView getAllQuestions() {
	//
	// final List<QuestionEntity> questions =
	// questionsService.getAllQuestions();
	//
	// final ModelAndView modelAndView = new ModelAndView();
	// modelAndView.setViewName("app/forum");
	// modelAndView.addObject("questions", questions);
	//
	// return modelAndView;
	// }

	/**
	 * get View of all questions when page loads at the beginning of the session
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAllQuestionsView() {

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("app/forum");

		return modelAndView;
	}

	/**
	 * get all questions when page loads at the beginning of the session with
	 * JSON object (rest)
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Questions> getAllQuestions() {

		final List<QuestionEntity> questionsList = questionsService.getAllQuestions();

		final Questions questions = new Questions();
		questions.setQuestions(questionsList);

		return new ResponseEntity<Questions>(questions, HttpStatus.OK);
	}

	@RequestMapping(value = "/askQuestion", method = RequestMethod.GET)
	public ModelAndView onLoadFormPage() {

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("app/new_question");
		modelAndView.addObject("question", new QuestionEntity());

		return modelAndView;
	}

	/**
	 * Gets parameters from the new_questions view and sets the categoryEnum
	 * field to the correspondent category and the answer field to false
	 * 
	 * @param question
	 * @param bindingResult
	 * @param category
	 * @param modelAndView
	 * @return a new questionEntity, a success Message and to app/new_question
	 *         view
	 * @throws Exception
	 */
	@RequestMapping(value = "/askQuestion", method = RequestMethod.POST)
	public ModelAndView createNewQuestion(@Valid QuestionEntity question, BindingResult bindingResult,
			@RequestParam("cat") String category, ModelAndView modelAndView) throws Exception {
		System.out.println("Creating question: " + question);

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("question", question);
			modelAndView.setViewName("app/new_question");
			modelAndView.addObject("errorMsg", "errorMsg");
			return modelAndView;
		}

		for (final CategoryEnum categoryEnum : CategoryEnum.values()) {
			if (categoryEnum.getCategory().equals(category)) {
				question.setCategory(categoryEnum);
			}
		}

		question.setAnswered(false);
		questionsService.createNewQuestion(question);

		modelAndView.addObject("question", new QuestionEntity());
		modelAndView.setViewName("app/new_question");
		modelAndView.addObject("successMsg", "successMsg");

		return modelAndView;
	}

	@RequestMapping(value = "/findByCategory", method = RequestMethod.GET)
	public ModelAndView findQuestionByCategory(@RequestParam("category") String category, ModelAndView modelAndView)
			throws Exception {
		System.out.println("Find Question By category: " + category);

		final List<QuestionEntity> questions = questionsService.getQuestionsByCategory(CategoryEnum.valueOf(category));

		modelAndView.setViewName("app/forum");
		modelAndView.addObject("questions", questions);

		return modelAndView;
	}

	@RequestMapping(value = "/deleteQuestion", method = RequestMethod.GET)
	public ModelAndView deleteQuestion(@RequestParam("id") int id, ModelAndView modelAndView) throws Exception {

		final QuestionEntity questionEntity = questionsService.findQuestionById(id);

		questionsService.deleteQuestion(questionEntity);

		modelAndView.addObject("question", new QuestionEntity());
		modelAndView.setViewName("app/forum");
		modelAndView.addObject("successMsg", "successMsg");

		return modelAndView;
	}

	@RequestMapping(value = "/updateQuestionToAnswered", method = RequestMethod.GET)
	public ModelAndView updateQuestionToAnswered(@RequestParam("questionId") int id, ModelAndView modelAndView)
			throws Exception {
		questionsService.updateQuestionToAnswered(id);

		modelAndView.addObject("question", new QuestionEntity());
		modelAndView.setViewName("app/forum");
		modelAndView.addObject("answeredMsg", "answeredMsg");

		return modelAndView;
	}

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ModelAndView selectQuestion(@RequestParam("questionId") int questionId, ModelAndView modelAndView) {

		final QuestionEntity questionEntity = questionsService.findQuestionById(questionId);

		final List<CommentEntity> commentsList = questionsService.getAllAnswersByQuestion(questionEntity);

		modelAndView.setViewName("app/view-question");
		modelAndView.addObject("commentsList", commentsList);
		modelAndView.addObject("question", questionEntity);
		modelAndView.addObject("comment", new CommentEntity());

		return modelAndView;
	}

	@RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
	public ModelAndView addAnswerToQuestion(@Valid CommentEntity comment, BindingResult bindingResult,
			@RequestParam("questionId") int questionId, ModelAndView modelAndView) throws Exception {

		System.out.println("Creating answer: " + comment + "for qustion id: " + questionId);

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("comment", comment);
			modelAndView.setViewName("app/view-question");
			modelAndView.addObject("errorMsg", "errorMsg");
			return modelAndView;
		}

		final QuestionEntity questionEntity = questionsService.getQuestionById(questionId);

		comment.setHelpful(false);
		questionsService.addAnswer(comment, questionEntity);
		messageService.sendMessegeQuestionAndswered(questionEntity);
		questionsService.updateQuestionToAnswered(questionId);

		final List<CommentEntity> commentsList = questionsService.getAllAnswersByQuestion(questionEntity);
		modelAndView.addObject("commentsList", commentsList);
		modelAndView.addObject("comment", new CommentEntity());
		modelAndView.addObject("question", questionEntity);
		modelAndView.setViewName("app/view-question");
		modelAndView.addObject("successMsg", "successMsg");

		return modelAndView;
	}

	@RequestMapping(value = "/recentQuestions", method = RequestMethod.GET)
	public ModelAndView getRecentQuestionsByUser() {

		final List<QuestionEntity> questions = questionsService.getRecentQuestions();
		final List<MessageEntity> messages = messageService.getMessagesForUser();

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("questions", questions);
		modelAndView.addObject("messages", messages);
		modelAndView.setViewName("app/profile");

		return modelAndView;
	}

	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public ModelAndView showInbox() {
		final List<QuestionEntity> questions = questionsService.getUnansweredQuestions();

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("questions", questions);
		modelAndView.setViewName("app/inbox");

		return modelAndView;
	}

	@RequestMapping(value = "/formInbox", method = RequestMethod.POST)
	public ModelAndView selectStateOfQuestionsInbox(@RequestParam("state") String state, ModelAndView modelAndView) {

		System.out.println("state >>" + state);

		List<QuestionEntity> questions = null;

		if (state.equals("unanswered")) {
			questions = questionsService.getUnansweredQuestions();
		} else if (state.equals("answered")) {
			questions = questionsService.getAnsweredQuestions();
		} else {
			throw new IllegalStateException();
		}

		modelAndView.addObject("questions", questions);
		modelAndView.setViewName("app/inbox");

		return modelAndView;
	}

	@RequestMapping(value = "/messageSeen", method = RequestMethod.GET)
	public ModelAndView changeMessageToOld(@RequestParam("messageId") int messageId) {
		System.out.println("[CONTROLLER] change message to new false: " + messageId);

		messageService.changeMessageToOld(messageId);

		final List<QuestionEntity> questions = questionsService.getRecentQuestions();
		final List<MessageEntity> messages = messageService.getMessagesForUser();

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("questions", questions);
		modelAndView.addObject("messages", messages);
		modelAndView.setViewName("app/profile");

		return modelAndView;
	}

	@RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
	public ModelAndView deleteMessage(@RequestParam("messageId") int messageId) {
		System.out.println("[CONTROLLER] delete message: " + messageId);

		messageService.deleteMessage(messageId);

		final List<QuestionEntity> questions = questionsService.getRecentQuestions();
		final List<MessageEntity> messages = messageService.getMessagesForUser();

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("questions", questions);
		modelAndView.addObject("messages", messages);
		modelAndView.setViewName("app/profile");

		return modelAndView;
	}

	@RequestMapping(value = "/customSearch", method = RequestMethod.POST)
	public ModelAndView getCustomSearch(@RequestParam("searchText") String text, ModelAndView modelAndView) {

		System.out.println("Find Question By Custom Search: " + text);

		final List<QuestionEntity> questions = questionsService.getCustomSearch(text);

		modelAndView.setViewName("app/forum");
		modelAndView.addObject("questions", questions);

		return modelAndView;
	}

	@RequestMapping(value = "/answerHelpful", params = { "commetId", "question" }, method = RequestMethod.GET)
	public ModelAndView setAnswerAsHelpful(@RequestParam("commetId") int commetId,
			@RequestParam("question") int questionId) {
		System.out.println("[CONTROLLER] set Answer As Helpful: " + commetId);

		questionsService.setAnswerAsHelpful(commetId);
		final QuestionEntity question = questionsService.getQuestionById(questionId);

		final List<CommentEntity> commentsList = questionsService.getAllAnswersByQuestion(question);

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("app/view-question");
		modelAndView.addObject("commentsList", commentsList);
		modelAndView.addObject("question", question);
		modelAndView.addObject("comment", new CommentEntity());

		return modelAndView;
	}
}
