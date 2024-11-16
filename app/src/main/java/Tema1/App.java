package Tema1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class App {
    private Scanner scan;
    public ArrayList<Comanda> comenzi = new ArrayList<>();
    static ArrayList<Alegeri> oldAlegeri = new ArrayList<>();
//    static ArrayList<Circumscriptie> circumscriptii = new ArrayList<>();
    static ArrayList<Frauda> fraude = new ArrayList<>();

//    static Scanner scan;
//    static int status;

    public App(){}
    public App(InputStream input) {
        this.scan = new Scanner(input);
    }

    public void run() {
        // Implementați aici cerințele din enunț
        // Pentru citirea datelor de la tastatura se folosește câmpul scanner.

        String temp = scan.nextLine();

        while (!temp.isBlank()) {
            int iD = Integer.parseInt(temp);
            if (iD == 18 || iD == 17) {
                oldAlegeri.clear();
                fraude.clear();
//                circumscriptii.clear();
                return;
            }
            temp = scan.nextLine();
            Comanda aux = null;
            switch (iD) {
                case 0:
                    aux = new CreareAlegeri(temp);
                    comenzi.add(aux);
                    break;

                case 1:
                    aux = new StartAlegeri(temp);

                    break;

                case 2:
                    aux = new AdaugareCircumscriptie(temp);
                    break;

                case 3:
                    aux = new EliminareCircumscriptie(temp);
                    break;

                case 4:
                    aux = new AdaugareCandidat(temp);
                    break;

                case 5:
                    aux = new EliminareCandidat(temp);
                    break;

                case 6:
                    aux = new AdaugareVotant(temp);
                    break;

                case 7:
                    aux = new ListareCandidati(temp);
                    break;

                case 8:
                    aux = new ListareVotanti(temp);
                    break;

                case 9:
                    aux = new InregistrareVot(temp);
                    break;

                case 10:
                    aux = new OprireAlegeri(temp);
                    break;
            }
            temp = scan.nextLine();

        }
        return;


    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}