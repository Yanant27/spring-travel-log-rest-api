package hyk.springframework.springtravellogrestapi.exception;

/**
 * @author Htoo Yanant Khin
 */
public class TravelLogNotFoundException extends RuntimeException{
    public TravelLogNotFoundException(String message) {
        super(message);
    }
}
