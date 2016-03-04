package j8;

import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Stream<User> userStream = Stream.of(
                new User("Dima", 25),
                new User("Pasha", 26),
                new User("Vasya", 11),
                new User("John", 99),
                new User("Petya", 26),
                new User("Mitya", 28));

//        filterMapGenericJoiner(userStream);
//        collectorToMap(userStream);
//        customJoiner(userStream);


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
                .collect(Collectors.toMap(
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
                .collect(Collectors.joining(", ", "These are the people: ", " that's it."));

        System.out.println("collect = " + collect);
    }

}
