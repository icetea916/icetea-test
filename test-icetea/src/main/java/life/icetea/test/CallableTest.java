package life.icetea.test;

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // runnable + result
        Future<Person> future2 = threadPool.submit(new CallableTask());

        try {
            Person person = future2.get(10, TimeUnit.SECONDS);
            System.out.println(person);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }


//        threadPool.shutdown();
    }

    static class CallableTask implements Callable<Person> {

        @Override
        public Person call() throws Exception {
            Person call = new Person(1, "call");
            TimeUnit.SECONDS.sleep(20);
//            int i = 1/0;
            return call;
        }

    }

    static class Person {
        private Integer id;
        private String name;

        public Person(Integer id, String name) {
            super();
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person [id=" + id + ", name=" + name + "]";
        }
    }

}
