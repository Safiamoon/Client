package fr.houseofcode.dap.commandLine.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author msa
 *
 */
public class CmdLineLauncher {

    /**
     * @param args
     */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Launch GmailService and CalendarService at the same time but in different cases.
     * @param args is the external parameters
     * @throws IOException  if the sent or received  message's broken
     * @throws GeneralSecurityException in case there's a security failure  
     */

    public static void main(final String[] args) throws IOException, GeneralSecurityException {

        // TODO Auto-generated method stub
        ServerUtil su = new ServerUtil();

        LOG.debug("Début du main avec comme arguments : " + args);

        String nbEmails;

        Integer choixUserEntier = Integer.parseInt(args[0]);
        String choixUserKey = args[1];
        String labels = su.getLabels(choixUserKey);
        nbEmails = su.getNbUnreadEmail(choixUserKey);
        String nextEvent;
        nextEvent = su.nextEvent(choixUserKey);

        switch (choixUserEntier) {

        case 1:
            LOG.debug("Connexion d'utilisateur dans ses labels : ");
            System.out.println(" Here are your Labels : " + "\n" + labels);

            break;

        case 2:
            LOG.debug("Connexion d'utilisateur dans sa boite mail ");
            System.out.println("Vous avez : " + nbEmails + " emails non lus");
            break;

        case 3:
            LOG.debug("Connexion d'utilisateur dans son GoogleCalendar : ");
            System.out.println("Your next event is : " + nextEvent);
            break;

        case 9:
            LOG.debug("Connexion d'utilisateur dans ses labels : ");
            LOG.debug("Connexion d'utilisateur dans sa boite mail: ");
            LOG.debug("Connexion d'utilisateur dans son GoogleCalendar : ");
            System.out.println(" Here are your Labels : " + "\n" + labels);

            System.out.println("Vous avez : " + nbEmails + " emails non lus");

            System.out.println("Your next event is : " + nextEvent);

            break;

        default:
            System.out.println("Pas d'option reconnue");

        }
    }
}