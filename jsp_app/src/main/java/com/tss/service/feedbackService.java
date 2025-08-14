package com.tss.service;

import com.tss.dao.FeedbackDAO;
import com.tss.exception.DBException;
import com.tss.model.Feedback;

public class feedbackService {
	
	FeedbackDAO dao;
	
	public feedbackService() {
		super();
		 dao = new FeedbackDAO();
		
	}
	
	public boolean saveFeedback(Feedback feedback) throws DBException {
		return  dao.saveFeedback(feedback);
	}
}
