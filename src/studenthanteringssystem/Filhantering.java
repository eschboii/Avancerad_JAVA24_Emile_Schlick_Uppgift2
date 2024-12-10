package studenthanteringssystem;

import java.io.*;
import java.io.File;
import java.util.LinkedHashMap;

public class Filhantering {

    private static Filhantering instance;
    private final String filSokvag = "Studenter.txt";

    private Filhantering(){
        skapaFil();
    }

    public static Filhantering getInstance() {
        if (instance == null) {
            instance = new Filhantering();
        }
        return instance;
    }

    public void skapaFil() {
        try {
            File studenter = new File(filSokvag);
            if (!studenter.exists()) {
                if (studenter.createNewFile()) {
                    System.out.println("Fil skapad: " + studenter.getName());
                    System.out.println();
                } else {
                    System.out.println("Filen kunde inte skapas.");
                    System.out.println();
                }
            } else {
                System.out.println("Filen finns redan.");
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("Ett fel uppstod vid skapandet av fil.");
            e.printStackTrace();
        }
    }

    public void sparaStudenterTillFil(LinkedHashMap<Integer, Student> studentMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filSokvag, false))) {
            for (Student student : studentMap.values()) {
                writer.write(student.getID() + "," + student.getNamn() + "," + student.getBetyg());
                writer.newLine();
            }

            System.out.println("Studenter sparade till fil.");
            System.out.println();

        } catch (IOException e) {
            System.out.println("Fel vid sparande till fil.");
            System.out.println();

            e.printStackTrace();
        }
    }

    public LinkedHashMap<Integer, Student> lasStudenterFranFil() {
        LinkedHashMap<Integer, Student> studentMap = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filSokvag))) {
            String rad;

            while ((rad = reader.readLine()) != null) {
                String[] data = rad.split(",");

                if (data.length == 3) {
                    int id = Integer.parseInt(data[0]);
                    String namn = data[1];
                    String betyg = data[2];
                    Student student = new Student(namn, betyg, id);
                    studentMap.put(id, student);
                }
            }

            System.out.println("Studenter lästa från fil.");
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas.");
            System.out.println();

        } catch (IOException e) {
            System.out.println("Fel vid läsning av fil.");
            System.out.println();

            e.printStackTrace();
        }

        return studentMap;
    }

    public void raderaFil() {
        File studenter = new File("Studenter.txt");

        if (studenter.delete()) {
            System.out.println("Raderar fil: " + studenter.getName());

        } else {
            System.out.println("Radera fil misslyckades.");
        }
    }
}
