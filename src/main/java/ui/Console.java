package ui;

import domain.Department;
import domain.Gender;
import domain.Patient;
import servises.DepartmentService;
import servises.PatientService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Console {
    private PatientService patientService;
    private DepartmentService departmnentService;
    private Scanner s = new Scanner(System.in);


    public Console(PatientService patientService, DepartmentService departmnentService) {
        this.patientService = patientService;
        this.departmnentService = departmnentService;
    }

    public void start() {
        System.out.println("Добрый день");
        while (true) {
            System.out.println("Нажмите 1 для работы с базой данных пациентов " +
                    "\nНажмите 2 для работы с базой данных отделений");
            String choice = s.nextLine();
            switch (choice) {
                case "1":
                    patient();
                    break;
                case "2":
                    department();
                    break;
                default:
                    System.out.println("Введен неверный символ");
            }

        }
    }

    private void patient() {
        while (true) {
            System.out.println("Нажмите 0 для возвращения на предыдущее меню\n" +
                    "Нажмите 1 для добавления пациента\n" +
                    "Нажмите 2 для добавления пациента в отделение\n" +
                    "Нажмите 3 для удаления пациента из отделелния\n" +
                    "Нажмите 4 для вывода информации о пациенте\n" +
                    "Нажмите 5 для вывода информации обо всех пациентах\n" +
                    "Нажмите 6 для изменения инофрмаци о пациенте\n" +
                    "Нажмите 7 для удаления пациента\n" +
                    "Нажмите 8 для удаления информации обо всех пациентах"
            );
            String choice = s.nextLine();
            switch (choice) {
                case "0":
                    return;
                case "1":
                    System.out.println("Введите имя, дату рождения (дд.мм.гггг), пол (м/ж), название отделения (если пациент уже привязан к отделению)");
                    String q = s.nextLine();
                    addPatient(q);
                    break;
                case "2":
                    System.out.println("Введите id пациента и название отдела");
                    q = s.nextLine();
                    addPatientInDepartmentByDepName(q);
                    break;
                case "3":
                    System.out.println("Введите id пациента");
                    q = s.nextLine();
                    removePatientFromDepartment(q);
                    break;
                case "4":
                    boolean loopContinue = true;
                    while (loopContinue) {
                        System.out.println("Нажмите 0 для возвращения на предыдущее меню\n" +
                                "Нажмите 1 для вывода информации по айди пациента\n" +
                                "Нажмите 2 для вывода информации по имени пациента"
                        );
                        choice = s.nextLine();
                        switch (choice) {
                            case "0":
                                loopContinue = false;
                                break;
                            case "1":
                                System.out.println("Введите id пациента");
                                q = s.nextLine();
                                System.out.println(getPatientById(q));
                                break;
                            case "2":
                                System.out.println("Введите имя пациента");
                                q = s.nextLine();
                                System.out.println(getPatientByName(q));
                                break;
                        }
                    }
                    break;
                case "5":
                    String str = getPatientsInfo();
                    System.out.println(str);
                    break;
                case "6":
                    System.out.println("Введите id пациента, имя пациента, дату рождения (дд.мм.гггг), пол (м/ж), название отдела(если есть)");
                    q = s.nextLine();
                    changePatient(q);
                    break;
                case "7":
                    System.out.println("Введите id пациента");
                    q = s.nextLine();
                    deletePatientById(q);
                    break;
                case "8":
                    deleteAllPatient();
                default:
                    System.out.println("Введен неверный символ");
            }
        }
    }

    private void department() {
        while (true) {
            System.out.println("Нажмите 0 для возвращения на предыдущее меню\n" +
                    "Нажмите 1 для добавления отделения\n" +
                    "Нажмите 3 для получения информации об отделении\n" +
                    "Нажмите 4 для изменения информации об отделении\n" +
                    "Нажмите 5 для удаления информации об отделении\n" +
                    "Нажмите 6 для удаления информации обо всех отделениях\n" +
                    "Нажмите 7 для вывода информации обо всех отделениях"
            );
            String choice = s.nextLine();
            switch (choice) {
                case "0":
                    return;
                case "1":
                    System.out.println("Введите название отделения");
                    String q = s.nextLine();
                    addDepartment(q);
                    break;
                case "3":
                    boolean loopContinue = true;
                    while (loopContinue) {
                        System.out.println("Нажмите 0 для возвращения на предыдущее меню\n" +
                                "Нажмите 1 для вывода информации по айди отделения\n" +
                                "Нажмите 2 для вывода информации по названию отделения"
                        );
                        choice = s.nextLine();
                        switch (choice) {
                            case "0":
                                loopContinue = false;
                                break;
                            case "1":
                                System.out.println("Введите id отделения");
                                q = s.nextLine();
                                System.out.println(getDepartamentInfoById(q));
                                break;
                            case "2":
                                System.out.println("Введите название отделения");
                                q = s.nextLine();
                                System.out.println(getDepartamentInfoByName(q));
                                break;
                        }
                    }
                    break;
                case "4":
                    System.out.println("Введите id и название отделения");
                    q = s.nextLine();
                    changeDepartment(q);
                    break;
                case "5":
                    loopContinue = true;
                    while (loopContinue) {
                        System.out.println("Нажмите 0 для возвращения на предыдущее меню\n" +
                                "Нажмите 1 для удаления информации по айди отделения\n" +
                                "Нажмите 2 для удаления информации по названию отделения"
                        );
                        choice = s.nextLine();
                        switch (choice) {
                            case "0":
                                loopContinue = false;
                                break;
                            case "1":
                                System.out.println("Введите id отделения");
                                q = s.nextLine();
                                deleteDepartmentById(q);
                                break;
                            case "2":
                                System.out.println("Введите название отделения");
                                q = s.nextLine();
                                deleteDepartmentByName(q);
                                break;
                        }
                    }
                    break;
                case "6":
                    deleteAllDepartments();
                    break;
                case "7":
                    System.out.println(getDepartmentInfo());
                    break;
                default:
                    System.out.println("Неверный символ");
                    break;
            }
        }
    }

    private void addDepartment(String query) {
        departmnentService.save(new Department(query));

    }

    private String getPatientByName(String q) {
        return patientService.getByName(q).toString();
    }

    private String getDepartmentInfo() {
        List<Department> dep = departmnentService.getAll();
        String result = "";
        for (Department d: dep
             ) {
            result+=d.toString();
        }
        return result;
    }

    private void addPatient(String query) {
        String[] dataMembers = query.split(" ");
        if (dataMembers.length < 5) {
            System.err.println("Недостаточно аргументов для добавления пациента");
            return;
        }
        String name = dataMembers[0] + " " + dataMembers[1] + " " + dataMembers[2];
        Gender gender;
        switch (dataMembers[4]) {
            case ("м"):
                gender = Gender.male;
                break;
            case ("ж"):
                gender = Gender.female;
                break;
            default:
                System.err.println("Неправильный пол пациента");
                return;
        }
        String[] birthdayString = dataMembers[3].split("\\.");
        LocalDate birthday;
        if (birthdayString.length < 3) {
            System.err.println("Неправильный формат даты рождения");
            return;
        }
        try {
            birthday = LocalDate.of(Integer.parseInt(birthdayString[2]), Integer.parseInt(birthdayString[1]), Integer.parseInt(birthdayString[0]));
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат даты рождения");
            return;
        }
        if (dataMembers.length > 5) {
            String departmentName = dataMembers[5];
            patientService.save(new Patient(name, Timestamp.valueOf(birthday.atTime(0, 0)), gender, departmentName));
        } else {
            patientService.save(new Patient(name, Timestamp.valueOf(birthday.atTime(0, 0)), gender));
        }
    }

    private void deleteDepartmentByName(String query) {
        departmnentService.deleteByName(query);
    }

    private void deleteDepartmentById(String query) {
        try {
            int departmentId = Integer.parseInt(query);
            departmnentService.delete(departmentId);
        } catch (NumberFormatException e) {
            System.err.println("ID отделения должно быть числом");
        }
    }

    private void deleteAllPatient() {
        patientService.deleteAll();
    }

    private void deleteAllDepartments() {
        departmnentService.deleteAllDepartments();
    }

    private void deletePatientById(String query) {
        try {
            int patientId = Integer.parseInt(query);
            patientService.delete(patientId);
        } catch (NumberFormatException e) {
            System.err.println("ID пациента должно быть числом");
        }
    }

    private void changeDepartment(String query) {
        String[] dataMembers = query.split(" ");
        if (dataMembers.length < 2) {
            System.err.println("Недостаточно аргументов для изменения отделения");
            return;
        }
        String name = dataMembers[1];
        try {
            int id = Integer.parseInt(dataMembers[0]);
            departmnentService.update(new Department(id, name));
        } catch (NumberFormatException e) {
            System.err.println("Количество пациентов должно быть числом");
        }

    }

    private void changePatient(String query) {
        String[] dataMembers = query.split(" ");
        int id;
        if (dataMembers.length < 6) {
            System.err.println("Недостаточно аргументов для изменения пациента");
            return;
        }
        try {
            id = Integer.parseInt(dataMembers[0]);
        } catch (NumberFormatException e) {
            System.err.println("Id пациента должен быть числом");
            return;
        }

        String name = dataMembers[1] + " " + dataMembers[2] + " " + dataMembers[3];

        String[] birthdayString = dataMembers[4].split("\\.");
        LocalDate birthday;
        if (birthdayString.length < 3) {
            System.err.println("Неправильный формат даты рождения");
            return;
        }
        try {
            birthday = LocalDate.of(Integer.parseInt(birthdayString[2]), Integer.parseInt(birthdayString[1]), Integer.parseInt(birthdayString[0]));
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат даты рождения");
            return;
        }

        Gender gender;
        switch (dataMembers[5]) {
            case ("м"):
                gender = Gender.male;
                break;
            case ("ж"):
                gender = Gender.female;
                break;
            default:
                System.err.println("Неправильный пол пациента");
                return;
        }

        if (dataMembers.length > 6) {
            String departmentName = dataMembers[6];
            patientService.update(new Patient(id, name, Timestamp.valueOf(birthday.atTime(0, 0)), gender, departmentName));
        } else {
            patientService.update(new Patient(id, name, Timestamp.valueOf(birthday.atTime(0, 0)), gender, null));
        }


    }

    private String getDepartamentInfoById(String query) {
        try {
            int departmentId = Integer.parseInt(query);
            return departmnentService.get(departmentId).toString();
        } catch (NumberFormatException e) {
            System.err.println("ID департамента должно быть числом");
            return null;
        }
    }

    private String getDepartamentInfoByName(String query) {
        return departmnentService.getByName(query).toString();
    }

    private String getPatientsInfo() {
        List<Patient> patients = patientService.getAll();
        String result = "";
        for (Patient d: patients
        ) {
            result+=d.toString();
        }
        return result;
    }

    private String getPatientById(String query) {
        try {
            int patientId = Integer.parseInt(query);
            return patientService.get(patientId).toString();
        } catch (NumberFormatException e) {
            System.err.println("ID пациента должно быть числом");
            return null;
        }
    }

    private void addPatientInDepartmentByDepName(String query) {
        String[] dataMembers = query.split(" ");
        if (dataMembers.length < 2) {
            System.err.println("Недостаточно аргументов для добавления пациента в отделение");
            return;
        }
        try {
            String departmentName = dataMembers[1];
            patientService.addPatientToDep(dataMembers[0], departmentName);
        } catch (NumberFormatException e) {
            System.err.println("ID должно быть числом");
        }
    }

    private void removePatientFromDepartment(String query) {
        try {
            patientService.removePatientFromDep(query);
        } catch (NumberFormatException e) {
            System.err.println("ID должно быть числом");
        }
    }
}
