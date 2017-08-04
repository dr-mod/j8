package org.drsblog.stream;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Functional {

    public static void main(String[] args) {
        final Predicate<Tuple> tupleNotEmpty = t -> !t._2.isEmpty();

        List<Tuple> a = IntStream.range(1, 100)
                .mapToObj(Tuple::new)
                .map(nWord(3, "Foo"))
                .map(nWord(5, "Bar"))
                .filter(tupleNotEmpty)
                .collect(Collectors.toList());
        System.out.println(a);
    }

    private static Function<Tuple, Tuple> nWord(Integer n, String w){
        return t -> (t._1 % n == 0) ? new Tuple(t._1, t._2 + w) : t;
    }

}

@AllArgsConstructor
@ToString(includeFieldNames = false)
class Tuple {
    final public Integer _1;
    final public String _2;

    public Tuple(Integer i){
        this(i, "");
    }
}
