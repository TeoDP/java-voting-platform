package Tema1;

public class Vot {
    String numeCircumscriptie;
    String CNPVotant;
    String CNPCandidat;

    // constructor care doar seteaza variabilele votului
    public Vot(String numeCircumscriptie, String CNPVotant, String CNPCandidat) {
        this.numeCircumscriptie = numeCircumscriptie;
        this.CNPVotant = CNPVotant;
        this.CNPCandidat = CNPCandidat;
        return;
    }
}
