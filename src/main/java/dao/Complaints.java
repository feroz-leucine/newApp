package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Complaint;
import util.DatabaseUtility;

public class ComplaintsDAO {

    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        String query = "SELECT * FROM complaints";

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                complaints.add(mapComplaint(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaints;
    }

    public Complaint getComplaintById(int id) {
        Complaint complaint = null;
        String query = "SELECT * FROM complaints WHERE id = ?";

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                complaint = mapComplaint(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaint;
    }

    public int addComplaint(Complaint complaint) {
        String query = "INSERT INTO complaints (complaint_code, complaint_date, complaint_time, product_info, complaint_description, contact_information, complaint_status, complaint_type, initial_assessment, urgency_level, fk_complainant_id, fk_complaint_status_id, fk_complaint_type_id, fk_product_id, fk_complaint_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = 0;

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, complaint.getComplaintCode());
            statement.setDate(2, new java.sql.Date(complaint.getComplaintDate().getTime()));
            statement.setTimestamp(3, new Timestamp(complaint.getComplaintTime().getTime()));
            statement.setString(4, complaint.getProductInfo());
            statement.setString(5, complaint.getComplaintDescription());
            statement.setString(6, complaint.getContactInformation());
            statement.setString(7, complaint.getComplaintStatus());
            statement.setString(8, complaint.getComplaintType());
            statement.setString(9, complaint.getInitialAssessment());
            statement.setString(10, complaint.getUrgencyLevel());
            statement.setInt(11, complaint.getComplainantId());
            statement.setInt(12, complaint.getComplaintStatusId());
            statement.setInt(13, complaint.getComplaintTypeId());
            statement.setInt(14, complaint.getProductId());
            statement.setInt(15, complaint.getComplaintId());

            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }

    public int updateComplaint(Complaint complaint) {
        String query = "UPDATE complaints SET complaint_code = ?, complaint_date = ?, complaint_time = ?, product_info = ?, complaint_description = ?, contact_information = ?, complaint_status = ?, complaint_type = ?, initial_assessment = ?, urgency_level = ?, fk_complainant_id = ?, fk_complaint_status_id = ?, fk_complaint_type_id = ?, fk_product_id = ?, fk_complaint_id = ?";
        int rowsAffected = 0;

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, complaint.getComplaintCode());
            statement.setDate(2, new java.sql.Date(complaint.getComplaintDate().getTime()));
            statement.setTimestamp(3, new Timestamp(complaint.getComplaintTime().getTime()));
            statement.setString(4, complaint.getProductInfo());
            statement.setString(5, complaint.getComplaintDescription());
            statement.setString(6, complaint.getContactInformation());
            statement.setString(7, complaint.getComplaintStatus());
            statement.setString(8, complaint.getComplaintType());
            statement.setString(9, complaint.getInitialAssessment());
            statement.setString(10, complaint.getUrgencyLevel());
            statement.setInt(11, complaint.getComplainantId());
            statement.setInt(12, complaint.getComplaintStatusId());
            statement.setInt(13, complaint.getComplaintTypeId());
            statement.setInt(14, complaint.getProductId());
            statement.setInt(15, complaint.getComplaintId());

            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }

    public int deleteComplaint(int id) {
        String query = "DELETE FROM complaints WHERE id = ?";
        int rowsAffected = 0;

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }

    private Complaint mapComplaint(ResultSet resultSet) throws SQLException {
        Complaint complaint = new Complaint();
        complaint.setId(resultSet.getInt("id"));
        complaint.setComplaintCode(resultSet.getString("complaint_code"));
        complaint.setComplaintDate(resultSet.getDate("complaint_date"));
        complaint.setComplaintTime(resultSet.getTimestamp("complaint_time"));
        complaint.setProductInfo(resultSet.getString("product_info"));
        complaint.setComplaintDescription(resultSet.getString("complaint_description"));
        complaint.setContactInformation(resultSet.getString("contact_information"));
        complaint.setComplaintStatus(resultSet.getString("complaint_status"));
        complaint.setComplaintType(resultSet.getString("complaint_type"));
        complaint.setInitialAssessment(resultSet.getString("initial_assessment"));
        complaint.setUrgencyLevel(resultSet.getString("urgency_level"));
        complaint.setComplainantId(resultSet.getInt("fk_complainant_id"));
        complaint.setComplaintStatusId(resultSet.getInt("fk_complaint_status_id"));
        complaint.setComplaintTypeId(resultSet.getInt("fk_complaint_type_id"));
        complaint.setProductId(resultSet.getInt("fk_product_id"));
        complaint.setComplaintId(resultSet.getInt("fk_complaint_id"));

        return complaint;
    }
}