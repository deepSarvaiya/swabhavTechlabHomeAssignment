package com.tss.dao;

import com.tss.db.DBConnection;
import com.tss.exception.DBException;
import com.tss.model.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackDAO {

    public boolean saveFeedback(Feedback feedback) throws DBException {
        String sql = "INSERT INTO feedback (name, session_date, session_content, query_resolution, interactivity, impactful_learning, content_delivery) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, feedback.getName());
            ps.setDate(2, java.sql.Date.valueOf(feedback.getSessionDate()));
            ps.setInt(3, feedback.getSessionContent());
            ps.setInt(4, feedback.getQueryResolution());
            ps.setInt(5, feedback.getInteractivity());
            ps.setInt(6, feedback.getImpactfulLearning());
            ps.setInt(7, feedback.getContentDelivery());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DBException("Error saving feedback", e);
        }
    }
}
