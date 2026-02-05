package com.hana8.hello;

import java.util.Arrays;

public class AutoBoxingString {

	public static void main(String[] args) {
//    Integer iObj = new Integer(value: 100); // Deprecated
		Integer iObj = 100;
		System.out.println("iObj.byteValue() = " + iObj.byteValue());
		int i1 = iObj;

		String s = "hong@gmail.com";
		int idxAt = s.indexOf('@');
		System.out.println("idxAt = " + idxAt);
		String name = s.substring(0, idxAt);
		System.out.println("name = " + name);
		String domain = s.substring(idxAt + 1);
		System.out.println("domain = " + domain);

		String[] name_domain = s.split("@");
		System.out.println("name_domain = " + Arrays.toString(name_domain));
		String name1 = name_domain[0];
		String domain1 = name_domain[1];

		for (String str : s.split("@")) {
			System.out.println("str = " + str);
		}
	}

}
