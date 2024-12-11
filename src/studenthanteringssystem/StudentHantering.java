package studenthanteringssystem;

import java.util.LinkedHashMap;

public class StudentHantering {
    // Vi ser till att klassen använder sig av Singelton-strukturen
    private static StudentHantering instance;
    private LinkedHashMap<Integer, Student> studentMap = new LinkedHashMap<>();

    private StudentHantering() {}

    public static StudentHantering getInstance() {
        if (instance == null) {
            instance = new StudentHantering();
        }
        return instance;
    }

    //Metod som tar in värdet namn, betyg och int och lägger in dessa i vår hashmap och vi skapar ett nytt objekt av
    // Student som vi får värdet namn, betyg och ID och som sedan sparas i vår hashmap.
    public void laggTillStudent(String namn, String betyg, int ID) {
        studentMap.put(ID, new Student(namn, betyg, ID));
    }

    // Metod som returnear vår linkedHashMap
    public LinkedHashMap<Integer, Student> getSparadeStudenter() {
        return studentMap;
    }

    // Metod som returnerar ID i vår sparade hashmap
    public Student sokStudentID(int id) {
        return studentMap.get(id);
    }
}
