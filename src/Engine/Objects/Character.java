package Engine.Objects;

import Engine.Enums.Gender;
import Engine.Enums.Job;

public class Character {

    private String name;
    private int age;
    private Gender gender;
    private String background;
    private String birthPlace;
    private Job job;

    Character(String name, int age, Gender gender, String background, String birthPlace, Job job) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.background = background;
        this.birthPlace = birthPlace;
        this.job = job;
    }


}
