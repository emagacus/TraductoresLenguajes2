package lexico;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lexico {

	public String[] getTokens(String input) {
		return input.trim().split(" ");
	}

	public Map<String, String> getTokentypes(String[] tokens) {

		Map<String, String> map = new HashMap<String, String>();
		Arrays.stream(tokens).forEach(token -> map.put(token, getType(analizeToken(token))));

		return map;
	}

	public String getType(int id) {
		switch (id) {
		case 1:
			return "Identificador";
		case 2:
			return "Numero Real";
		default:
			return "Token invalido";

		}
	}

	public int analizeToken(String token) {

		// 1 = id
		// 2 = real

		char initial = token.charAt(0);

		int current = -1;

		current = state0(initial);

		for (int i = 1; i < token.length(); i++) {
			switch (current) {
			case 1:// letter detected
				current = state1(token.charAt(i));
				break;
			case 2:// number detected
				current = state2(token.charAt(i));
				break;
			case -2: // a . was detected
				current = state3(token.charAt(i));
				break;
			default:
				current = -1;
			}
			// System.out.println(token.charAt(i));
		}

		return current;
	}

	// estados
	public int state0(char c) {
		if (isNumber(c)) {
			return 2;
		}
		if (isAlphabetic(c)) {
			return 1;
		}
		return -1;
	}

	public int state1(char c) {
		if (isAlphaNumeric(c)) {
			return 1;
		}
		return -1;
	}

	public int state2(char c) {
		if (isDot(c)) {
			return -2;
		} else if (isNumber(c)) {
			return 2;
		}
		return -1;
	}

	public int state3(char c) {
		if (isNumber(c)) {
			return 2;
		}

		return -1;
	}

	public boolean isNumber(char c) {
		return Character.isDigit(c);
	}

	public boolean isAlphabetic(char c) {
		return Character.isAlphabetic(c);
	}

	public boolean isAlphaNumeric(char c) {
		return (Character.isDigit(c) || Character.isAlphabetic(c));
	}

	public boolean isDot(char c) {
		return c == '.';
	}

}
