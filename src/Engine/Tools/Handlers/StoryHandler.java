package Engine.Tools.Handlers;

import Engine.Characters.Character;
import Engine.Tools.Choice;
import Engine.Tools.Statics;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Engine.Tools.Statics.characters;

public class StoryHandler {

    private File file;
    private FileInputStream fileInputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public StoryHandler(String sPath) {
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

    /**
     * This method is used to read from the Story file.
     * @param choice The name of the choice, can be empty, if empty, it will just start from the start of the file, otherwise it will start at the choice.
     * @return The text that needs to be displayed.
     * @throws IOException If there is no file to be read.
     */
    public String read(String... choice) throws IOException {
        String line;
        StringBuilder text = new StringBuilder();

        if (choice.length == 0) {
            while ((line = bufferedReader.readLine()) != null) {
                if (isText(line)) {
                    text.append(line).append(" ");
                } else if (isNewLine(line)) {
                    text.append("\n\n");
                } else if (containsVariable(line)) {
                    // TODO needs to check if the variable is being assigned or being shown, act accordingly
                    text.append(handleVariable(line));
                } else if (isChoice(line)) {
                    handleChoices(readChoices(line));
                    break;
                }
            }
            return text.toString();
        } else {
            String txt = findChoice(choice[0]);
            if (txt.isEmpty()) {
                return txt;
            } else {
                return txt.substring(2, txt.length());
            }
        }
    }


    /**
     * @param line The line that contains the choices, they should be on their own line.
     * @return A list of all the choices on this line
     * @throws IOException If there is no file to be read.
     */
    private ArrayList<Choice> readChoices(String line) throws IOException {
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
                } else {
                    // TODO make an exception happen, I guess a custom one.
                    System.out.println("Something is wrong.");
                    break;
                }
            } else {
                if (c != '|' && !nameDone) {
                    name.append(c);
                } else {
                    if (!nameDone) {
                        nameDone = true;
                    }
                    if (c != ']' && suffix.toString().isEmpty()) {
                        description.append(c);
                    } else {
                        // Simple check to see if the suffix is actually 2x ] and not 1x or something in between.
                        if (c != ']') {
                            // TODO make an exception happen
                            System.out.println("Something went wrong.");
                        } else {
                            suffix.append(c);

                            if (suffix.toString().equals("]]")) {
                                String text = findChoice(name.toString());
                                boolean clear = text.startsWith("1");
                                text = text.substring(2, text.length());
                                choices.add(new Choice(clear, text, name.toString(), description.toString(), this));
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
        }
        return choices;
    }

    /**
     * Will go through the file to look for a specific choice.
     * @param name The name of the choice
     * @return The text that the choice needs.
     * Adds a 1 or a 2 at the beginning, depending on if it is a Section or a Sub-Section respectively.
     * @throws IOException If the line can not be read.
     */
    private String findChoice(String name) throws IOException {
        String line;
        StringBuilder text = new StringBuilder();
        boolean found = false;
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        while((line = bufferedReader.readLine()) != null) {
            if (isSubSection(line) && !found) {
                // Sub-Section
                if (line.contains(name)) {
                    if (line.substring(2, name.length() + 2).equals(name) && line.length() == name.length() + 2) {
                        found = true;
                        text.append('2');
                    }
                }
            } else if(isSection(line) && !found) {
                // Section
                if (line.contains(name)) {
                    if (line.substring(1, name.length() + 1).equals(name) && line.length() == name.length() + 1) {
                        found = true;
                        text.append('1');
                    }
                }
            } else if((isSection(line) || isSubSection(line)) && found) {
                break;
            } else if (isChoice(line) && found){
                handleChoices(readChoices(line));
                break;
            } else if (found){
                if (containsVariable(line)) {
                    text.append(handleVariable(line));
                } else {
                    text.append(" ").append(line);
                }
            }
        }
        return text.toString();
    }

    /**
     * This method exists so it can be done from multiple places
     * @param choices The choices that were found
     */
    private void handleChoices(ArrayList<Choice> choices) {
        // TODO make this better
        for (int i = 0; i < choices.size(); i++) {
            Statics.controlWindow.setButton(choices.get(i), Statics.controlWindow.getListRow().get(i));
        }
    }

    private String handleVariable(String line) {
        StringBuilder prefix = new StringBuilder();
        StringBuilder suffix = new StringBuilder();
        StringBuilder text = new StringBuilder();
        StringBuilder name = new StringBuilder();
        boolean nameDone = false;
        StringBuilder var = new StringBuilder();
        boolean varDone = false;
        StringBuilder cmd = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '$' && !prefix.toString().equals("$$")) {
                prefix.append(c);
            } else if (c == '$' && prefix.toString().equals("$$") && !suffix.toString().equals("$$")){
                suffix.append(c);
            } else if(prefix.toString().equals("$$") && !suffix.toString().equals("$$")) {
                if (!nameDone) {
                    if (c == '.') {
                        nameDone = true;
                    } else {
                        name.append(c);
                    }
                } else if(!varDone) {
                    if (c == '.') {
                        // TODO let it check for commands, ENUM??
                        varDone = true;
                    } else {
                        var.append(c);
                    }
                }
            } else {
                if (suffix.toString().equals("$$") && cmd.toString().isEmpty()) {
                    for (Character character : characters) {
                        if (character.name.toLowerCase().equals(name.toString().toLowerCase()) ||
                            character.type.name().toLowerCase().equals(name.toString().toLowerCase())) {
                            for (Field field : character.getClass().getDeclaredFields()) {
                                if (field.getName().toLowerCase().equals(var.toString().toLowerCase())) {
                                    try {
                                        text.append(field.get(character));
                                        suffix = new StringBuilder();
                                        prefix = new StringBuilder();
                                        name = new StringBuilder();
                                        var = new StringBuilder();
                                        cmd = new StringBuilder();
                                        nameDone = false;
                                        varDone = false;
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
                text.append(c);
            }
        }
        return text.toString();
    }

    /**
     * @param line The line that is being read
     * @return True if the line is empty
     */
    private boolean isNewLine(String line) {
        return line.isEmpty();
    }

    /**
     * @param line The line that is being read
     * @return True if it is not any of the other special lines
     */
    private boolean isText(String line) {
        return !isSection(line) && !isSubSection(line) && !isChoice(line) && !containsVariable(line) && !isComment(line) && !isNewLine(line);
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