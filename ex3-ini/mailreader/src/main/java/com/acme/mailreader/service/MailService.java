package com.acme.mailreader.service;

import java.util.ArrayList;
import java.util.List;

import com.acme.mailreader.domain.Mail;
import com.acme.mailreader.utils.MailInvalideException;
import com.acme.mailreader.utils.MailInvalideException.ErreurMail;
import com.acme.mailreader.utils.MailSender;
import com.google.inject.Inject;

public class MailService {
	
	private static final int TAILLE_MAX_SUJET = 20;
	
	//TODO : injecter par constructeur un sender
	private MailSender sender;
	
	@Inject
	public MailService(MailSender sender) {
		super();
		this.sender = sender;
	}


	/**
	 * operation d'envoir de mail
	 * 
	 * @param le
	 *            mail à envoyer
	 * @throws MailInvalideException si le mail n'est pas valide
	 * */
	public void envoyerMail(Mail mail) throws MailInvalideException {
		if (mail.getSujet().length() > TAILLE_MAX_SUJET){
			throw new MailInvalideException(ErreurMail.SUJET_TROP_LONG);
		}
		//...
		sender.envoyerMail(mail);

	}

	/**
	 * operation de récupération de tous les mails de la boite
	 * 
	 * @return l'ensemble des mails de la boite ou une liste vide si aucun
	 */
	public List<Mail> getTousLesMails() {
		//TODO
		return new ArrayList<Mail>();
	}

}
