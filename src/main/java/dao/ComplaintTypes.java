package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ComplaintTypesDAO {
    private static final String INSERT_QUERY = "INSERT INTO complaint_types (type_name, description, created_at, updated_at, fk_complaint_type_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE complaint_types SET type_name = ?, description = ?, updated_at = ?, fk_complaint_type_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM complaint_types WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM complaint_types";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM complaint_types WHERE id = ?";

    public void create(String typeName, String description, Timestamp createdAt, Timestamp updatedAt, int fkComplaintTypeId) throws SQLException {
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, typeName);
            preparedStatement.setString(2, description);
            preparedStatement.setTimestamp(3, createdAt);
            preparedStatement.setTimestamp(4, updatedAt);
            preparedStatement.setInt(5, fkComplaintTypeId);

            preparedStatement.executeUpdate();
        }
    }

    public void update(int id, String typeName, String description, Timestamp updatedAt, int fkComplaintTypeId) throws SQLException {
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, typeName);
            preparedStatement.setString(2, description);
            preparedStatement.setTimestamp(3, updatedAt);
            preparedStatement.setInt(4, fkComplaintTypeId);
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public List<ComplaintType> getAll() throws SQLException {
        List<ComplaintType> complaintTypes = new ArrayList<>();

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ComplaintType complaintType = mapResultSetToComplaintType(resultSet);
                complaintTypes.add(complaintType);
            }
        }

        return complaintTypes;
    }

    public ComplaintType getById(int id) throws SQLException {
        ComplaintType complaintType = null;

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                complaintType = mapResultSetToComplaintType(resultSet);
            }
        }

        return complaintType;
    }

    private ComplaintType mapResultSetToComplaintType(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String typeName = resultSet.getString("type_name");
        String description = resultSet.getString("description");
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        Timestamp updatedAt = resultSet.getTimestamp("updated_at");
        int fkComplaintTypeId = resultSet.getInt("fk_complaint_type_id");

        return new ComplaintType(id, typeName, description, createdAt, updatedAt, fkComplaintTypeId);
    }
}

package dao;

import java.sql.Timestamp;

public class ComplaintType {
    private int id;
    private String typeName;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int fkComplaintTypeId;

    public ComplaintType(int id, String typeName, String description, Timestamp createdAt, Timestamp updatedAt, int fkComplaintTypeId) {
        this.id = id;
        this.typeName = typeName;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fkComplaintTypeId = fkComplaintTypeId;
    }

    // Getters and setters for each field
}