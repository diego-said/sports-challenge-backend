package br.com.doublelogic.sportschallengebackend.streamfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VowelFinder {

	private static final String VOWELS = "aAeEiIoOuU";

	/**
	 * Encontra a primeira vogal após uma consoante antecedida por uma vogal e que não 
	 * repete na stream.
	 * @param input stream que será pesquisada
	 * @return a primeira vogal encontrada ou o número 0 caso não seja possível
	 */
	public static char firstChar(Stream input) {
		Map<Character, Integer> usedVowels = new HashMap<>();
		List<Character> foundedVowels = new ArrayList<>(VOWELS.length());

		char c1 = '0';
		char c2 = '0';
		while (input.hasNext()) {
			char c3 = input.getNext();
			if(isVowel(c3)) {
				vowelTimesUsed(usedVowels, c3);
				if(isVowel(c1) && !isVowel(c2)) {
					foundedVowels.add(c3);
				}
			}
			c1 = c2;
			c2 = c3;
		}

		List<Character> result = foundedVowels.stream()
				.filter(c -> {
					return usedVowels.get(c) == 1 ? true : false;
				}).collect(Collectors.toList()); 
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return '0';
	}

	/**
	 * Determina quantas vezes uma vogal já foi usada e guarda o total
	 * @param usedVowels map que armazena o total de cada vogal
	 * @param c3 vogal a ser contada
	 */
	private static void vowelTimesUsed(Map<Character, Integer> usedVowels, char c3) {
		Integer timesUsed = usedVowels.get(c3);
		timesUsed = timesUsed == null ? 1 : timesUsed + 1;
		usedVowels.put(c3, timesUsed);
	}

	/**
	 * Determina se uma letra é vogal ou não
	 * @param c letra a ser verificada
	 * @return true para a letra ser vogal e false caso seja consoante
	 */
	private static boolean isVowel(char c) {
		return VOWELS.indexOf(c) != -1;
	}

}