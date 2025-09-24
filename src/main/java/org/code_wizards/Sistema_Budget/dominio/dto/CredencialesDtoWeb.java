package org.code_wizards.Sistema_Budget.dominio.dto;

import java.time.LocalDateTime;

public class CredencialesJsfDto {
    private Long idCredencial;
    private Long userID;
    private String email;
    private String password;
    private LocalDateTime dateRecord;

    public CredencialesJsfDto() {}

    public CredencialesJsfDto(Long idCredencial, Long userID, String email, String password, LocalDateTime dateRecord) {
        this.idCredencial = idCredencial;
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.dateRecord = dateRecord;
    }

    public Long getIdCredencial() { return idCredencial; }
    public void setIdCredencial(Long idCredencial) { this.idCredencial = idCredencial; }

    public Long getUserID() { return userID; }
    public void setUserID(Long userID) { this.userID = userID; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getDateRecord() { return dateRecord; }
    public void setDateRecord(LocalDateTime dateRecord) { this.dateRecord = dateRecord; }
}
