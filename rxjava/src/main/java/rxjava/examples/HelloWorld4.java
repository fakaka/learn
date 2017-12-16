package rxjava.examples;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 * @author MJ
 *
 */
public class HelloWorld4 {

	public static void main(String[] args) throws Exception {
		Flowable.range(1, 10).observeOn(Schedulers.computation()).map(v -> v * v).blockingSubscribe(System.out::println);
	}
}
