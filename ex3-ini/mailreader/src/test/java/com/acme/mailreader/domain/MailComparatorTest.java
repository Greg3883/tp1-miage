package com.acme.mailreader.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import com.acme.mailreader.utils.DateIncorrecteException;

public class MailComparatorTest {
	
	private MailComparator comparator;

	@Before
	public void setUp() throws Exception {
		this.comparator = new MailComparator();
	}

	@Test
	public final void egauxSiDeuxMailsNuls() {
		Mail mail = null;
		Mail AutreMail = null;
		assertThat(comparator.compare(mail, AutreMail), is(0));
	}
	
	@Test
	public final void egauxSiUnDesMailsNuls() {
		Mail mail = new Mail();
		Mail AutreMail = null;
		assertThat(comparator.compare(mail, AutreMail), is(0));
	}
	
	@Test
	public final void premierPetitSiMoinsImportant(){
		Mail mail = new Mail.Builder("test").important(false).build();
		Mail AutreMail = new Mail.Builder("test").important(true).build();
		assertThat(comparator.compare(mail, AutreMail), is(1));
	}
	
	@Test
	public final void premierGrandSiPlusImportant(){
		Mail mail = new Mail.Builder("test").important(true).build();
		Mail AutreMail = new Mail.Builder("test").important(false).build();
		assertThat(comparator.compare(mail, AutreMail), is(-1));
	}
	
	@Test
	public final void egauxSiMemeImportance(){
		Mail mail = new Mail.Builder("test").important(true).build();
		Mail AutreMail = new Mail.Builder("test").important(true).build();
		assertThat(comparator.compare(mail, AutreMail), is(0));
	}
	
	@Test
	public final void premierGrandSiStatutSuperieur(){
		Mail mail = new Mail.Builder("test").statut(Mail.Statut.SENT).build();
		Mail AutreMail = new Mail.Builder("test").statut(Mail.Statut.UNSENT).build();
		assertThat(comparator.compare(mail, AutreMail), is(-1));
	}
	
	@Test
	public final void premierPetitSiStatutInferieur(){
		Mail mail = new Mail.Builder("test").statut(Mail.Statut.UNSENT).build();
		Mail AutreMail = new Mail.Builder("test").statut(Mail.Statut.SENT).build();
		assertThat(comparator.compare(mail, AutreMail), is(1));
	}
	
	@Test
	public final void egauxSiMemeStatut(){
		Mail mail = new Mail.Builder("test").statut(Mail.Statut.SENT).build();
		Mail AutreMail = new Mail.Builder("test").statut(Mail.Statut.SENT).build();
		assertThat(comparator.compare(mail, AutreMail), is(0));
	}
	
	@Test
	public final void premierPlusPetitSiDateSuperieur() throws DateIncorrecteException{
		Mail mail1 = new Mail.Builder("test").date(Instant.parse("2017-04-05T13:33:12Z")).build();
		Mail mail2 = new Mail.Builder("test").date(Instant.parse("2017-04-04T13:33:12Z")).build();
		assertThat(comparator.compare(mail1, mail2), is(1));
	}
	
	@Test
	public final void premierPlusGrandSiDateInferieur() throws DateIncorrecteException {
		Mail mail = new Mail.Builder("test").date(Instant.parse("2017-04-03T13:33:12Z")).build();
		Mail AutreMail = new Mail.Builder("test").date(Instant.parse("2017-04-04T13:33:12Z")).build();
		assertThat(comparator.compare(mail, AutreMail), is(-1));
	}
	
	@Test
	public final void egauxSiMemeDate() throws DateIncorrecteException {
		Mail mail = new Mail.Builder("test").date(Instant.parse("2017-04-04T13:33:12Z")).build();
		Mail AutreMail = new Mail.Builder("test").date(Instant.parse("2017-04-04T13:33:12Z")).build();
		assertThat(comparator.compare(mail, AutreMail), is(0));
	}
	
}
