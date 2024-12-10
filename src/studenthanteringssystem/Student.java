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

        public String getNamn() {
            return namn;
        }

        public String getBetyg() {
            return betyg;
        }

        public int getID() {
            return ID;
        }

        @Override
        public String toString() {
            return "ID: " + ID + ", Namn: " + namn + ", Betyg: " + betyg;
        }

}
