package com.javarush.task.task38.task3804;

public class ExceptionFactory {

    public static Throwable getFactory(Enum e){
        Throwable ex = null;

        if (e != null){
            String message = e.name().charAt(0) + e.name().substring(1).toLowerCase().replace("_", " ");

            if (e instanceof ApplicationExceptionMessage)
                ex = new Exception(message);

            else if (e instanceof DatabaseExceptionMessage)
                ex = new RuntimeException(message);

            else if (e instanceof UserExceptionMessage)
                ex = new Error(message);

            else ex = new IllegalArgumentException();
        }else ex = new IllegalArgumentException();
        return ex;
    }
}
