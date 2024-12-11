package studenthanteringssystem;

import java.io.*;
import java.io.File;
import java.util.LinkedHashMap;

public class Filhantering {
    // Vi strukturear efter Singeltonmetoden
    // Vi skapar en string med filsökvägen för att garantera att det blir rätt varje gång vi refererar
    private static Filhantering instance;
    private final String filSokvag = "Studenter.txt";

    // Konstruktorn skapar fil så fort vi anropar på klassen Filhantering
    private Filhantering(){
        skapaFil();
    }

    public static Filhantering getInstance() {
        if (instance == null) {
            instance = new Filhantering();
        }
        return instance;
    }

    // Vi försöker skapa en fil "Studenter.txt"
    public void skapaFil() {
        try {
            // Vi skapar ett objekt "File" som representerar vår fil
            File studenter = new File(filSokvag);

            // Vi kontrollerar om filen redan finns på platsen, om inte så som matar vi ut att vi skapar filen
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

            // Sker oväntade fel så har vi en catch som tar emot det och matar ut text som beskriver att något blivit
            // fel
        } catch (IOException e) {
            System.out.println("Ett fel uppstod vid skapandet av fil.");
            e.printStackTrace();
        }
    }

    // Denna metoden sparar studenterna vi har i vår LinkedHashmap till vår fil
    public void sparaStudenterTillFil(LinkedHashMap<Integer, Student> studentMap) {
        // Vi försöker att öppna filen på rätt filsökväg och använder bool för att beskriva att vi skriver över filen
        // om filen redan finns.

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filSokvag, false))) {
            // Vi iterar genom vår hashmap och sparar dem i filen med ett komma som separerar de olika posterna

            for (Student student : studentMap.values()) {
                writer.write(student.getID() + "," + student.getNamn() + "," + student.getBetyg());
                // Säger att skrivaren går över till nästa rad
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

    // Vi skapar en tom LinkedHashmap som kommer att spara alla studenter som läses från filen
    public LinkedHashMap<Integer, Student> lasStudenterFranFil() {
        LinkedHashMap<Integer, Student> studentMap = new LinkedHashMap<>();

        // Vi öppnar filen och löser den med hjälp av "BufferedReader"
        try (BufferedReader reader = new BufferedReader(new FileReader(filSokvag))) {
            String rad;

            // Vår string "rad" sparar varje rad av text där vår while går genom dokumentet rad för rad och fortsätter
            // tills vi har en tom rad
            while ((rad = reader.readLine()) != null) {

                // Varje rad antas vara uppdelat med ett komma
                String[] data = rad.split(",");

                // Kontrollerar att raderna är korrekt formaterade med våra tre värden, ID, namn och betyg
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

            // Vi fångar olika problem, t.ex att vi inte hittar filen och att vi inte kan läsa från filen
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas.");
            System.out.println();

        } catch (IOException e) {
            System.out.println("Fel vid läsning av fil.");
            System.out.println();

            e.printStackTrace();
        }

        // Vi returnerar hashmapen med alla studenter
        return studentMap;
    }

    // Metod som raderar filen
    public void raderaFil() {
        File studenter = new File(filSokvag);

        if (studenter.delete()) {
            System.out.println("Raderar fil: " + studenter.getName());

        } else {
            System.out.println("Radera fil misslyckades.");
        }
    }
}
