package Engine.Tools.Handlers;

import Engine.Tools.Choice;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StoryHandler {

    private FileInputStream fileInputStream;
    private BufferedReader bufferedReader;
    String lastChoice;

    StoryHandler(String sPath) {
        Path path = Paths.get(sPath);
        File file = path.toFile();
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    public void readChoice() throws IOException {
        String line;

        // title = 1, section = 2, sub-section = 3
        int section = 0;

        while((line = bufferedReader.readLine()) != null) {
            if (isSubSection(line)) {
                section = 3;
            } else if(isSection(line)) {
                section = 2;
            } else if(isTitle(line)) {
                section = 1;
            } else if(isChoice(line)) {
                StringBuilder prefix = new StringBuilder();
                StringBuilder name = new StringBuilder();
                boolean nameDone = false;
                StringBuilder description = new StringBuilder();
                StringBuilder suffix = new StringBuilder();

                // An idiotic for loop to retrieve the choices
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
                                nameDone= true;
                            }
                            if (c != ']') {
                                description.append(c);
                            } else {
                                suffix.append(c);
                                if (suffix.toString().equals("]]")) {
                                    String text = findChoice(name.toString());
                                    boolean clear = text.startsWith("2");
                                    text = text.substring(2, text.length());
                                    // TODO make this choice do something
                                    Choice choice = new Choice(clear, text, name.toString(), description.toString());
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
            } else {
                if (isComment(line)) {
                    break;
                } else {
                    if (containsVariable(line)) {

                    } else {

                    }
                }

            }
        }
    }

    private String findChoice(String name) throws IOException {
        String line;
        StringBuilder text = new StringBuilder();
        boolean found = false;
        while((line = bufferedReader.readLine()) != null) {
            if (isSubSection(line)) {
                // Sub-Section
                if (line.contains(name)) {
                    found = true;
                    text.append('3');
                }
            } else if(isSection(line)) {
                // Section
                if (line.contains(name)) {
                    found = true;
                    text.append('2');
                }
            } else if((isSection(line) || isSubSection(line)) && found) {
                break;
            } else {
                text.append(" ").append(line);
            }
        }
        return text.toString();
    }

    private boolean isTitle(String line) {
        return line.startsWith("#") && line.charAt(1) != '#';
    }

    private boolean isSection(String line) {
        return line.startsWith("##") && line.charAt(2) != '#';
    }

    private boolean isSubSection(String line) {
        return line.startsWith("###");
    }

    private boolean isChoice(String line) {
        return line.startsWith("[[") && line.endsWith("]]");
    }

    private boolean containsVariable(String s) {
        return s.contains("$$");
    }

    private boolean isComment(String line) {
        return line.startsWith("--");
    }

}
