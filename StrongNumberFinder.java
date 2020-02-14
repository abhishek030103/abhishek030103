
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StrongNumberFinder {

	/**
	 * Input of this method will be 2351234
	 * Output of program will be: StrongNumberOutPut [strongest=5, steps=[451, 45, 5]]
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String strNumber = in.nextLine(); 
		StrongNumberOutPut strongNumberOutPut = new StrongNumberFinder.StrongNumberOutPut();
		StrongNumberOutPut strongNumber = getStrongNumber(strNumber, strongNumberOutPut);
		System.out.println(strongNumber);

	}

	static StrongNumberOutPut getStrongNumber(String strNumber, StrongNumberOutPut strongNumberOutPut) {

		if (strNumber.length() <= 1) {
			strongNumberOutPut.strongest = Integer.parseInt(strNumber);
			return strongNumberOutPut;
		}

		CharacterIterator characterItrForwrd = new StringCharacterIterator(strNumber);
		CharacterIterator characterItrBckwrd = new StringCharacterIterator(strNumber);
		characterItrBckwrd.last();
		StringBuilder strBuilder = new StringBuilder();

		while (characterItrForwrd.getIndex() < characterItrBckwrd.getIndex()) {
			int leftDigit = Integer.parseInt(String.valueOf(characterItrForwrd.current()));
			characterItrForwrd.next();

			int rightDigit = Integer.parseInt(String.valueOf(characterItrBckwrd.current()));
			characterItrBckwrd.previous();

			if (leftDigit < rightDigit) {
				strBuilder.append(rightDigit);
			} else if (leftDigit > rightDigit) {
				strBuilder.append(leftDigit);
			}
		}

		// lone number digit
		if (characterItrForwrd.getIndex() == characterItrBckwrd.getIndex()) {
			strBuilder.append(Integer.parseInt(String.valueOf(characterItrForwrd.current())));
		}

		strongNumberOutPut.steps.add(Integer.parseInt(strBuilder.toString()));
		return getStrongNumber(strBuilder.toString(), strongNumberOutPut);
	}

	static class StrongNumberOutPut {
		int strongest;
		List<Integer> steps = new ArrayList<>();

		@Override
		public String toString() {
			return "StrongNumberOutPut [strongest=" + strongest + ", steps=" + steps + "]";
		}

	}

}
