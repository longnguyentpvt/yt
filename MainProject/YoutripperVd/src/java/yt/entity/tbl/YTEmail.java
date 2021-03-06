package yt.entity.tbl;
// Generated Jan 13, 2017 3:31:54 PM by Hibernate Tools 4.3.1

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PartnerCountry generated by hbm2java
 */
@Entity
@Table(name = "YTEmail", schema = "dbo")
public class YTEmail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmailID", unique = true, nullable = false)
    private long emailID;

    @Column(name = "FunctionName", nullable = false)
    private String functionName;

    @Column(name = "ReceiverEmail")
    private String receiverEmail;

    @Column(name = "EmailTitle")
    private String emailTitle;

    @Column(name = "EmailContent")
    private String emailContent;

    @Column(name = "EmailSent")
    private boolean emailSent;

    @Column(name = "ResponseCode")
    private long responseCode;

    @Column(name = "ExecutionDateTime")
    private long executionDateTime;

    public YTEmail() {
    }

    public YTEmail(String functionName, String receiverEmail, String emailTitle, String emailContent, boolean emailSent, long executionDateTime, int responseCode) {
        this.functionName = functionName;
        this.receiverEmail = receiverEmail;
        this.emailTitle = emailTitle;
        this.emailContent = emailContent;
        this.emailSent = emailSent;
        this.executionDateTime = executionDateTime;
        this.responseCode = responseCode;
    }

    public long getEmailID() {
        return emailID;
    }

    public void setEmailID(long emailID) {
        this.emailID = emailID;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public boolean isEmailSent() {
        return emailSent;
    }

    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

    public long getExecutionDateTime() {
        return executionDateTime;
    }

    public void setExecutionDateTime(long executionDateTime) {
        this.executionDateTime = executionDateTime;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public long getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(long responseCode) {
        this.responseCode = responseCode;
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

}
