package util;

import java.util.Arrays;
import java.util.Enumeration;
import sun.misc.Queue;

public class DataUtil {

	private DataUtil() {
		
	}
	
	public static <T> boolean queueContains(Queue<T> q,T o) {
		Enumeration<T> e = q.elements();
		
		while(e.hasMoreElements()) {
			if (e.nextElement() == o)
				return true;
		}
		return false;
	}
	
	public static <T> T[] prepend(T[] array, T newElement) {
		T[] newArray = Arrays.copyOf(array, array.length+1);
		for (int x = 1; x < newArray.length; x++) {
			newArray[x] = array[x-1];
		}
		newArray[0] = newElement;
		return newArray;
	}
	
}
