package com.academia.appForum.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.academia.appForum.model.CategoryEnum;
import com.academia.appForum.model.CommentEntity;
import com.academia.appForum.model.QuestionEntity;
import com.academia.appForum.model.UsersEntity;

@Repository
@Transactional
public class QuestionsRepository {

	@PersistenceContext(name = "persistenceUnitH2")
	EntityManager entityManager;

	public List<QuestionEntity> getAllQuestions() {
		System.out.println("[REPO] all questions");

		return entityManager.createQuery("SELECT Q FROM QuestionEntity Q", QuestionEntity.class).getResultList();
	}

	public void createQuestion(QuestionEntity questionEntity) {
		System.out.println("[REPO] before persist: " + questionEntity);

		entityManager.persist(questionEntity);

		System.out.println("[REPO] question created" + questionEntity);
	}

	public QuestionEntity findQuestionById(int id) {

		System.out.println("[REPO] find question with id: " + id);

		return entityManager.createQuery("SELECT Q FROM QuestionEntity Q WHERE Q.id = :id", QuestionEntity.class)
				.setParameter("id", id).getSingleResult();
	}

	public void deleteQuestion(QuestionEntity questionEntity) {

		System.out.println("[REPO] before delete question " + questionEntity);

		final QuestionEntity questionToBeRemoved = entityManager.merge(questionEntity);
		entityManager.remove(questionToBeRemoved);

		System.out.println("[REPO] deleted question " + questionEntity);
	}

	public void updateQuestionToAnswered(int id) {

		System.out.println("[REPO] before updte id: " + id);

		entityManager.createQuery("UPDATE QuestionEntity Q SET Q.answered = true WHERE Q.id = :id")
				.setParameter("id", id).executeUpdate();

		System.out.println("[REPO] updated question with id: " + id);
	}

	public void addAnswer(CommentEntity commentEntity) {
		System.out.println("[REPO] before persist answer: " + commentEntity);

		entityManager.persist(commentEntity);

		System.out.println("[REPO] answer created" + commentEntity);
	}

	public List<CommentEntity> getAllAnswersByQuestion(QuestionEntity questionEntity) {
		System.out.println("[REPO] all answers for question id: " + questionEntity.getId());

		return entityManager.createQuery("SELECT C FROM CommentEntity C WHERE C.questionEntity = :questionEntity",
				CommentEntity.class).setParameter("questionEntity", questionEntity).getResultList();
	}

	public List<QuestionEntity> getRecentQuestions(UsersEntity usersEntity) {

		System.out.println("[REPO] get las 3 questions asked by user: " + usersEntity.getUsername());

		return entityManager
				.createQuery("SELECT Q FROM QuestionEntity Q WHERE Q.userEntity = :userEntity ORDER BY Q.createDate",
						QuestionEntity.class)
				.setParameter("userEntity", usersEntity).setMaxResults(3).getResultList();
	}

	public List<QuestionEntity> getUnansweredQuestions() {

		System.out.println("[REPO] get unaswered questions");

		return entityManager
				.createQuery("SELECT Q FROM QuestionEntity Q WHERE Q.answered = false", QuestionEntity.class)
				.getResultList();
	}

	public List<QuestionEntity> getAnsweredQuestions() {

		System.out.println("[REPO] get unaswered questions");

		return entityManager.createQuery("SELECT Q FROM QuestionEntity Q WHERE Q.answered = true", QuestionEntity.class)
				.getResultList();
	}

	public List<QuestionEntity> getQuestionsByCategory(CategoryEnum category) {

		System.out.println("[REPO] get questions by Category: " + category.getCategory());

		return entityManager
				.createQuery("SELECT Q FROM QuestionEntity Q WHERE Q.category = :category", QuestionEntity.class)
				.setParameter("category", category).getResultList();
	}

	public List<QuestionEntity> getCustomSearch(String text) {

		System.out.println("[REPO] get custom search questions: " + text);

		return entityManager
				.createQuery("SELECT Q FROM QuestionEntity Q WHERE Q.title Like :text", QuestionEntity.class)
				.setParameter("text", '%' + text + '%').getResultList();
	}
}
