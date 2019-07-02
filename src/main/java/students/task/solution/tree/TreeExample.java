package students.task.solution.tree;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

public class TreeExample {

    @Test
    public void test2() {
        TreeMap<String, Integer> tm = new TreeMap<>();
        tm.put("SSN", 1);
        tm.put("Employee", 2);
        tm.put("C", 3);
        tm.put("D", 4);
        tm.put("E", 5);
        tm.put("SSN", 1);
        tm.put("SSN", 11);
        tm.put("SSN", 111);

        System.out.println(tm);
    }


    static class User{
        String name;
        String lastName;

        public User(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }
    }

    @Test
    public void test3() {
        TreeMap<User, Integer> tm = new TreeMap<>();

        tm.put(new User("Ivan", "Ivanov"), 2300);
        tm.put(new User("Tanya", "Ivanova"), 1500);
        tm.put(new User("Stepan", "Petrow"), 4000);
        tm.put(new User("Vova", "Koval"), 2900);

        System.out.println(tm);
    }
}
