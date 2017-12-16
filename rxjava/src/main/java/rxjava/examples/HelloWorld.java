package rxjava.examples;

import io.reactivex.Flowable;

public class HelloWorld {

	public static void main(String[] args) throws Exception {
		Flowable.just("Hello world").subscribe(System.out::println);
	}
}
