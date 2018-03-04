import com.mj.collection.ArrayList;
import com.mj.collection.List;

public class TestArrayList {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");

		list.remove(2);

		list.remove("ddd");
		list.set(0, "000");

		System.out.println(list);

		for (String str : list) {
			System.out.println(str);
		}

	}
}
