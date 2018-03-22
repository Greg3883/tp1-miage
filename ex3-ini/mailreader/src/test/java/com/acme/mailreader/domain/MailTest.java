package com.acme.mailreader.domain;

import java.time.Instant;

import org.junit.Ignore;
import org.junit.Test;

import com.acme.mailreader.utils.DateIncorrecteException;

public class MailTest {
	

	@Ignore
	@Test(expected=DateIncorrecteException.class)
	public final void erreurSiDateAvant1979() throws DateIncorrecteException {
		Mail mail = new Mail.Builder("test").date(Instant.parse("1900-04-05T13:33:12Z")).build();
	}

}
