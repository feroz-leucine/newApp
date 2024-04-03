package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import utl.DatabaseUtility;

public class Actions {
    private int id;
    private String actionType;
    private String description;
    private Date initiationDate;
    private Date completionDate;
    private String status;
    private String impactLevel;
    private String responsibleDepartment;
    private String expectedOutcome;
    private int fkInvestigationId;

    public Actions() {
    }

    public Actions(int id, String actionType, String description, Date initiationDate, Date completionDate, String status,
            String impactLevel, String responsibleDepartment, String expectedOutcome, int fkInvestigationId) {
        this.id = id;
        this.actionType = actionType;
        this.description = description;
        this.initiationDate = initiationDate;
        this.completionDate = completionDate;
        this.status = status;
        this.impactLevel = impactLevel;
        this.responsibleDepartment = responsibleDepartment;
        this.expectedOutcome = expectedOutcome;
        this.fkInvestigationId = fkInvestigationId;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getInitiationDate() {
        return initiationDate;
    }

    public void setInitiationDate(Date initiationDate) {
        this.initiationDate = initiationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImpactLevel() {
        return impactLevel;
    }

    public void setImpactLevel(String impactLevel) {
        this.impactLevel = impactLevel;
    }

    public String getResponsibleDepartment() {
        return responsibleDepartment;
    }

    public void setResponsibleDepartment(String responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment;
    }

    public String getExpectedOutcome() {
        return expectedOutcome;
    }

    public void setExpectedOutcome(String expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }

    public int getFkInvestigationId() {
        return fkInvestigationId;
    }

    public void setFkInvestigationId(int fkInvestigationId) {
        this.fkInvestigationId = fkInvestigationId;
    }

    // CRUD operations

    public List<Actions> getAllActions() {
        List<Actions> actionsList = new ArrayList<>();
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM actions");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Actions action = new Actions(
                        resultSet.getInt("id"),
                        resultSet.getString("action_type"),
                        resultSet.getString("description"),
                        resultSet.getDate("initiation_date"),
                        resultSet.getDate("completion_date"),
                        resultSet.getString("status"),
                        resultSet.getString("impact_level"),
                        resultSet.getString("responsible_department"),
                        resultSet.getString("expected_outcome"),
                        resultSet.getInt("fk_investigation_id")
                );

                actionsList.add(action);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actionsList;
    }

    public Actions getActionById(int id) {
        Actions action = null;
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM actions WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                action = new Actions(
                        resultSet.getInt("id"),
                        resultSet.getString("action_type"),
                        resultSet.getString("description"),
                        resultSet.getDate("initiation_date"),
                        resultSet.getDate("completion_date"),
                        resultSet.getString("status"),
                        resultSet.getString("impact_level"),
                        resultSet.getString("responsible_department"),
                        resultSet.getString("expected_outcome"),
                        resultSet.getInt("fk_investigation_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return action;
    }

    public boolean insertAction(Actions action) {
        boolean success = false;
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO actions (action_type, description, initiation_date, completion_date, status, impact_level, responsible_department, expected_outcome, fk_investigation_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, action.getActionType());
            statement.setString(2, action.getDescription());
            statement.setDate(3, action.getInitiationDate());
            statement.setDate(4, action.getCompletionDate());
            statement.setString(5, action.getStatus());
            statement.setString(6, action.getImpactLevel());
            statement.setString(7, action.getResponsibleDepartment());
            statement.setString(8, action.getExpectedOutcome());
            statement.setInt(9, action.getFkInvestigationId());

            int rowsAffected = statement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean updateAction(Actions action) {
        boolean success = false;
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE actions SET action_type = ?, description = ?, initiation_date = ?, completion_date = ?, status = ?, impact_level = ?, responsible_department = ?, expected_outcome = ?, fk_investigation_id = ? WHERE id = ?");
            statement.setString(1, action.getActionType());
            statement.setString(2, action.getDescription());
            statement.setDate(3, action.getInitiationDate());
            statement.setDate(4, action.getCompletionDate());
            statement.setString(5, action.getStatus());
            statement.setString(6, action.getImpactLevel());
            statement.setString(7, action.getResponsibleDepartment());
            statement.setString(8, action.getExpectedOutcome());
            statement.setInt(9, action.getFkInvestigationId());
            statement.setInt(10, action.getId());

            int rowsAffected = statement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean deleteAction(int id) {
        boolean success = false;
        try (Connection connection = DatabaseUtility.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM actions WHERE id = ?");
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}