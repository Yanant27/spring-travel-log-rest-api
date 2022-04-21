package hyk.springframework.springtravellogrestapi.api.v1.mapper;

import hyk.springframework.springtravellogrestapi.api.v1.model.TravelLogDto;
import hyk.springframework.springtravellogrestapi.domain.TravelLog;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Htoo Yanant Khin
 */
@Mapper
public interface TravelLogMapper {
    TravelLogDto travelLogToTravelLogDto(TravelLog travelLog);
    TravelLog travelLogDtoToTravelLog(TravelLogDto travelLogDto);
    List<TravelLogDto> travelLogsToTravelLogDtos(List<TravelLog> travelLogs);
    List<TravelLog> travelLogDtosToTravelLogs(List<TravelLogDto> travelLogDtos);
}
