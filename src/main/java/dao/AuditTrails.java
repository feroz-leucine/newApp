package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.AuditTrails;
import util.DatabaseUtility;

public class AuditTrailsDAO {
    private static final String INSERT_AUDIT_TRAIL = "INSERT INTO audit_trails (action_type, entity_type, entity_id, action_timestamp, user_id, action_detail, fk_complaint_id, fk_investigation_id, fk_action_id, fk_communication_log_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_AUDIT_TRAIL = "UPDATE audit_trails SET action_type = ?, entity_type = ?, entity_id = ?, action_timestamp = ?, user_id = ?, action_detail = ?, fk_complaint_id = ?, fk_investigation_id = ?, fk_action_id = ?, fk_communication_log_id = ? WHERE id = ?";
    private static final String DELETE_AUDIT_TRAIL = "DELETE FROM audit_trails WHERE id = ?";
    private static final String GET_AUDIT_TRAIL_BY_ID = "SELECT * FROM audit_trails WHERE id = ?";
    private static final String GET_ALL_AUDIT_TRAILS = "SELECT * FROM audit_trails";

    public void createAuditTrail(AuditTrails auditTrail) throws SQLException {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUDIT_TRAIL);
            preparedStatement.setString(1, auditTrail.getActionType());
            preparedStatement.setString(2, auditTrail.getEntityType());
            preparedStatement.setInt(3, auditTrail.getEntityId());
            preparedStatement.setTimestamp(4, auditTrail.getActionTimestamp());
            preparedStatement.setInt(5, auditTrail.getUserId());
            preparedStatement.setString(6, auditTrail.getActionDetail());
            preparedStatement.setInt(7, auditTrail.getFkComplaintId());
            preparedStatement.setInt(8, auditTrail.getFkInvestigationId());
            preparedStatement.setInt(9, auditTrail.getFkActionId());
            preparedStatement.setInt(10, auditTrail.getFkCommunicationLogId());

            preparedStatement.executeUpdate();
        }
    }

    public void updateAuditTrail(AuditTrails auditTrail) throws SQLException {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUDIT_TRAIL);
            preparedStatement.setString(1, auditTrail.getActionType());
            preparedStatement.setString(2, auditTrail.getEntityType());
            preparedStatement.setInt(3, auditTrail.getEntityId());
            preparedStatement.setTimestamp(4, auditTrail.getActionTimestamp());
            preparedStatement.setInt(5, auditTrail.getUserId());
            preparedStatement.setString(6, auditTrail.getActionDetail());
            preparedStatement.setInt(7, auditTrail.getFkComplaintId());
            preparedStatement.setInt(8, auditTrail.getFkInvestigationId());
            preparedStatement.setInt(9, auditTrail.getFkActionId());
            preparedStatement.setInt(10, auditTrail.getFkCommunicationLogId());
            preparedStatement.setInt(11, auditTrail.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteAuditTrail(int auditTrailId) throws SQLException {
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUDIT_TRAIL);
            preparedStatement.setInt(1, auditTrailId);

            preparedStatement.executeUpdate();
        }
    }

    public AuditTrails getAuditTrailById(int auditTrailId) throws SQLException {
        AuditTrails auditTrail = null;

        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_AUDIT_TRAIL_BY_ID);
            preparedStatement.setInt(1, auditTrailId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                auditTrail = mapAuditTrail(resultSet);
            }
        }

        return auditTrail;
    }

    public List<AuditTrails> getAllAuditTrails() throws SQLException {
        List<AuditTrails> auditTrails = new ArrayList<>();

        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_AUDIT_TRAILS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AuditTrails auditTrail = mapAuditTrail(resultSet);
                auditTrails.add(auditTrail);
            }
        }

        return auditTrails;
    }

    private AuditTrails mapAuditTrail(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String actionType = resultSet.getString("action_type");
        String entityType = resultSet.getString("entity_type");
        int entityId = resultSet.getInt("entity_id");
        Timestamp actionTimestamp = resultSet.getTimestamp("action_timestamp");
        int userId = resultSet.getInt("user_id");
        String actionDetail = resultSet.getString("action_detail");
        int fkComplaintId = resultSet.getInt("fk_complaint_id");
        int fkInvestigationId = resultSet.getInt("fk_investigation_id");
        int fkActionId = resultSet.getInt("fk_action_id");
        int fkCommunicationLogId = resultSet.getInt("fk_communication_log_id");

        return new AuditTrails(id, actionType, entityType, entityId, actionTimestamp, userId, actionDetail, fkComplaintId, fkInvestigationId, fkActionId, fkCommunicationLogId);
    }
}