package studenthanteringssystem;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Konsolgranssnitt {
    private static Konsolgranssnitt instance;
    private boolean studentLoggning;
    private int val;

    // Vi skapar instans av Regex som vi använder för att garantera att användaren använder sig av rätt bokstäver/siffror
    public String bokstaver = "^[a-zA-ZåäöÅÄÖ]+$";
    public String siffror = "^[0-9]+$";

    // Vi skapar objekt av våra klasser, vi använder getInstance() då vi skapat dem enligt Singelton-strukturen
    private final StudentHantering studentHantering = StudentHantering.getInstance();
    private final Filhantering filhantering = Filhantering.getInstance();

    private final Scanner scan = new Scanner(System.in);

    private Konsolgranssnitt() {
        // Vi anropar våra startmetoder i konstruktorn
        setStudentLoggning(true);
        viLoggarStudenter();
    }

    // Vi skapar klassen enligt Singelton-strukturen
    public static Konsolgranssnitt getInstance() {
        if (instance == null) {
            instance = new Konsolgranssnitt();
        }
        return instance;
    }

    // Startmenyn som anropar två metoder
    public void viLoggarStudenter() {
        while (isStudentLoggning()) {
            System.out.println("Hej, välkommen till Emiles lilla skola!");
            System.out.println("Var vänlig välj ett av alternativen");
            System.out.println();
            System.out.println("1. Lägg till nya studentposter via menyn");
            System.out.println("2. Sök efter en student via deras ID");
            System.out.println("3. Visa alla sparade studenter");
            System.out.println("4. Godkänn och spara studenter till fil");
            System.out.println("5. Läs sparade studenter från fil");
            System.out.println("6. Avsluta programmet");

            scanningInt();
            inmatningsAlternativ();
        }
    }

    // Metod med switch-case som anropar metoder beroende på inmatat tal
    private void inmatningsAlternativ() {
        switch (getVal()) {
            case 1:
                laggTillStudentUI();
                break;
            case 2:
                sokStudentUI();
                break;
            case 3:
                visaSparadeStudenter();
                break;
            case 4:
                sparaStudenterTillFil();
                break;
            case 5:
                visaStudenterFranFil();
                break;
            case 6:
                avslutaProgram();
                break;
            default:
                System.out.println("Ogiltigt val, försök igen.");
                break;
        }
    }

    // Metod som promptar användaren att skriva in studentens namn, betyg och ID-nummer. Vi har även lite kontroller som
    // ser till att rätt bokstäver/siffror används på rätt plats och att första bokstaven börjar med stor bokstav
    private void laggTillStudentUI() {
        boolean laggTillFlerStudenter = true;

        while (laggTillFlerStudenter) {
            try {
                String namn = "";
                while (!namn.matches(bokstaver)) {
                    System.out.println("Vad har studenten för namn? (Endast bokstäver tillåtna)");
                    namn = scan.nextLine().trim();

                    if (!namn.matches(bokstaver)) {
                        System.out.println("Ogiltigt namn. Endast bokstäver är tillåtna. Försök igen.");
                    }
                }

                namn = namn.substring(0, 1).toUpperCase() + namn.substring(1).toLowerCase();

                String betyg = "";
                while (true) {
                    System.out.println("Vilket betyg har studenten? (Endast en bokstav A till F tillåten, både stora och små bokstäver)");
                    betyg = scan.nextLine().trim();

                    if (betyg.matches("^[a-fA-F]$")) {
                        betyg = betyg.toUpperCase();
                        break;
                    } else {
                        System.out.println("Ogiltigt betyg. Endast bokstäver A till F tillåtet. Försök igen.");
                    }
                }

                String idInput = "";
                while (!idInput.matches(siffror)) {
                    System.out.println("Vad är studentens ID? (Endast siffror tillåtna)");
                    idInput = scan.nextLine().trim();

                    if (!idInput.matches(siffror)) {
                        System.out.println("Ogiltigt ID. Endast siffror är tillåtna. Försök igen.");
                    }
                }

                int student_ID = Integer.parseInt(idInput);

                // Vi använder oss av "laggTillStudent" från klassen "StudentHantering"
                studentHantering.laggTillStudent(namn, betyg, student_ID);
                System.out.println("Student tillagd.");

                boolean godkantVal = false;

                while (!godkantVal) {
                    System.out.println("Vill du lägga till fler studenter?");
                    System.out.println("1. Ja");
                    System.out.println("2. Nej");

                    scanningInt();
                    int val1 = getVal();

                    if (val1 == 1) {
                        godkantVal = true;

                    } else if (val1 == 2) {
                        godkantVal = true;
                        laggTillFlerStudenter = false;

                    } else {
                        System.out.println("Ogiltigt val. Välj 1 eller 2.");
                    }
                }

            } catch (Exception e) {
                System.out.println("Ett oväntat fel uppstod. Försök igen.");
                scan.nextLine();
            }
        }
    }

    // Metod som låter oss söka efter en student genom att mata in studentens ID-nummer
    private void sokStudentUI() {
        System.out.println("Ange studentens ID för att söka:");

        int studentID = scan.nextInt();
        scan.nextLine();

        // Vi skapar en Studentvariabel som heter student och tilldelar den Studentobjektet som returneras av
        // sokStudentID(studentID). Hittas en student med ID:et tilldelas det till studentvariabeln
        Student student = studentHantering.sokStudentID(studentID);
        if (student != null) {
            System.out.println("Student: " + student);
        } else {
            System.out.println("Ingen student hittades med ID: " + studentID);
        }
        System.out.println();
    }

    // Metod som visar sparade studenter i vår StudentHanteringsklass "getSparadeStudenter()"
    private void visaSparadeStudenter() {
        LinkedHashMap<Integer, Student> students = studentHantering.getSparadeStudenter();

        if (students.isEmpty()) {
            System.out.println("Inga studenter är sparade ännu.");
        } else {
            System.out.println("Sparade studenter:");
            for (Student student : students.values()) {
                System.out.println(student);
            }
        }
        System.out.println();
    }

    // Metod som sparar studenter till en text-fil genom att anropa metoden "sparaStudenterTillFil()" i klassen
    // Filhantering. Vi får studenterna genom att skicka in metoden getSparadeStudenter() från klassen StudentHantering
    private void sparaStudenterTillFil() {
        System.out.println("Sparar studenter till fil...");
        filhantering.sparaStudenterTillFil(studentHantering.getSparadeStudenter());
    }

    // Metoden skapar en LinkedHashMap dit vi sparar de sparade studenterna från filen, sedan går vi genom hashmapen
    // med en enhanced for-loop och skriver ut alla sparade studenter
    private void visaStudenterFranFil() {
        System.out.println("Läser in studenter från fil...");
        LinkedHashMap<Integer, Student> sparadeStudenter = filhantering.lasStudenterFranFil();

        if (sparadeStudenter != null && !sparadeStudenter.isEmpty()) {
            System.out.println("Studenter från fil:");
            for (Student student : sparadeStudenter.values()) {
                System.out.println(student);
            }
        } else {
            System.out.println("Inga studenter laddades från fil.");
        }
    }

    // Metod som läser in inmatad siffra och ansätter "val" i metod. Metoden ser också till att endast siffror kan
    // kan matas in
    private void scanningInt (){
        while (true) {
            if (scan.hasNextInt()) {
                val = scan.nextInt();
                scan.nextLine();
                setVal(val);
                break;

            } else {
                System.out.println("Var vänlig och välj ett tal mellan alternativen");
                scan.next();
            }
        }
    }

    // Metod som avslutar programmet och som tar bort studenttextfilen
    private void avslutaProgram() {
        filhantering.raderaFil();
        System.out.println("Avslutar programmet, ha en trevlig dag!");
        setStudentLoggning(false);
    }

    // Metod som stänger av scanner
    public void closeScanner() {
        scan.close();
    }

    // Metod som returnerar siffran sparad i "val"
    public int getVal() {
        return val;
    }

    // Metod som spara siffra till "val"
    public void setVal(int val) {
        this.val = val;
    }

    // Metod som sätter värdet true/false till vår boolean studentLoggning
    private void setStudentLoggning(boolean studentLoggning) {
        this.studentLoggning = studentLoggning;
    }

    // Metod som returnerar om studentLoggning är true eller false
    public boolean isStudentLoggning() {
        return studentLoggning;
    }
}
