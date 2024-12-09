package studenthanteringssystem;

import java.io.*;
import java.util.Scanner;
import java.io.File;

public class Filhantering {

    public Filhantering(){
        skapaFil();
        lasaFil();
        //raderaFil();
    }

    public void skapaFil() {
        try {
            File studenter = new File("Studenter.txt");
            if (!studenter.exists()) {
                if (studenter.createNewFile()) {
                    System.out.println("Fil skapad: " + studenter.getName());
                } else {
                    System.out.println("Filen kunde inte skapas.");
                }
            } else {
                System.out.println("Filen finns redan.");
            }

            FileWriter skrivare = new FileWriter(studenter, false);
            BufferedWriter buffer = new BufferedWriter(skrivare);
            buffer.write("Hej dette är en testfil");
            buffer.close();

        } catch (IOException e) {
            System.out.println("Ett fel uppstod vid skapandet eller skrivande till fil.");
            e.printStackTrace();
        }
    }

    public void lasaFil() {
        try {
            File studenter = new File("Studenter.txt");
            if (studenter.exists()) {
                BufferedReader minBufferLasare = new BufferedReader(new FileReader(studenter));
                String rad;
                while ((rad = minBufferLasare.readLine()) != null) {
                    System.out.println(rad);
                }
                minBufferLasare.close();
            } else {
                System.out.println("Filen finns inte.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ett fel uppstod vid läsandet av fil.");
            e.printStackTrace();
        }
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


