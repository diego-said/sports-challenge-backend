package br.com.doublelogic.sportschallengebackend.streamfind;

public class StringStream implements Stream {

	private final String s;
	
	private int index = 0;
	
	public StringStream(String s) {
		this.s = s;
	}

	public char getNext() {
		char c = s.charAt(index);
		index++;
		return c;
	}

	public boolean hasNext() {
		return index < s.length();
	}

}
