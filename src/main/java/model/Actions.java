package model;

import java.time.LocalDate;
import java.time.LocalTimestamp;
import java.util.List;

public class Actions {
    private int id;
    private String actionType;
    private String description;
    private LocalDate initiationDate;
    private LocalDate completionDate;
    private String status;
    private String impactLevel;
    private String responsibleDepartment;
    private String expectedOutcome;
    private int fkInvestigationId;
    private List<InvestigationActions> investigationActions;

    // Default constructor
    public Actions() {
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getActionType() {
        return actionType;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getInitiationDate() {
        return initiationDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public String getStatus() {
        return status;
    }

    public String getImpactLevel() {
        return impactLevel;
    }

    public String getResponsibleDepartment() {
        return responsibleDepartment;
    }

    public String getExpectedOutcome() {
        return expectedOutcome;
    }

    public int getFkInvestigationId() {
        return fkInvestigationId;
    }

    public List<InvestigationActions> getInvestigationActions() {
        return investigationActions;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInitiationDate(LocalDate initiationDate) {
        this.initiationDate = initiationDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImpactLevel(String impactLevel) {
        this.impactLevel = impactLevel;
    }

    public void setResponsibleDepartment(String responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment;
    }

    public void setExpectedOutcome(String expectedOutcome) {
        this.expectedOutcome = expectedOutcome;
    }

    public void setFkInvestigationId(int fkInvestigationId) {
        this.fkInvestigationId = fkInvestigationId;
    }

    public void setInvestigationActions(List<InvestigationActions> investigationActions) {
        this.investigationActions = investigationActions;
    }
}