package hyk.springframework.springtravellogrestapi.controller.api.v1;

import hyk.springframework.springtravellogrestapi.api.v1.mapper.TravelLogMapper;
import hyk.springframework.springtravellogrestapi.api.v1.model.TravelLogDto;
import hyk.springframework.springtravellogrestapi.domain.TravelLog;
import hyk.springframework.springtravellogrestapi.exception.TravelLogNotFoundException;
import hyk.springframework.springtravellogrestapi.service.TravelLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Htoo Yanant Khin
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/travellogs")
public class TravelLogController {

    private final TravelLogService travelLogService;
    private final TravelLogMapper travelLogMapper;

    public TravelLogController(TravelLogService travelLogService, TravelLogMapper travelLogMapper) {
        this.travelLogService = travelLogService;
        this.travelLogMapper = travelLogMapper;
    }

    @GetMapping
    public ResponseEntity<List<TravelLogDto>> getAllTravelLogs() {
        log.info("***** Get all travel logs *****");
        List<TravelLog> travelLogDtos = travelLogService.getAllTravelLogs();
        if (travelLogDtos.isEmpty()) {
            throw new TravelLogNotFoundException("No Travel Log !");
        }
        return new ResponseEntity<>(travelLogMapper.travelLogsToTravelLogDtos(travelLogDtos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelLogDto> findTravelLogById(@PathVariable Long id) {
        log.info("***** Get travel log with requested ID *****");
        TravelLog travelLog = travelLogService.getTravelLogById(id);
        if (travelLog == null) {
            throw  new TravelLogNotFoundException("No Travel Log Found for ID=" + id);
        }
        return new ResponseEntity<>(travelLogMapper.travelLogToTravelLogDto(travelLog), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TravelLogDto> createTravelLog(@RequestBody TravelLogDto travelLogDto) {
        log.info("***** Create new travel log *****");
        HttpHeaders headers = new HttpHeaders();
        TravelLog savedTravelLog = travelLogService
                .saveTravelLog(travelLogMapper.travelLogDtoToTravelLog(travelLogDto));
        headers.setLocation(UriComponentsBuilder.newInstance()
                .path("/api/travellogs/{id}").buildAndExpand(savedTravelLog.getId()).toUri());
        return new ResponseEntity<>(travelLogMapper.travelLogToTravelLogDto(savedTravelLog),
                headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelLogDto> updateTravelLog(@PathVariable Long id, @RequestBody TravelLogDto travelLogDto) {
        log.info("***** Update existing  travel log with requested ID *****");
        TravelLog travelLog = travelLogService.getTravelLogById(id);
        if (travelLog == null) {
            throw  new TravelLogNotFoundException("Update Failed ! No Travel Log for ID=" + id);
        }

        travelLog.setTravellerName(travelLogDto.getTravellerName());
        travelLog.setPhoneNumber(travelLogDto.getPhoneNumber());
        travelLog.setOrigin(travelLogDto.getOrigin());
        travelLog.setDestination(travelLogDto.getDestination());
        travelLog.setPurpose(travelLogDto.getPurpose());
        travelLog.setDepartureDate(travelLogDto.getDepartureDate());
        travelLog.setArrivalDate(travelLogDto.getArrivalDate());
        travelLog.setTransportationType(travelLogDto.getTransportationType());
        travelLog.setStayAt(travelLogDto.getStayAt());
        travelLogService.saveTravelLog(travelLog);
        return new ResponseEntity<>(travelLogMapper.travelLogToTravelLogDto(travelLog), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravelLogById(@PathVariable Long id) {
        log.info("***** Delete travel log with requested ID *****");
        TravelLog travelLog = travelLogService.getTravelLogById(id);
        if (travelLog == null) {
            throw  new TravelLogNotFoundException("Deletion Failed ! No Travel Log for ID=" + id);
        }
        travelLogService.deleteTravelLogById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
