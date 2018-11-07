package arolla.aa;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

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

    String bestValidWord(Set<String> playedWords) {
        Stream<String> wordsStream = playedWords.stream();
        return wordsStream
                .filter(dictionary::contains)
                .map(word -> new AbstractMap.SimpleImmutableEntry<>(score(word), word))
                .max(comparing(AbstractMap.SimpleImmutableEntry::getKey))
                .map(AbstractMap.SimpleImmutableEntry::getValue)
                .get();
    }

}
