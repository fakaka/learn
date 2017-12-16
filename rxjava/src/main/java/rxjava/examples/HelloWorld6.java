package rxjava.examples;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 * @author MJ
 *
 */
public class HelloWorld6 {

	public static void main(String[] args) throws Exception {
		Flowable.range(1, 10).parallel().runOn(Schedulers.computation()).map(v -> v * v).sequential()
				.blockingSubscribe(System.out::println);
	}
}
