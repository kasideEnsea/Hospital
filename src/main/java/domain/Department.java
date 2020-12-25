package domain;

public class Department {
    private int id;
    private String name;
    private int countOfPatients;

    public Department(int id, String name, int countOfPatients) {
        this.id = id;
        this.name = name;
        this.countOfPatients = countOfPatients;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(String name, int countOfPatients) {
        this.name = name;
        this.countOfPatients = countOfPatients;
    }

    public Department(String name) {
        this.name = name;
        this.countOfPatients = 0;
    }

    public void incCount(int increment){
        this.countOfPatients+=increment;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountOfPatients() {
        return countOfPatients;
    }

    public void incCountOfPatients(int numberOfNewPatients) {
        this.countOfPatients += numberOfNewPatients;
    }

    public void setToZeroCountOfPatients() {
        this.countOfPatients = 0;
    }

    public void setCountOfPatients(int count){
        this.countOfPatients = count;
    }
}
