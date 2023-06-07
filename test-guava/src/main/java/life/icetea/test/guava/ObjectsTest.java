package life.icetea.test.guava;

import com.google.common.base.Objects;

public class ObjectsTest {

    public static void main(String[] args) {
        System.out.println(Objects.equal(new Person("peida", 23), new Person("peida", 23)));
        Person person = new Person("peida", 23);
        System.out.println(Objects.equal(person, person));

        System.out.println(Objects.hashCode("a"));
        System.out.println(Objects.hashCode("a"));
        System.out.println(Objects.hashCode("a", "b"));
        System.out.println(Objects.hashCode("b", "a"));
        System.out.println(Objects.hashCode("a", "b", "c"));

        Person person2 = new Person("peida", 23);
        System.out.println(Objects.hashCode(person));
        System.out.println(Objects.hashCode(person2));

    }

    static class Person {
        public String name;
        public int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
