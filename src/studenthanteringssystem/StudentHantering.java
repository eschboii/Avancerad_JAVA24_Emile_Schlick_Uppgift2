package studenthanteringssystem;

import java.util.LinkedHashMap;


public class StudentHantering {
    private static StudentHantering instance;
    private LinkedHashMap<Integer, Student> studentMap = new LinkedHashMap<>();

    private StudentHantering() {}

    public static StudentHantering getInstance() {
        if (instance == null) {
            instance = new StudentHantering();
        }
        return instance;
    }

    public void laggTillStudent(String namn, String betyg, int ID) {
        studentMap.put(ID, new Student(namn, betyg, ID));
    }

    public LinkedHashMap<Integer, Student> getSparadeStudenter() {
        return studentMap;
    }

    public Student sokStudentID(int id) {
        return studentMap.get(id);
    }
}
