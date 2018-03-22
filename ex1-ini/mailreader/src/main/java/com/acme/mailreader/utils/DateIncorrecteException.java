package com.acme.mailreader.utils;

public class DateIncorrecteException extends Exception {

	private static final long serialVersionUID = -212914890553410236L;
	public enum ErreurDate {
		ANTERIEUR_1970("Date antérieure à 1970"), POSTERIEUR_2100(
				"Date postérieure à 2100");

		ErreurDate(String message) {
			this.message = message;
		}

		private String message;

		public String getMessage() {
			return message;
		}
	}

	public DateIncorrecteException(ErreurDate erreur) {
		super(erreur.getMessage());
	}

}
