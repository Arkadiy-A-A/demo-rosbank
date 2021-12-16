package ru.ibs.company.framework.utils;

import java.nio.file.Paths;

public class FileUtils {

    public static String getAbsolutePathFile(String relativeFilePath){
        return Paths.get(relativeFilePath).toFile().getAbsolutePath();
    }

}
