package banqueServeur;

import java.io.IOException;
import java.io.PrintStream;

import static java.lang.Integer.parseInt;

/**
 * Classe permettant de gérer le choix entre les différents protocoles {@link IProtocole} dans {@link ServeurSpecifique}
 */
public class Contexte {
    public String[] chaine;
    public ServeurTCP monServeur;
    public PrintStream os;

    public Contexte(String[] chaine, ServeurTCP monServeur, PrintStream os) {
        this.chaine = chaine;
        this.monServeur = monServeur;
        this.os = os;
    }

    public IProtocole getProtocole() throws IOException {
        String comType = chaine[0];
        String value = "";
        if(chaine.length > 1){
            value = chaine[1];
        }
        switch (comType){
            case "retrait":
                return new ProtocoleRetrait(parseInt(value), monServeur, os);
            case "depot":
                return new ProtocoleDepot(parseInt(value), monServeur, os);
            case automateClient.Historique.requeteHisto:
                return new ProtocoleRequeteHistorique(monServeur, os);
            default:
                os.println("Erreur de protocole..... \n");
                throw new IOException("Protocole Inexistant");
        }
    }
}
