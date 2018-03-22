package com.acme.mailreader.commun;

import java.util.Comparator;

import com.acme.mailreader.domain.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on dÃ©sire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur nÃ©gative
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
		if (differenceImportance(mail, autreMail)) {
			return triMailImportance(mail, autreMail);
		}
		if (differenceStatut(mail, autreMail)) {
			return triStatut(mail, autreMail);
		}
		if (mail.getSujet() != autreMail.getSujet()) {
			return autreMail.getSujet().compareTo(mail.getSujet());
		}
		return autreMail.getDate().compareTo(mail.getDate());
	}

	private boolean mailVide(Mail mail) {
		return mail == null;
	}
	
	private boolean differenceImportance(Mail mail, Mail autreMail) {
		return mail.isImportant() != autreMail.isImportant();
	}
	
	private boolean differenceStatut(Mail mail, Mail autreMail) {
		return mail.getStatut() != autreMail.getStatut();
	}
	
	private int triMailImportance(Mail mail, Mail autreMail) {
		if (mail.isImportant() && !autreMail.isImportant()) {
			return PREMIERINFERIEUR;
		} else {
			return PREMIERSUPERIEUR;
		}
	}
	
	private int triStatut(Mail mail, Mail autreMail) {
		int comp = mail.getStatut().ordinal()
				- autreMail.getStatut().ordinal();
		return comp > 0 ? PREMIERINFERIEUR : PREMIERSUPERIEUR;
	}

	

}
