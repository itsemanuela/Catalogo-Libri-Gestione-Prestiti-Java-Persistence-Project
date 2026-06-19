package entities.exceptions;

public class SalvataggioException extends RuntimeException {

    // costruttore semplice per passare un messaggio personalizzato
    public SalvataggioException(String messaggio) {
        super(messaggio);
    }


}