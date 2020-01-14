package icetea916.test.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    private String message;

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return message;
    }

    public MyCallable(String message) {
        this.message = message;
    }
}
