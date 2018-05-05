package br.com.doublelogic.sportschallengebackend.streamfind;

public class App {
	
	public static void main(String[] args) {
		//testando a string informada
		StringStream stringStream = new StringStream("aAbBABacafe");
		System.out.println(VowelFinder.firstChar(stringStream));
	}

}