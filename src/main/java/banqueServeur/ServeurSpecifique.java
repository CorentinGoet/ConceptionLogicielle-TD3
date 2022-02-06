package banqueServeur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import static java.lang.Integer.parseInt;

/**
 * Représente la gestion de la connexion d'un client avec le serveur. Cette
 * gestion repose sur une {@link Socket} et s'effectue dans un {@link Thread}
 * 
 * 
 */
class ServeurSpecifique extends Thread {

	private Socket clientSocket;
	private ServeurTCP monServeur;
	private IProtocole protocoleEchange;
	private Contexte contexte;

	public ServeurSpecifique(Socket uneSocket, ServeurTCP unServeur) {
		super("ServeurThread");
		clientSocket = uneSocket;
		monServeur = unServeur;
	}

	@Override
	public void run() {
		String inputReq;

		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintStream os = new PrintStream(clientSocket.getOutputStream());
			System.out.println("Serveur avec  Client ");

			if ((inputReq = is.readLine()) != null) {
				System.out.println(" Msg 2 Recu " + inputReq);
				String chaines[] = inputReq.split(" ");
				System.out.println(" Ordre Recu " + chaines[0]);
				try {
					contexte = new Contexte(chaines, monServeur, os);
					setProtocoleEchange(contexte.getProtocole());
					protocoleEchange.run();
				}catch (IOException e){
					os.println("Erreur de protocole..... \n");
				}
			}
			clientSocket.close();
			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public IProtocole getProtocoleEchange() {
		return protocoleEchange;
	}

	public void setProtocoleEchange(IProtocole protocoleEchange) {
		this.protocoleEchange = protocoleEchange;
	}


}
