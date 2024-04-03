package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.CommunicationLog;
import util.DatabaseUtility;

public class CommunicationLogsDAO {

    public boolean createCommunicationLog(CommunicationLog communicationLog) {
        String sql = "INSERT INTO communication_logs(communication_type, communication_date, communication_subject, communication_content, initiator, is_resolved, fk_complaint_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, communicationLog.getCommunicationType());
            preparedStatement.setTimestamp(2, communicationLog.getCommunicationDate());
            preparedStatement.setString(3, communicationLog.getCommunicationSubject());
            preparedStatement.setString(4, communicationLog.getCommunicationContent());
            preparedStatement.setString(5, communicationLog.getInitiator());
            preparedStatement.setInt(6, communicationLog.getIsResolved());
            preparedStatement.setInt(7, communicationLog.getFk_complaint_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CommunicationLog> readCommunicationLogs() {
        String sql = "SELECT * FROM communication_logs";
        List<CommunicationLog> communicationLogs = new ArrayList<>();

        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CommunicationLog communicationLog = new CommunicationLog();
                communicationLog.setId(resultSet.getInt("id"));
                communicationLog.setCommunicationType(resultSet.getString("communication_type"));
                communicationLog.setCommunicationDate(resultSet.getTimestamp("communication_date"));
                communicationLog.setCommunicationSubject(resultSet.getString("communication_subject"));
                communicationLog.setCommunicationContent(resultSet.getString("communication_content"));
                communicationLog.setInitiator(resultSet.getString("initiator"));
                communicationLog.setIsResolved(resultSet.getInt("is_resolved"));
                communicationLog.setFk_complaint_id(resultSet.getInt("fk_complaint_id"));

                communicationLogs.add(communicationLog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return communicationLogs;
    }

    public boolean updateCommunicationLog(CommunicationLog communicationLog) {
        String sql = "UPDATE communication_logs SET communication_type = ?, communication_date = ?, communication_subject = ?, communication_content = ?, initiator = ?, is_resolved = ?, fk_complaint_id = ? WHERE id = ?";

        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, communicationLog.getCommunicationType());
            preparedStatement.setTimestamp(2, communicationLog.getCommunicationDate());
            preparedStatement.setString(3, communicationLog.getCommunicationSubject());
            preparedStatement.setString(4, communicationLog.getCommunicationContent());
            preparedStatement.setString(5, communicationLog.getInitiator());
            preparedStatement.setInt(6, communicationLog.getIsResolved());
            preparedStatement.setInt(7, communicationLog.getFk_complaint_id());
            preparedStatement.setInt(8, communicationLog.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCommunicationLog(int communicationLogId) {
        String sql = "DELETE FROM communication_logs WHERE id = ?";

        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, communicationLogId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}