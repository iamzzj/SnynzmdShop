package com.snynzmd.shop.utils;


import com.snynzmd.shop.entity.Assistant;

import java.util.Comparator;

public class PinyinComparator implements Comparator<Assistant> {

	public int compare(Assistant o1, Assistant o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
