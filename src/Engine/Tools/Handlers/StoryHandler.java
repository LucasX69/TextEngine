package Engine.Tools.Handlers;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StoryHandler {

    File file;
    FileInputStream fileInputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    String lastChoice;

    StoryHandler(String sPath) {
        Path path = Paths.get(sPath);
        file = path.toFile();
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inputStreamReader = new InputStreamReader(fileInputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    public void readChoice() {
        
    }

    private boolean isTitle(String line) {
        return false;
    }

    private boolean isSection(String line) {
        return false;
    }

    private boolean isSubSection(String line) {
        return false;
    }

    private boolean isChoice(String line) {
        return false;
    }

    private boolean isVariable(String line) {
        return false;
    }

    private boolean isNewLine(String line) {
        return false;
    }

}
