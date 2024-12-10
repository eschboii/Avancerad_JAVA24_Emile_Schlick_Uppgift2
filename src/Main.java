import studenthanteringssystem.Filhantering;
import studenthanteringssystem.Konsolgranssnitt;

public class Main {
    public static void main(String[] args) {
        Filhantering filhantering = Filhantering.getInstance();
        filhantering.skapaFil();

        Konsolgranssnitt konsolgranssnitt = Konsolgranssnitt.getInstance();
        konsolgranssnitt.viLoggarStudenter();
        konsolgranssnitt.closeScanner();
    }
}