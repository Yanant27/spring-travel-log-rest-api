package hyk.springframework.springtravellogrestapi.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Htoo Yanant Khin
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@AttributeOverride(name = "id", column = @Column(name = "travel_log_id"))
public class TravelLog extends BaseEntity {

    private String travellerName;

    private String phoneNumber;

    private String origin;

    private String destination;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private String purpose;

    private String transportationType;

    private String stayAt;

    @CreationTimestamp
    private LocalDateTime logEntryDate;

    @UpdateTimestamp
    private LocalDateTime logUpdateDateTime;
}
