package de.rho.client;

import org.omg.CORBA.PRIVATE_MEMBER;

public class TEST {

	public static void main(String[] args) {
		Integer a;
		Integer b;
		a = 1;
		b = 2;
		
		System.out.println("1. Test");
		test(a,b);
		
		System.out.println("\n2. Test");
		b = null;
		test(a,b);
		 
	}
	
	private static void test(int a, int b) {
		if (String.valueOf(a).isEmpty()) {
			System.out.println("a - leer");
		}
		else {
			System.out.println("a - voll");
		}
		
		if (String.valueOf(b).isEmpty()) {
			System.out.println("b - leer");
		}
		else {
			System.out.println("b - voll");
		}
	}

}