package exceptionHandling.custom;

public class CustomException extends Exception{

    CustomException(String message){
        super(message);
    }

    CustomException(String message, Throwable cause){
        super(message,cause);
    }

    CustomException(Throwable cause){
        super(cause);
    }
}
