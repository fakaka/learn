package test;

import java.util.Arrays;

import org.junit.Test;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

public class GuavaTest {

	@Test
	public void strings() {
		System.out.println(Strings.isNullOrEmpty(""));
	}

	@Test
	public void spiter() {
		Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
		System.out.println(splitter.split("a,b,  c,,"));

	}

	@Test
	public void joiner() {
		Joiner joiner = Joiner.on(",");
		String join = joiner.join("a", "b");
		System.out.println(join);
	}

	@Test
	public void testJoiner() {
		System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1, 2, 3, 4, 5, null, 6)));
	}

	@Test
	public void testCaseFormat() {
		System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
		System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
	}
}
