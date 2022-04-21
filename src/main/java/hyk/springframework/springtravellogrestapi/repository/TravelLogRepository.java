package hyk.springframework.springtravellogrestapi.repository;

import hyk.springframework.springtravellogrestapi.domain.TravelLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Htoo Yanant Khin
 */
public interface TravelLogRepository extends JpaRepository<TravelLog, Long> {
}
