package banqueServeur;

import java.io.PrintStream;

/**
 * Protocole de requête de l'historique des transactions utilisé dans {@link ServeurSpecifique}
 */
public class ProtocoleRequeteHistorique implements IProtocole{
    private ServeurTCP monServeur;
    private PrintStream os;

    public ProtocoleRequeteHistorique(ServeurTCP monServeur, PrintStream os) {
        this.monServeur = monServeur;
        this.os = os;
    }

    @Override
    public void run() {
        String outputString = "Liste des operations :\n";
        for (String h : ((IBanque) monServeur.getObjet()).getHistoriqueOperations()) {
            outputString = outputString + h + "\n";
        }
        System.out.println(" Liste a envoyer : \n " + outputString);
        os.println(outputString);
        os.flush();
    }
}
