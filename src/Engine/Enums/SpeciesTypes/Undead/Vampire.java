package Engine.Enums.SpeciesTypes.Undead;

public enum Vampire {

    VAMPIRE("Vampire", false, true);

    private String name;
    private boolean ages;
    private boolean intelligent;

    Vampire(String name, boolean ages, boolean intelligent) {
        this.name = name;
        this.ages = ages;
        this.intelligent = intelligent;
    }

}
