package Tema1;

public class Frauda {
    private String CNP;

    public Frauda() {}
    public Frauda(String CNP) {
        this.CNP = CNP;
        System.out.println("FRAUDa: Votantul cu CNP-ul " + CNP + " a incercat sa comita o frauda. Votul a fost anulat");
        System.out.println("FRAUDA: Votantul cu CNP-ul " + CNP + " a incercat sa comita o frauda. Votul a fost anulat");
    }
}
