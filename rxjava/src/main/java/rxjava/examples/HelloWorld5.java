package rxjava.examples;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 * @author MJ
 *
 */
public class HelloWorld5 {

	public static void main(String[] args) throws Exception {
		Flowable.range(1, 10).flatMap(v -> Flowable.just(v).subscribeOn(Schedulers.computation()).map(w -> w * w))
				.blockingSubscribe(System.out::println);
	}
}
