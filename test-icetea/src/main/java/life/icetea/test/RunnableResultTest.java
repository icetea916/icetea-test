package life.icetea.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableResultTest {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        new Thread();
        // runnable + result
        Person p = new Person(0, "person");
        Future<Person> future2 = threadPool.submit(new RunnableTask(), p);
        try {
            System.out.println("feature.get");
            Person person = future2.get();
            System.out.println(person);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class RunnableTask implements Runnable {

        @Override
        public void run() {

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
