import java.util.*;

/**
 * <b>把一个数分解成任意几个数之和（递归法）</b>
 */
public class Demo1 {

	private static List<Integer> list = new ArrayList<Integer>();
	private static Set<Result> results = new HashSet<Result>();

	public static void main(String[] args) {
		int num = 5;
		for (int i = 1; i < num; i++) {
			check(num, i);
		}

		for (Result result : results) {
			System.out.println(result.list);
		}
	}

	public static void check(int num, int i) {
		list.add(i);
		list.add(num - i);
		results.add(new Result(new ArrayList<Integer>(list)));
		list.remove(list.size() - 1);
		if (num - i > 1) {
			check(num - i, 1);
		}
		list.clear();
	}
}

class Result {
	List<Integer> list;

	public Result(List<Integer> list) {
		Collections.sort(list);
		this.list = list;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Result) {
			Result result = (Result) obj;
			if (result.list.size() == this.list.size()) {
				for (int i = 0; i < this.list.size(); i++) {
					if (result.list.get(i) != this.list.get(i)) {
						return false;
					}
				}
				return true;
			}
		}

		return false;
	}

	public int hashCode() {
		return list.size();
	}
}
