package br.com.doublelogic.sportschallengebackend.streamfind;

import org.junit.Assert;
import org.junit.Test;

public class VowelFinderTest {
    
	@Test
	public void testGiven() {
		StringStream stringStream = new StringStream("aAbBABacafe");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('e', result);
	}
	
	@Test
	public void test1() {
		StringStream stringStream = new StringStream("abe");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('e', result);
	}
	
	@Test
	public void test2() {
		StringStream stringStream = new StringStream("ABe");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('e', result);
	}
	
	@Test
	public void test3() {
		StringStream stringStream = new StringStream("aBe");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('e', result);
	}
	
	@Test
	public void test4() {
		StringStream stringStream = new StringStream("Abe");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('e', result);
	}
	
	@Test
	public void test5() {
		StringStream stringStream = new StringStream("ABE");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('E', result);
	}
	
	@Test
	public void test6() {
		StringStream stringStream = new StringStream("aBE");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('E', result);
	}
	
	@Test
	public void test7() {
		StringStream stringStream = new StringStream("AbE");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('E', result);
	}
	
	@Test
	public void test8() {
		StringStream stringStream = new StringStream("abeabe");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('0', result);
	}
	
	@Test
	public void test9() {
		StringStream stringStream = new StringStream("abeabi");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('e', result);
	}
	
	@Test
	public void test10() {
		StringStream stringStream = new StringStream("abeabeabi");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('i', result);
	}
	
	@Test
	public void test11() {
		StringStream stringStream = new StringStream("abiabeabi");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('e', result);
	}
	
	@Test
	public void test12() {
		StringStream stringStream = new StringStream("abiabeabe");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('i', result);
	}
	
	@Test
	public void test13() {
		StringStream stringStream = new StringStream("ab");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('0', result);
	}
	
	@Test
	public void test14() {
		StringStream stringStream = new StringStream("ba");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('0', result);
	}
	
	@Test
	public void test15() {
		StringStream stringStream = new StringStream("");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('0', result);
	}
	
	@Test
	public void test16() {
		StringStream stringStream = new StringStream("a");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('0', result);
	}
	
	@Test
	public void test17() {
		StringStream stringStream = new StringStream("abeE");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('e', result);
	}
	
	@Test
	public void test18() {
		StringStream stringStream = new StringStream("abee");
		char result = VowelFinder.firstChar(stringStream);
		Assert.assertEquals('0', result);
	}
	
}