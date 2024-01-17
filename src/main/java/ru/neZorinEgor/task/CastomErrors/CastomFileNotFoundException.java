package ru.neZorinEgor.task.CastomErrors;

import java.io.FileNotFoundException;

public class CastomFileNotFoundException extends FileNotFoundException {
    public CastomFileNotFoundException(String mesage){
        super(mesage);
    }

}
