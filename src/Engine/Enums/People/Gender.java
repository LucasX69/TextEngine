package Engine.Enums.People;

public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

}
