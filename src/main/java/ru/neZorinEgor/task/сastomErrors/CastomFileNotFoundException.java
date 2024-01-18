package ru.neZorinEgor.task.сastomErrors;

import java.io.FileNotFoundException;

public class CastomFileNotFoundException extends FileNotFoundException {
    public CastomFileNotFoundException(String mesage){
        super(mesage);
    }

}
