package com.acme.mailreader.utils;

import java.util.Comparator;

import com.acme.mailreader.model.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {
	
	private static final int PREMIERSUPERIEUR = 1;
	private static final int PREMIERINFERIEUR = -1;
	private static final int EGAUX = 0;

	public int compare(Mail mail, Mail autreMail) {
		if (mailVide(mail) && mailVide(autreMail)) {
			return EGAUX;
		}
		if (mail.isImportant() != autreMail.isImportant()) {
			if (mail.isImportant() && !autreMail.isImportant()) {
				return -1;
			} else {
				return 1;
			}
		}
		if (mail.getStatut() != autreMail.getStatut()) {
			int comp = mail.getStatut().ordinal()
					- autreMail.getStatut().ordinal();
			return comp > 0 ? -1 : 1;
		}
		if (mail.getSujet() != autreMail.getSujet()) {
			return autreMail.getSujet().compareTo(mail.getSujet());
		}
		return autreMail.getDate().compareTo(mail.getDate());
	}
	
	private boolean mailVide(Mail mail) {
		return mail == null;
	}
	

}
