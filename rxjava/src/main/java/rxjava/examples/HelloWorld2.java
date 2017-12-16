package rxjava.examples;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class HelloWorld2 {

	public static void main(String[] args) throws Exception {

		Flowable.fromCallable(() -> {
			Thread.sleep(1000); // imitate expensive computation
			return "Done";
		}).subscribeOn(Schedulers.io()).observeOn(Schedulers.single()).subscribe(System.out::println, Throwable::printStackTrace);

		Thread.sleep(2000); // <--- wait for the flow to finish
	}
}
