package lk.ijse.paymentservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Dasun Wijayathilaka
 * @created 6/20/2025
 * @github https://github.com/dasunwijayathilaka
 * @project Smart-Parking-Management-System-Microservice-Based-Application
 */

@Data
public class Receipt {
    private String receiptNumber;
    private String paymentId;
    private String businessName;
    private String businessAddress;
    private String businessPhone;
    private LocalDateTime issueDateTime;

    private String customerId;
    private String customerName;

    private String spaceId;
    private String spaceNumber;
    private String location;
    private String vehicleId;
    private String licensePlate;

    private LocalDateTime parkingStartTime;
    private LocalDateTime parkingEndTime;
    private long durationHours;
    private long durationMinutes;

    private double hourlyRate;
    private double subtotal;
    private double taxAmount;
    private double totalAmount;
    private String paymentMethod;
    private String transactionReference;
    private String currency;

    private String termsAndConditions;
    private String thankYouMessage;

    public Receipt() {
        this.businessName = "Smart Parking Management System";
        this.businessAddress = "123 Smart City Avenue, Colombo 03, Sri Lanka";
        this.businessPhone = "+94 11 234 5678";
        this.currency = "LKR";
        this.termsAndConditions = "All payments are non-refundable. Parking violations may result in additional charges.";
        this.thankYouMessage = "Thank you for using Smart Parking System!";
    }
}