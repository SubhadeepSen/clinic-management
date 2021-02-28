package dr.sens.dental.clinic.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.ui.Model;

public class DentalClinicUtils {

	private DentalClinicUtils() {
	}

	public static void addToModel(Model model, String key, Object value) {
		model.addAttribute(key, value);
	}

	public static void removeFromModel(Model model, String key) {
		model.addAttribute(key, null);
	}

	public static String getUniqueId() {
		return RandomStringUtils.randomAlphanumeric(12);
	}
}
