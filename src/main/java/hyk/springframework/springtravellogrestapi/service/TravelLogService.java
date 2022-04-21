package hyk.springframework.springtravellogrestapi.service;

import hyk.springframework.springtravellogrestapi.domain.TravelLog;

import java.util.List;

/**
 * @author Htoo Yanant Khin
 */
public interface TravelLogService {
    List<TravelLog>  getAllTravelLogs();
    TravelLog getTravelLogById(Long id);
    TravelLog saveTravelLog(TravelLog travelLog);
    void deleteTravelLogById(Long id);
}
