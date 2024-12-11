package studenthanteringssystem;

public class Student {
        private String namn;
        private String betyg;
        private int ID;

        public Student(String namn, String betyg, int ID) {
            this.namn = namn;
            this.betyg = betyg;
            this.ID = ID;
        }

        // Returnerar namn
        public String getNamn() {
            return namn;
        }
        // Returnerar betyg
        public String getBetyg() {
            return betyg;
        }
        // Returnerar id
        public int getID() {
            return ID;
        }

        // Vi skapar en override som formaterar om och skriver över toString så när vi skriver ut "Student" så kommer
        // vi automatiskt få denna formateringen ex Student student = new Student("Emile", "A", 1337); printar vi detta
        // får vi ID; 1337, Namn: Emile, Betyg: A
        @Override
        public String toString() {
            return "ID: " + ID + ", Namn: " + namn + ", Betyg: " + betyg;
        }

}
