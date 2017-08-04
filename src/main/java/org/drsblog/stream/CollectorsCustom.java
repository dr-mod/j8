package org.drsblog.stream;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorsCustom {

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("Dima", 25),
                new User("Pasha", 26),
                new User("Vasya", 11),
                new User("John", 99),
                new User("Petya", 26),
                new User("Mitya", 28)
        );

        filterMapGenericJoiner(users.stream());
        collectorToMap(users.stream());
        customJoiner(users.stream());
    }

    private static void customJoiner(Stream<User> userStream) {
        String collect = userStream
                .collect(Collector.of(
                        () -> new StringJoiner(" | "),
                        (joiner, user) -> joiner.add(user.getName()),
                        StringJoiner::merge,
                        StringJoiner::toString
                ));

        System.out.println("customJoiner = " + collect);
    }

    private static void collectorToMap(Stream<User> userStream) {
        Map<Integer, String> collect = userStream
                .collect(java.util.stream.Collectors.toMap(
                        User::getAge,
                        User::getName,
                        (user1, user2) -> user1 + "; " + user2
                ));

        System.out.println("collectorToMap = " + collect);
    }

    private static void filterMapGenericJoiner(Stream<User> userStream) {
        String collect = userStream
                .filter(user -> user.getAge() < 80)
                .map(User::getName)
                .map(String::toUpperCase)
                .collect(java.util.stream.Collectors.joining(", ", "These are the people: ", " that's it."));

        System.out.println("collect = " + collect);
    }

    @Data
    static private class User {
        final private String name;
        final private int age;
    }

}
