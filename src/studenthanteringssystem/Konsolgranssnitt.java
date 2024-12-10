package studenthanteringssystem;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Konsolgranssnitt {
    private static Konsolgranssnitt instance;
    private boolean studentLoggning;
    private int val;

    public String bokstaver = "^[a-zA-ZåäöÅÄÖ]+$";
    public String siffror = "^[0-9]+$";

    private final StudentHantering studentHantering = StudentHantering.getInstance();
    private final Filhantering filhantering = Filhantering.getInstance();

    private final Scanner scan = new Scanner(System.in);

    private Konsolgranssnitt() {
        setStudentLoggning(true);
        viLoggarStudenter();
    }

    public static Konsolgranssnitt getInstance() {
        if (instance == null) {
            instance = new Konsolgranssnitt();
        }
        return instance;
    }

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

                studentHantering.laggTillStudent(namn, betyg, student_ID);
                System.out.println("Student tillagd.");

                boolean validChoice = false;
                while (!validChoice) {
                    System.out.println("Vill du lägga till fler studenter?");
                    System.out.println("1. Ja");
                    System.out.println("2. Nej");

                    scanningInt();
                    int choice = getVal();

                    if (choice == 1) {
                        validChoice = true;

                    } else if (choice == 2) {
                        validChoice = true;
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

    private void sokStudentUI() {
        System.out.println("Ange studentens ID för att söka:");
        int studentID = scan.nextInt();
        scan.nextLine();

        Student student = studentHantering.sokStudentID(studentID);
        if (student != null) {
            System.out.println("Student: " + student);
        } else {
            System.out.println("Ingen student hittades med ID: " + studentID);
        }
        System.out.println();
    }

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

    private void sparaStudenterTillFil() {
        System.out.println("Sparar studenter till fil...");
        filhantering.sparaStudenterTillFil(studentHantering.getSparadeStudenter());
    }

    private void visaStudenterFranFil() {
        System.out.println("Läser in studenter från fil...");
        LinkedHashMap<Integer, Student> loadedStudents = filhantering.lasStudenterFranFil();

        if (loadedStudents != null && !loadedStudents.isEmpty()) {
            System.out.println("Studenter från fil:");
            for (Student student : loadedStudents.values()) {
                System.out.println(student);
            }
        } else {
            System.out.println("Inga studenter laddades från fil.");
        }
    }

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

    private void avslutaProgram() {
        filhantering.raderaFil();
        System.out.println("Avslutar programmet, ha en trevlig dag!");
        setStudentLoggning(false);
    }

    public void closeScanner() {
        scan.close();
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    private void setStudentLoggning(boolean studentLoggning) {
        this.studentLoggning = studentLoggning;
    }

    public boolean isStudentLoggning() {
        return studentLoggning;
    }
}
