package de.zettsystems.feutrainer.values.user;

public enum Role {
	DATA(), ADMIN(), STUDENT();

	public static class Constants {
		public static final String DATA = "ROLE_DATA";
		public static final String ADMIN = "ROLE_ADMIN";
		public static final String STUDENT = "ROLE_STUDENT";
	}
}
