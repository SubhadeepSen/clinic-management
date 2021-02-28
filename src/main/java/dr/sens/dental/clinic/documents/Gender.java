package dr.sens.dental.clinic.documents;

import dr.sens.dental.clinic.exceptions.UnsupportedGenderException;

public enum Gender {

	MALE("male"), FEMALE("female");

	private String value;

	private Gender(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Gender fromValue(String value) {
		for (Gender gender : Gender.values()) {
			if (gender.getValue().equals(value)) {
				return gender;
			}
		}
		throw new UnsupportedGenderException(
				String.format("%s is not a value, supported values are: ", value, Gender.values()));
	}

	public static boolean isValidGender(String value) {
		for (Gender gender : Gender.values()) {
			if (gender.getValue().equals(value)) {
				return true;
			}
		}
		throw new UnsupportedGenderException(
				String.format("%s is not a value, supported values are: ", value, Gender.values()));
	}
}
