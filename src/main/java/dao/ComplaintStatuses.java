package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ComplaintStatusesDAO {

    private static final String INSERT_COMPLAINT_STATUS = "INSERT INTO complaint_statuses(status_name, description, created_at, updated_at) VALUES(?, ?, ?, ?)";
    private static final String SELECT_COMPLAINT_STATUS_BY_ID = "SELECT * FROM complaint_statuses WHERE id = ?";
    private static final String SELECT_ALL_COMPLAINT_STATUSES = "SELECT * FROM complaint_statuses";
    private static final String UPDATE_COMPLAINT_STATUS = "UPDATE complaint_statuses SET status_name = ?, description = ?, updated_at = ? WHERE id = ?";
    private static final String DELETE_COMPLAINT_STATUS = "DELETE FROM complaint_statuses WHERE id = ?";

    public ComplaintStatus insertComplaintStatus(ComplaintStatus complaintStatus) throws SQLException {
        Connection connection = DatabaseUtility.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMPLAINT_STATUS);
        preparedStatement.setString(1, complaintStatus.getStatusName());
        preparedStatement.setString(2, complaintStatus.getDescription());
        preparedStatement.setTimestamp(3, complaintStatus.getCreatedAt());
        preparedStatement.setTimestamp(4, complaintStatus.getUpdatedAt());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating complaint status failed, no rows affected.");
        }

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                complaintStatus.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating complaint status failed, no ID obtained.");
            }
        }

        return complaintStatus;
    }

    public ComplaintStatus selectComplaintStatusById(int id) throws SQLException {
        Connection connection = DatabaseUtility.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPLAINT_STATUS_BY_ID);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String statusName = resultSet.getString("status_name");
            String description = resultSet.getString("description");
            Timestamp createdAt = resultSet.getTimestamp("created_at");
            Timestamp updatedAt = resultSet.getTimestamp("updated_at");

            return new ComplaintStatus(id, statusName, description, createdAt, updatedAt);
        } else {
            return null;
        }
    }

    public List<ComplaintStatus> selectAllComplaintStatuses() throws SQLException {
        Connection connection = DatabaseUtility.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMPLAINT_STATUSES);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<ComplaintStatus> complaintStatuses = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String statusName = resultSet.getString("status_name");
            String description = resultSet.getString("description");
            Timestamp createdAt = resultSet.getTimestamp("created_at");
            Timestamp updatedAt = resultSet.getTimestamp("updated_at");

            complaintStatuses.add(new ComplaintStatus(id, statusName, description, createdAt, updatedAt));
        }

        return complaintStatuses;
    }

    public boolean updateComplaintStatus(ComplaintStatus complaintStatus) throws SQLException {
        Connection connection = DatabaseUtility.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMPLAINT_STATUS);
        preparedStatement.setString(1, complaintStatus.getStatusName());
        preparedStatement.setString(2, complaintStatus.getDescription());
        preparedStatement.setTimestamp(3, complaintStatus.getUpdatedAt());
        preparedStatement.setInt(4, complaintStatus.getId());

        int affectedRows = preparedStatement.executeUpdate();

        return affectedRows > 0;
    }

    public boolean deleteComplaintStatus(int id) throws SQLException {
        Connection connection = DatabaseUtility.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMPLAINT_STATUS);
        preparedStatement.setInt(1, id);

        int affectedRows = preparedStatement.executeUpdate();

        return affectedRows > 0;
    }
}