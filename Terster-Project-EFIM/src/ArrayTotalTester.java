import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ArrayTotalTester {

	public static void main(String[] args) {
		Map<Integer, String> test = new HashMap<>();
		test.put(2, "Polos");
		test.put(1, "Loop");
		test.put(4, "Polk");
		test.put(3, "Lokj");
		test.put(3, "Lokj baru");
		for (Integer str : test.keySet()) {
			System.err.println(test.get(str));
		}
	}

}
