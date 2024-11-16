package Tema1;

public class Frauda {
    private String idAlegeri;
    private String CNP;
    private String nume;
    private String numeCircumscriptie;

    public String getIdAlegeri() {
        return idAlegeri;
    }

    public Frauda() {}
    public Frauda(Votant votant) {
        this.CNP = votant.getCNP();
        this.nume = votant.getNume();
        this.numeCircumscriptie = votant.numeCircumscriptie;
        this.idAlegeri = votant.idAlegeri;
        System.out.println("FRAUDa: Votantul cu CNP-ul " + CNP + " a incercat sa comita o frauda. Votul a fost anulat");
        System.out.println("FRAUDA: Votantul cu CNP-ul " + CNP + " a incercat sa comita o frauda. Votul a fost anulat");
    }

    public String toString() {
        return "in " + numeCircumscriptie + ": " + CNP + " " + nume;
    }
}
