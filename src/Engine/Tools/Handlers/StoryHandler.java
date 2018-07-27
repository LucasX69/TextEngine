package Engine.Tools.Handlers;

import Engine.Tools.Choice;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StoryHandler {

    private FileInputStream fileInputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    StoryHandler(String sPath) {
        Path path = Paths.get(sPath);
        File file = path.toFile();
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inputStreamReader = new InputStreamReader(fileInputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    public String read() throws IOException {
        String line;
        StringBuilder text = new StringBuilder();

        while((line = bufferedReader.readLine()) != null) {
            if (isText(line)) {
                text.append(line);
            } else if (containsVariable(line)) {
                // TODO needs to check if the variable is being assigned or being shown, act accordingly
            } else if (isChoice(line)) {
                readChoices(line);
            }
        }
        return text.toString();
    }

    /**
     * @param line The line that contains the choices, they should be on their own line.
     * @return A list of all the choices on this line
     */
    private ArrayList<Choice> readChoices(String line) {
        StringBuilder prefix = new StringBuilder();
        StringBuilder name = new StringBuilder();
        boolean nameDone = false;
        StringBuilder description = new StringBuilder();
        StringBuilder suffix = new StringBuilder();

        // An idiotic for loop to retrieve the choices
        ArrayList<Choice> choices = new ArrayList<>();
        for (char c : line.toCharArray()) {
            if (!prefix.toString().equals("[[")) {
                if (c == '[') {
                    prefix.append(c);
                }
            } else {
                if (c != '|' && !nameDone) {
                    name.append(c);
                } else {
                    if (!nameDone) {
                        nameDone = true;
                    }
                    if (c != ']') {
                        description.append(c);
                    } else {
                        suffix.append(c);
                        if (suffix.toString().equals("]]")) {
                            String text = findChoice(name.toString());
                            boolean clear = text.startsWith("1");
                            text = text.substring(2, text.length());
                            choices.add(new Choice(clear, text, name.toString(), description.toString()));
                            prefix = new StringBuilder();
                            name = new StringBuilder();
                            nameDone = false;
                            description = new StringBuilder();
                            suffix = new StringBuilder();
                        }
                    }
                }
            }
        }
        return choices;
    }

    /**
     * @param name The name of the choice
     * @return The text that the choice needs.
     * Adds a 1 or a 2 at the beginning, depending on if it is a Section or a Sub-Section respectively.
     * @throws IOException If the line can not be read.
     */
    private String findChoice(String name) throws IOException {
        String line;
        StringBuilder text = new StringBuilder();
        boolean found = false;
        while((line = bufferedReader.readLine()) != null) {
            if (isSubSection(line)) {
                // Sub-Section
                if (line.contains(name)) {
                    found = true;
                    text.append('2');
                }
            } else if(isSection(line)) {
                // Section
                if (line.contains(name)) {
                    found = true;
                    text.append('1');
                }
            } else if((isSection(line) || isSubSection(line)) && found) {
                break;
            } else {
                text.append(" ").append(line);
            }
        }
        return text.toString();
    }

    /**
     * @param line The line that is being read
     * @return True if it is not any of the other special lines
     */
    private boolean isText(String line) {
        return !isSection(line) && !isSubSection(line) && !isChoice(line) && !containsVariable(line) && !isComment(line);
    }

    /**
     * @param line The line that is being read
     * @return True if the line is the start of a Section
     */
    private boolean isSection(String line) {
        return line.startsWith("#") && line.charAt(1) != '#';
    }

    /**
     * @param line The line that is being read
     * @return True if the line is the start of a Sub-Section
     */
    private boolean isSubSection(String line) {
        return line.startsWith("##");
    }

    /**
     * @param line The line that is being read
     * @return True if it the line is a choice line
     */
    private boolean isChoice(String line) {
        return line.startsWith("[[") && line.endsWith("]]");
    }

    /**
     * @param line The line that is being read
     * @return True if it the line contains a variable
     */
    private boolean containsVariable(String line) {
        return line.contains("$$");
    }

    /**
     * @param line The line that is being read
     * @return True if the line is a comments, should be ignored
     */
    private boolean isComment(String line) {
        return line.startsWith("--");
    }

}
