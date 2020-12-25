
import servises.DepartmentService;
import servises.PatientService;
import ui.Console;


public class Main {
    public static void main(String[] args) {
        DepartmentService departmnentService = new DepartmentService();
        PatientService patientService = new PatientService();
        Console cosoleApp = new Console(patientService, departmnentService);
        cosoleApp.start();
    }
}
