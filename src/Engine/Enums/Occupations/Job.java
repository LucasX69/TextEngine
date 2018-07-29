package Engine.Enums.Occupations;

import static Engine.Enums.Occupations.JobType.POLICE;
import static Engine.Enums.Occupations.JobType.UNEMPLOYED;

public enum Job {

    // TODO add more

    PO("Police Officer", 20.0, POLICE),
    JOBLESS("Jobless", 0.0, UNEMPLOYED);

    private String name;
    private double salary; // In hours
    private JobType type;

    Job(String name, Double salary, JobType type) {
        this.name = name;
        this.salary = salary;
        this.type = type;
    }

}
