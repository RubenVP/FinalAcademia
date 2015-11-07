package com.academia.appForum.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.appForum.model.CategoryEnum;
import com.academia.appForum.model.CommentEntity;
import com.academia.appForum.model.QuestionEntity;
import com.academia.appForum.model.UsersEntity;
import com.academia.appForum.repository.QuestionsRepository;

@Service
public class QuestionsService {

	@Autowired
	QuestionsRepository questionsRepository;

	@Autowired
	UsersService usersService;

	public void createNewQuestion(QuestionEntity questionEntity) {

		System.out.println("[SERVICE] create question " + questionEntity);

		final UsersEntity usersEntity = usersService.getUserInSession();

		questionEntity.setUserEntity(usersEntity);

		questionEntity.setCreateDate(new Date());

		questionsRepository.createQuestion(questionEntity);
	}

	public List<QuestionEntity> getAllQuestions() {

		System.out.println("[SERVICE] get all questions");

		return questionsRepository.getAllQuestions();

	}

	public QuestionEntity findQuestionById(Integer id) {

		System.out.println("[SERVICE] find question by id " + id);

		final QuestionEntity questionEntity = questionsRepository.findQuestionById(id);

		return questionEntity;
	}

	public void updateQuestionToAnswered(int id) {

		System.out.println("[SERVICE] marking as answered question with id: " + id);

		questionsRepository.updateQuestionToAnswered(id);

	}

	public void deleteQuestion(QuestionEntity questionEntity) {

		System.out.println("[SERVICE] deleting question: " + questionEntity);

		questionsRepository.deleteQuestion(questionEntity);

	}

	public void addAnswer(CommentEntity commentEntity, QuestionEntity questionEntity) {
		System.out.println("[SERVICE] adding answer: " + commentEntity);

		final UsersEntity userEntity = usersService.getUserInSession();

		commentEntity.setUserEntity(userEntity);
		commentEntity.setQuestionEntity(questionEntity);
		commentEntity.setAnswerDate(new Date());

		questionsRepository.addAnswer(commentEntity);
	}

	public List<CommentEntity> getAllAnswersByQuestion(QuestionEntity questionEntity) {

		System.out.println("[SERVICE] get all answers by question id: " + questionEntity.getId());

		return questionsRepository.getAllAnswersByQuestion(questionEntity);
	}

	public List<QuestionEntity> getRecentQuestions() {

		final UsersEntity usersEntity = usersService.getUserInSession();

		System.out.println("[SERVICE] get recent questions by user: " + usersEntity.getUsername());

		return questionsRepository.getRecentQuestions(usersEntity);
	}

	public List<QuestionEntity> getUnansweredQuestions() {
		System.out.println("[SERVICE] get unanswered questions");

		return questionsRepository.getUnansweredQuestions();
	}

	public List<QuestionEntity> getAnsweredQuestions() {
		System.out.println("[SERVICE] get answered questions");

		return questionsRepository.getAnsweredQuestions();
	}

	public QuestionEntity getQuestionById(int questionId) {
		System.out.println("[SERVICE] get question by id" + questionId);

		return questionsRepository.findQuestionById(questionId);
	}

	public List<QuestionEntity> getQuestionsByCategory(CategoryEnum category) {

		System.out.println("[SERVICE] get questions by Category: " + category.getCategory());

		return questionsRepository.getQuestionsByCategory(category);
	}

	public List<QuestionEntity> getCustomSearch(String text) {

		System.out.println("[SERVICE] get custom search questions: " + text);

		return questionsRepository.getCustomSearch(text);
	}

	public void setAnswerAsHelpful(int commnetId) {
		System.out.println("[SERVICE] set Answer As Helpful: " + commnetId);

		questionsRepository.setAnswerAsHelpful(commnetId);
	}

}
