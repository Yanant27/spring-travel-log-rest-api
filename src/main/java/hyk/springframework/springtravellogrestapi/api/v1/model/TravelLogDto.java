package hyk.springframework.springtravellogrestapi.api.v1.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TravelLogDto {

    private String travellerName;

    private String phoneNumber;

    private String origin;

    private String destination;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private LocalDateTime createdTimestamp;

    private LocalDateTime lastModifiedTimestamp;
}
