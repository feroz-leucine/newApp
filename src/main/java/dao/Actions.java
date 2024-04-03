package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActionsDAO {

    public boolean createAction(int id, String actionType, String description, String initiationDate, String completionDate, String status, String impactLevel, String responsibleDepartment, String expectedOutcome, int fkInvestigationId) {
        String sql = "INSERT INTO actions (id, action_type, description, initiation_date, completion_date, status, impact_level, responsible_department, expected_outcome, fk_investigation_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, actionType);
            pstmt.setString(3, description);
            pstmt.setString(4, initiationDate);
            pstmt.setString(5, completionDate);
            pstmt.setString(6, status);
            pstmt.setString(7, impactLevel);
            pstmt.setString(8, responsibleDepartment);
            pstmt.setString(9, expectedOutcome);
            pstmt.setInt(10, fkInvestigationId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<String[]> readActions() {
        String sql = "SELECT * FROM actions";
        List<String[]> actions = new ArrayList<>();
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String[] action = {
                        rs.getString("id"),
                        rs.getString("action_type"),
                        rs.getString("description"),
                        rs.getString("initiation_date"),
                        rs.getString("completion_date"),
                        rs.getString("status"),
                        rs.getString("impact_level"),
                        rs.getString("responsible_department"),
                        rs.getString("expected_outcome"),
                        rs.getString("fk_investigation_id")
                };
                actions.add(action);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return actions;
    }

    public boolean updateAction(int id, String actionType, String description, String initiationDate, String completionDate, String status, String impactLevel, String responsibleDepartment, String expectedOutcome, int fkInvestigationId) {
        String sql = "UPDATE actions SET action_type = ?, description = ?, initiation_date = ?, completion_date = ?, status = ?, impact_level = ?, responsible_department = ?, expected_outcome = ?, fk_investigation_id = ? WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, actionType);
            pstmt.setString(2, description);
            pstmt.setString(3, initiationDate);
            pstmt.setString(4, completionDate);
            pstmt.setString(5, status);
            pstmt.setString(6, impactLevel);
            pstmt.setString(7, responsibleDepartment);
            pstmt.setString(8, expectedOutcome);
            pstmt.setInt(9, fkInvestigationId);
            pstmt.setInt(10, id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deleteAction(int id) {
        String sql = "DELETE FROM actions WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}