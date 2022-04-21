package hyk.springframework.springtravellogrestapi.service;

import hyk.springframework.springtravellogrestapi.api.v1.mapper.TravelLogMapper;
import hyk.springframework.springtravellogrestapi.domain.TravelLog;
import hyk.springframework.springtravellogrestapi.repository.TravelLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Htoo Yanant Khin
 */
@Service
public class TravelLogServiceImpl implements TravelLogService {

    private final TravelLogRepository travelLogRepository;
    private final TravelLogMapper travelLogMapper;

    public TravelLogServiceImpl(TravelLogRepository travelLogRepository, TravelLogMapper travelLogMapper) {
        this.travelLogRepository = travelLogRepository;
        this.travelLogMapper = travelLogMapper;
    }

    @Override
    public List<TravelLog> getAllTravelLogs() {
        return travelLogRepository.findAll();
    }

    @Override
    public TravelLog getTravelLogById(Long id) {
        return travelLogRepository.findById(id).orElse(null);
    }

    @Override
    public TravelLog saveTravelLog(TravelLog travelLog) {
        return travelLogRepository.save(travelLog);
    }

    @Override
    public void deleteTravelLogById(Long id) {
        travelLogRepository.deleteById(id);
    }
}
