package Engine.Characters;

import Engine.Enums.Occupations.Job;
import Engine.Enums.People.Gender;
import Engine.Enums.People.Type;

public class Character {

    private String name;
    private int age;
    private Gender gender;
    private String background;
    private String birthPlace;
    private Job job;
    private Type type;

    Character() {

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

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBackground() {
        return background;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public Job getJob() {
        return job;
    }

    public Type getType() {
        return type;
    }
}
