package rxjava.examples;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 * @author MJ
 *
 */
public class HelloWorld3 {

	public static void main(String[] args) throws Exception {
		Flowable<String> source = Flowable.fromCallable(() -> {
			Thread.sleep(1000); // imitate expensive computation
			return "Done";
		});

		Flowable<String> runBackground = source.subscribeOn(Schedulers.io());

		Flowable<String> showForeground = runBackground.observeOn(Schedulers.single());

		showForeground.subscribe(System.out::println, Throwable::printStackTrace);

		Thread.sleep(2000);
	}
}
