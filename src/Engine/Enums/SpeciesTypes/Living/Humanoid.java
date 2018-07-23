package Engine.Enums.SpeciesTypes.Living;

public enum Humanoid {

    HUMAN("Human", 80),
    ELF("Elf", 300);

    private String name;
    private int averageAge;

    Humanoid(String name, int averageAge) {
        this.name = name;
        this.averageAge = averageAge;
    }

}
