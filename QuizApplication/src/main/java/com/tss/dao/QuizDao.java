package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.db.DBConnection;
import com.tss.model.Question;

public class QuizDao {

    public List<Question> getRandomQuestions(int count) {
        List<Question> questionList = new ArrayList<>();
        String query = "SELECT * FROM questions ORDER BY RAND() LIMIT ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, count);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Question q = new Question(
                    rs.getInt("id"),
                    rs.getString("question_text"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("correct_option")
                );
                questionList.add(q);
            }
        } catch (SQLException e) {
            System.out.println("Error in getRandomQuestions: " + e.getMessage());
        }
        return questionList;
    }

    public boolean saveResult(int userId, int score, int totalQuestions) {
        String sql = "INSERT INTO results (user_id, score, total_questions) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, score);
            stmt.setInt(3, totalQuestions);
            int rows = stmt.executeUpdate();

            return rows > 0; // returns true if insertion was successful

        } catch (SQLException e) {
            System.out.println("Error in saveResult: " + e.getMessage());
            return false;
        }
    }

}
