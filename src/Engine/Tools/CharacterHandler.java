package Engine.Tools;

import Engine.Characters.Character;
import Engine.Enums.Occupations.Job;
import Engine.Enums.People.Gender;
import Engine.Enums.People.Type;

public class CharacterHandler {

    private String name;

    CharacterHandler(String name) {
        this.name = name;
    }

    public Character readCharacter(String[] args) {
        Type type = Type.valueOf(args[0].trim().toUpperCase());
        String name = args[0];
        int age = Integer.parseInt(args[0]);
        Gender gender = Gender.valueOf(args[0].trim().toUpperCase());
        Job job = Job.valueOf(args[0].trim().toUpperCase());
        if (type == Type.GENERIC) {
            return new Character(name, age, gender, job, type);
        } else {
            String background = args[0];
            String birthPlace = args[0];
            return new Character(name, age, gender, background, birthPlace, job, type);
        }

    }

    public String[] writeCharacter(Character character) {
        if (character.getType() == Type.GENERIC) {

        } else {

        }
        return null;
    }


}
