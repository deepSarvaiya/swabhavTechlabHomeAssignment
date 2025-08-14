package com.tss.service;

import java.util.List;

import com.tss.dao.QuizDao;
import com.tss.model.Question;

public class QuizService {
	private QuizDao dao = new QuizDao();

	public List<Question> getRandomQuestions(int count) {
		return dao.getRandomQuestions(count);
	}
}
