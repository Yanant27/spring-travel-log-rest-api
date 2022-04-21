package hyk.springframework.springtravellogrestapi.api.v1.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TravelLogDto {

    private String travellerName;

    private String phoneNumber;

    private String origin;

    private String destination;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private String purpose;

    private String transportationType;

    private String stayAt;
}
