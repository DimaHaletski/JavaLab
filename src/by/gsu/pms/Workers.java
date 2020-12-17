package by.gsu.pms;

public class Workers {
    private int worker_id;
    private String worker_name;
    private int worker_salary;

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public int getWorker_salary() {
        return worker_salary;
    }

    public void setWorker_salary(int worker_salary) {
        this.worker_salary = worker_salary;
    }

    public Workers(int worker_id, String worker_name, int worker_salary) {
        this.worker_id = worker_id;
        this.worker_name = worker_name;
        this.worker_salary = worker_salary;
    }
}
