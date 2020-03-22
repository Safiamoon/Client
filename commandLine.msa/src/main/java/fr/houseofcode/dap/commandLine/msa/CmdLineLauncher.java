package fr.houseofcode.dap.commandLine.msa;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author msa
 */
public class CmdLineLauncher {

	/**
	 * Logger.
	 */
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Launch GmailService and CalendarService at the same time but in different
	 * cases.
	 * 
	 * @param args is the external parameters
	 * @throws IOException              if the sent or received message's broken
	 * @throws GeneralSecurityException in case there's a security failure
	 * @throws URISyntaxException
	 */
	public static void main(final String[] args) throws IOException, GeneralSecurityException, URISyntaxException {
		ServerUtil su = new ServerUtil();
		LOG.debug("Début du main avec comme arguments : " + args + "\n");

		System.out.println("Que voulez-vou faire ? \n");
		System.out.println("1 ==> afficher les labels");
		System.out.println("2 ==> afficher les emails non lus");
		System.out.println("3 ==> afficher le next event");
		System.out.println("4 ==> afficher les labels, les emails non lus et le next event");
		System.out.println("5 ==> ajouter un nouvel utilisateur \n");
		System.out.println("Entrer votre choix :");
		Scanner scanner = new Scanner(System.in);
		Integer choixUserEntier = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Entrer le nom d'utilisateur :");
		String choixUserKey = scanner.nextLine();

		String labels = su.getLabels(choixUserKey);
		String nbEmails = su.getNbUnreadEmail(choixUserKey);
		String nextEvent = su.nextEvent(choixUserKey);
		String addAccount = su.addAccount(choixUserKey);

		LOG.info("Utilisation d'un switch case pour les différents choix possible pour l'utilisateur");
		try {
			switch (choixUserEntier) {
			case 1:
				LOG.debug("Connexion d'utilisateur dans ses labels : ");
				System.out.println("Here are your Labels : " + "\n" + labels);
				break;
			case 2:
				LOG.debug("Connexion d'utilisateur dans sa boite mail ");
				System.out.println("Vous avez : " + nbEmails + " emails non lus");
				break;
			case 3:
				LOG.debug("Connexion d'utilisateur dans son GoogleCalendar : ");
				System.out.println("Your next event is : " + nextEvent);
				break;
			case 4:
				LOG.debug("Connexion d'utilisateur dans ses labels : ");
				LOG.debug("Connexion d'utilisateur dans sa boite mail: ");
				LOG.debug("Connexion d'utilisateur dans son GoogleCalendar : ");
				System.out.println(" Here are your Labels : " + "\n" + labels);
				System.out.println("Vous avez : " + nbEmails + " emails non lus");
				System.out.println("Your next event is : " + nextEvent);
				break;
			case 5:
				LOG.debug("Ajout d'un nouveau utilisateur et connexion au compte google : ");
				System.out.println("Entrer un nouvel utilisateur : ");
				String userToAdd = scanner.nextLine();
				su.addAccount(userToAdd);
				System.out.println("L'utilisateur : " + addAccount + "a été ajouté");
				break;
			default:
				System.out.println("Pas d'option reconnue");
			}
		} catch (URISyntaxException e) {
			System.out.println("Problem with URI syntax");
		}

		scanner.close();
	}
}