package com.ascendingdc.learnrestapi.exception;

import java.io.Serial;

public class BandNotFoundException extends RuntimeException{


    @Serial
    private static final long serialVersionUID = 6128332637606471451L;

    public BandNotFoundException(){
        super();
    }

    public BandNotFoundException(String arg0){
        super(arg0);
    }

    public BandNotFoundException(Throwable cause){
        super(cause);
    }

    public BandNotFoundException(String errorMsg, Throwable cause){
        super(errorMsg, cause);
    }

}
