package com.acme.mailreader.commun;

import com.acme.mailreader.infrastructure.InMemoryMailSender;
import com.acme.mailreader.infrastructure.SmtpMailSender;
import com.acme.mailreader.utils.MailSender;
import com.google.inject.AbstractModule;

/**
 * Module de configuration Guice 
 * @author bflorat
 *
 */
public class MailReaderModule extends AbstractModule {

	private boolean production = false;

	public MailReaderModule(boolean production) {
		super();
		this.production = production;
	}


	@Override
	protected void configure() {

		if (production) {
			bind(MailSender.class).to(SmtpMailSender.class);
		} else {
			bind(MailSender.class).to(InMemoryMailSender.class);

		}
	}

}
