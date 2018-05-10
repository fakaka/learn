package test;

import java.util.ArrayList;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class GuavaTest {

	public static void main(String[] args) {
		ArrayList<String> newArrayList = Lists.newArrayList();
		newArrayList.add("a");

	}

	@Test
	public void strings() {
		System.out.println(Strings.isNullOrEmpty(""));
	}

	@Test
	public void spiter() {
		Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
		Iterable<String> split = splitter.split("a,b,  c,,");
		System.out.println(split);

	}

	@Test
	public void joiner() {
		Joiner joiner = Joiner.on(",");
		String join = joiner.join("a", "b");
		System.out.println(join);
	}

}
