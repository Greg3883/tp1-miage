package com.acme.mailreader.domain;

import java.time.Instant;

import com.acme.mailreader.utils.DateIncorrecteException;
import com.acme.mailreader.utils.DateIncorrecteException.ErreurDate;

public class Mail {

	public enum Statut {
		PAS_ENVOYE, ENVOYE, LU
	}

	public static class Builder {
		Mail mailConstruit;

		public Builder(String sujet) {
			mailConstruit = new Mail();
			mailConstruit.setSujet(sujet);
		}

		public Builder statut(Statut statut) {
			mailConstruit.setStatut(statut);
			return this;
		}

		public Builder date(Instant instant) throws DateIncorrecteException {
			mailConstruit.setDate(instant);
			return this;
		}

		public Builder important(boolean important) {
			mailConstruit.setImportant(important);
			return this;
		}

		public Mail build() {
			return mailConstruit;
		}
	}

	private boolean important;
	private String sujet;
	private Instant date;
	private Statut statut;

	private static final Instant MIN_DATE = Instant
			.parse("1970-01-01T00:00:00.00Z");
	private static final Instant MAX_DATE = Instant
			.parse("2100-01-01T00:00:00.00Z");

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public Instant getDate() {
		return date;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setDate(Instant date) throws DateIncorrecteException {
		if (date.isBefore(MIN_DATE)) {
			throw new DateIncorrecteException(ErreurDate.ANTERIEUR_1970);
		} else if (date.isAfter(MAX_DATE)) {
			throw new DateIncorrecteException(ErreurDate.POSTERIEUR_2100);
		}
		this.date = date;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (important ? 1231 : 1237);
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		result = prime * result + ((sujet == null) ? 0 : sujet.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mail other = (Mail) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (important != other.important)
			return false;
		if (statut != other.statut)
			return false;
		if (sujet == null) {
			if (other.sujet != null)
				return false;
		} else if (!sujet.equals(other.sujet))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mail [important=" + important + ", sujet=" + sujet + ", date="
				+ date + "]";
	}

}
