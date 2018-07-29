package Engine.Enums.Occupations;

public enum JobType {

    // TODO add more

    POLICE("Police"),
    HOSPITAL("Hospital"),
    UNEMPLOYED("Unemployed");

    private String name;

    JobType(String name) {
        this.name = name;
    }

}
