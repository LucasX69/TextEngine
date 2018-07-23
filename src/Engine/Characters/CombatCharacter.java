package Engine.Characters;

import Engine.Enums.Occupations.Job;
import Engine.Enums.People.Gender;
import Engine.Enums.People.Type;

public class CombatCharacter extends Character {

    double cash;

    private int experiencePoints;
    private int level;

    private double health;
    private double maxHealth;

    private double stamina;
    private double maxStamina;

    private double mana;
    private double maxMana;

    private boolean isPlayer;

    public CombatCharacter(String name, int age, Gender gender, String background, String birthPlace, Job job, Type type, boolean isPlayer) {
        super(name, age, gender, background, birthPlace, job, type);
        this.isPlayer = isPlayer;
    }

    public CombatCharacter(String name, int age, Gender gender, String background, String birthPlace, Job job, Type type, boolean isPlayer,
                           int experiencePoints, double maxHealth, double maxStamina, double maxMana) {
        this(name, age, gender, background, birthPlace, job, type, isPlayer);
        this.experiencePoints = experiencePoints;
        this.maxHealth = maxHealth;
        this.maxMana = maxMana;
        this.maxStamina = maxStamina;
    }

    public CombatCharacter(String name, int age, Gender gender, String background, String birthPlace, Job job, Type type, boolean isPlayer,
                           int experiencePoints, double maxHealth, double maxStamina, double maxMana, double health, double mana,
                           double stamina, double cash) {
        this(name, age, gender, background, birthPlace, job, type, isPlayer, experiencePoints, maxHealth, maxMana, maxStamina);
        this.health = health;
        this.mana = mana;
        this.stamina = stamina;
        this.cash = cash;
    }

    public int getLevel() {
        // TODO add a system for Level based on experiencePoints
        return level;
    }
}
