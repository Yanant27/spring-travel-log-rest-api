package hyk.springframework.springtravellogrestapi.bootstrap;

import hyk.springframework.springtravellogrestapi.domain.TravelLog;
import hyk.springframework.springtravellogrestapi.repository.TravelLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final TravelLogRepository travelLogRepository;

    public Bootstrap(TravelLogRepository travelLogRepository) {
        this.travelLogRepository = travelLogRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadTravellLog();
    }

    private void loadTravellLog() {
        TravelLog log1 = TravelLog.builder()
                .travellerName("Ye Aung")
                .phoneNumber("09-123456781")
                .origin("Mudon")
                .destination("Yangon")
                .departureDate(LocalDate.of(2022,4,6))
                .arrivalDate(LocalDate.of(2022,4,8))
                .build();

        TravelLog log2 = TravelLog.builder()
                .travellerName("Khin Pyone Yee")
                .phoneNumber("09-123456782")
                .origin("Mudon")
                .destination("Yangon")
                .departureDate(LocalDate.of(2022,4,6))
                .arrivalDate(LocalDate.of(2022,4,8))
                .build();

        TravelLog log3 = TravelLog.builder()
                .travellerName("War War")
                .phoneNumber("09-123456783")
                .origin("Mudon")
                .destination("Yangon")
                .departureDate(LocalDate.of(2022,4,6))
                .arrivalDate(LocalDate.of(2022,4,8))
                .build();

        TravelLog log4 = TravelLog.builder()
                .travellerName("Lin Htet Aung")
                .phoneNumber("09-123456784")
                .origin("Thanlyin")
                .destination("Pyin Oo Lwin")
                .departureDate(LocalDate.of(2022,4,17))
                .arrivalDate(LocalDate.of(2022,4,19))
                .build();

        TravelLog log5 = TravelLog.builder()
                .travellerName("Cho San")
                .phoneNumber("09-123456785")
                .origin("Thanlyin")
                .destination("Pyin Oo Lwin")
                .departureDate(LocalDate.of(2022,4,17))
                .arrivalDate(LocalDate.of(2022,4,19))
                .build();

        travelLogRepository.save(log1);
        travelLogRepository.save(log2);
        travelLogRepository.save(log3);
        travelLogRepository.save(log4);
        travelLogRepository.save(log5);

        log.info("***** Sample Data loaded *****");
    }
}
