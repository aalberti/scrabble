package arolla.aa;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

class Scrabble {
    private static final Map<Character, Integer> pointsPerLetter = new HashMap<Character, Integer>() {{
        put('a', 1);
        put('b', 3);
        put('c', 3);
        put('d', 2);
        put('e', 1);
        put('f', 4);
        put('g', 2);
        put('h', 4);
        put('i', 1);
        put('j', 8);
        put('k', 5);
        put('l', 1);
        put('m', 3);
        put('n', 1);
        put('o', 1);
        put('p', 3);
        put('q', 10);
        put('r', 1);
        put('s', 1);
        put('t', 1);
        put('u', 1);
        put('v', 4);
        put('w', 4);
        put('x', 8);
        put('y', 4);
        put('z', 10);
    }};

    private Set<String> dictionary;

    Scrabble(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    static int score(String word) {
        return word
                .toLowerCase()
                .chars().mapToObj(c -> (char) c)
                .map(pointsPerLetter::get)
                .mapToInt(Integer::intValue).sum();
    }

    String bestValidWord(Set<String> words) {
        return words.stream()
                .filter(dictionary::contains)
                .map(word -> new AbstractMap.SimpleImmutableEntry<>(score(word), word))
                .max(comparing(AbstractMap.SimpleImmutableEntry::getKey))
                .map(AbstractMap.SimpleImmutableEntry::getValue)
                .get();
    }

    Map<Integer, Integer> histogram(Set<String> words) {
        return words.stream()
                .filter(dictionary::contains)
                .collect(toMap(
                        Scrabble::score,
                        w -> 1,
                        (count1, count2) -> count1 + count2
                ));
    }

    Map<Integer, Collection<String>> bestThreeScores(Set<String> words) {
        return words.stream()
                .filter(dictionary::contains)
                .collect(toMap(
                        Scrabble::score,
                        Collections::singleton,
                        this::concatenate
                )).entrySet().stream()
                .sorted((e1, e2) -> e2.getKey() - e1.getKey())
                .limit(3)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Collection<String> concatenate(Collection<String> a, Collection<String> b) {
        return new HashSet<String>() {{
            addAll(a);
            addAll(b);
        }};
    }
}
