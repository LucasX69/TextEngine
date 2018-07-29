package Engine.Characters;

import Engine.Enums.Occupations.Job;
import Engine.Enums.People.Gender;
import Engine.Enums.People.Type;

public class Character {

    public String name;
    public int age;
    public Gender gender;
    public String background;
    public String birthPlace;
    public Job job;
    public Type type;

    public Character() {

    }

    // Generic Characters
    public Character(String name, int age, Gender gender, Job job, Type type) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.job = job;
        this.type = type;
        background = "Generic";
        birthPlace = "Generic";
    }

    // Custom character and Player
    public Character(String name, int age, Gender gender, String background, String birthPlace, Job job, Type type) {
        this(name, age, gender, job, type);
        this.background = background;
        this.birthPlace = birthPlace;
    }
}
