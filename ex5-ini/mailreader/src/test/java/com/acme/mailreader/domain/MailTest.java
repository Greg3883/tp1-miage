package com.acme.mailreader.domain;

import java.time.Instant;

import org.junit.Test;

import com.acme.mailreader.utils.DateIncorrecteException;

public class MailTest {
	
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1979() throws DateIncorrecteException {
		new Mail.Builder("sujet").date(Instant.parse("1968-12-03T10:15:30.00Z")).build();
				
	}

}
