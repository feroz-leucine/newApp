package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ComplainantsDAO {

    public boolean createComplainant(String complainantName, String complainantType, String contactInformation,
                                     String relationshipToProduct) {
        String sql = "INSERT INTO complainants(complainant_name, complainant_type, contact_information, relationship_to_product) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, complainantName);
            pstmt.setString(2, complainantType);
            pstmt.setString(3, contactInformation);
            pstmt.setString(4, relationshipToProduct);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<String[]> readAllComplainants() {
        String sql = "SELECT id, complainant_name, complainant_type, contact_information, relationship_to_product, date_created, date_updated FROM complainants";
        List<String[]> complainants = new ArrayList<>();

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String[] complainant = new String[7];
                complainant[0] = rs.getString("id");
                complainant[1] = rs.getString("complainant_name");
                complainant[2] = rs.getString("complainant_type");
                complainant[3] = rs.getString("contact_information");
                complainant[4] = rs.getString("relationship_to_product");
                complainant[5] = rs.getTimestamp("date_created").toString();
                complainant[6] = rs.getTimestamp("date_updated").toString();
                complainants.add(complainant);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return complainants;
    }

    public boolean updateComplainant(int id, String complainantName, String complainantType, String contactInformation,
                                     String relationshipToProduct) {
        String sql = "UPDATE complainants SET complainant_name=?, complainant_type=?, contact_information=?, relationship_to_product=? WHERE id=?";

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, complainantName);
            pstmt.setString(2, complainantType);
            pstmt.setString(3, contactInformation);
            pstmt.setString(4, relationshipToProduct);
            pstmt.setInt(5, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteComplainant(int id) {
        String sql = "DELETE FROM complainants WHERE id=?";

        try (Connection conn = DatabaseUtility.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}