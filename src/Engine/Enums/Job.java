package Engine.Enums;

import static Engine.Enums.JobType.POLICE;

public enum Job {

    // TODO add more

    PO("Police Officer", 20.0, POLICE);

    private String name;
    private double salary; // In hours
    private JobType type;

    Job(String name, Double salary, JobType type) {
        this.name = name;
        this.salary = salary;
        this.type = type;
    }

}
