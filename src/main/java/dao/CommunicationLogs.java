package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommunicationLogsDAO {

    public boolean createCommunicationLog(int complaintId, String communicationType, Timestamp communicationDate,
                                          String communicationSubject, String communicationContent, String initiator,
                                          boolean isResolved) {
        String sql = "INSERT INTO communication_logs(fk_complaint_id, communication_type, communication_date, communication_subject, communication_content, initiator, is_resolved) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, complaintId);
            pstmt.setString(2, communicationType);
            pstmt.setTimestamp(3, communicationDate);
            pstmt.setString(4, communicationSubject);
            pstmt.setString(5, communicationContent);
            pstmt.setString(6, initiator);
            pstmt.setBoolean(7, isResolved);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<String[]> readCommunicationLogs(int complaintId) {
        String sql = "SELECT id, communication_type, communication_date, communication_subject, communication_content, initiator, is_resolved FROM communication_logs WHERE fk_complaint_id = ? ORDER BY communication_date";
        List<String[]> communicationLogs = new ArrayList<>();

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, complaintId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] log = {
                        Integer.toString(rs.getInt("id")),
                        rs.getString("communication_type"),
                        rs.getTimestamp("communication_date").toString(),
                        rs.getString("communication_subject"),
                        rs.getString("communication_content"),
                        rs.getString("initiator"),
                        Boolean.toString(rs.getBoolean("is_resolved"))
                };

                communicationLogs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return communicationLogs;
    }

    public boolean updateCommunicationLog(int logId, String communicationType, Timestamp communicationDate,
                                          String communicationSubject, String communicationContent, String initiator,
                                          boolean isResolved) {
        String sql = "UPDATE communication_logs SET communication_type = ?, communication_date = ?, communication_subject = ?, communication_content = ?, initiator = ?, is_resolved = ? WHERE id = ?";

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, communicationType);
            pstmt.setTimestamp(2, communicationDate);
            pstmt.setString(3, communicationSubject);
            pstmt.setString(4, communicationContent);
            pstmt.setString(5, initiator);
            pstmt.setBoolean(6, isResolved);
            pstmt.setInt(7, logId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCommunicationLog(int logId) {
        String sql = "DELETE FROM communication_logs WHERE id = ?";

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, logId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}