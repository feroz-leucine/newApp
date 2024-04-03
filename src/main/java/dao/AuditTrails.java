package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AuditTrailsDAO {

    public void createAuditTrail(AuditTrails auditTrail) throws SQLException {
        String sql = "INSERT INTO audit_trails(action_type, entity_type, entity_id, action_timestamp, user_id, action_detail, fk_complaint_id, fk_investigation_id, fk_action_id, fk_communication_log_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, auditTrail.getActionType());
            pstmt.setString(2, auditTrail.getEntityType());
            pstmt.setInt(3, auditTrail.getEntityId());
            pstmt.setTimestamp(4, auditTrail.getActionTimestamp());
            pstmt.setInt(5, auditTrail.getUserId());
            pstmt.setString(6, auditTrail.getActionDetail());
            pstmt.setInt(7, auditTrail.getFkComplaintId());
            pstmt.setInt(8, auditTrail.getFkInvestigationId());
            pstmt.setInt(9, auditTrail.getFkActionId());
            pstmt.setInt(10, auditTrail.getFkCommunicationLogId());

            pstmt.executeUpdate();
        }
    }

    public List<AuditTrails> readAllAuditTrails() throws SQLException {
        List<AuditTrails> auditTrails = new ArrayList<>();
        String sql = "SELECT * FROM audit_trails";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                AuditTrails auditTrail = new AuditTrails(
                        rs.getInt("id"),
                        rs.getString("action_type"),
                        rs.getString("entity_type"),
                        rs.getInt("entity_id"),
                        rs.getTimestamp("action_timestamp"),
                        rs.getInt("user_id"),
                        rs.getString("action_detail"),
                        rs.getInt("fk_complaint_id"),
                        rs.getInt("fk_investigation_id"),
                        rs.getInt("fk_action_id"),
                        rs.getInt("fk_communication_log_id")
                );
                auditTrails.add(auditTrail);
            }
        }
        return auditTrails;
    }

    public void updateAuditTrail(AuditTrails auditTrail) throws SQLException {
        String sql = "UPDATE audit_trails SET action_type = ?, entity_type = ?, entity_id = ?, action_timestamp = ?, user_id = ?, action_detail = ?, fk_complaint_id = ?, fk_investigation_id = ?, fk_action_id = ?, fk_communication_log_id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, auditTrail.getActionType());
            pstmt.setString(2, auditTrail.getEntityType());
            pstmt.setInt(3, auditTrail.getEntityId());
            pstmt.setTimestamp(4, auditTrail.getActionTimestamp());
            pstmt.setInt(5, auditTrail.getUserId());
            pstmt.setString(6, auditTrail.getActionDetail());
            pstmt.setInt(7, auditTrail.getFkComplaintId());
            pstmt.setInt(8, auditTrail.getFkInvestigationId());
            pstmt.setInt(9, auditTrail.getFkActionId());
            pstmt.setInt(10, auditTrail.getFkCommunicationLogId());

            pstmt.executeUpdate();
        }
    }

    public void deleteAuditTrail(int id) throws SQLException {
        String sql = "DELETE FROM audit_trails WHERE id = ?";
        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}