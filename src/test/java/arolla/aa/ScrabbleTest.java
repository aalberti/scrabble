package arolla.aa;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@RunWith(JUnitParamsRunner.class)
public class ScrabbleTest {
    private static Scrabble scrabble;
    private static Set<String> playedWords;
    private static final Map<Character, Integer> AVAILABLE_LETTERS = new HashMap<Character, Integer>() {{
        put('d', 1);
        put('j', 1);
        put('k', 1);
        put('q', 1);
        put('x', 1);
        put('z', 1);
        put('b', 2);
        put('c', 2);
        put('f', 2);
        put('h', 2);
        put('m', 2);
        put('p', 2);
        put('v', 2);
        put('w', 2);
        put('y', 2);
        put('g', 3);
        put('l', 4);
        put('s', 4);
        put('u', 4);
        put('n', 6);
        put('r', 6);
        put('t', 6);
        put('o', 8);
        put('e', 12);
        put('a', Integer.MAX_VALUE);
        put('i', Integer.MAX_VALUE);
    }};

    @BeforeClass
    public static void setUpClass() throws Exception {
        Set<String> dictionary = Files.lines(Paths.get("dictionary.txt")).collect(toSet());
        scrabble = new Scrabble(dictionary);
        playedWords = Files.lines(Paths.get("playedwords.txt")).collect(toSet());
    }

    @Test
    public void empty_string_scores_0() {
        assertThat(Scrabble.score("")).isEqualTo(0);
    }

    @Test
    public void hello_is_8() {
        assertThat(Scrabble.score("hello")).isEqualTo(8);
    }

    @Test
    public void accept_upper_case() {
        assertThat(Scrabble.score("heLlo")).isEqualTo(8);
    }

    @Test
    public void best_played_word_is_whizzing() {
        String highestScore = scrabble.highestScore(playedWords);
        assertThat(highestScore).isEqualTo("whizzing");
    }

    @Test
    public void histogram() {
        assertThat(scrabble.histogram(playedWords))
                .hasSize(29)
                .contains(
                        entry(33, 1),
                        entry(28, 9),
                        entry(16, 360),
                        entry(8, 1459),
                        entry(2, 26)
                );
    }

    @Test
    public void best_three_scores() {
        Map<Integer, Collection<String>> bestThreeScores = scrabble.bestThreeScores(playedWords);
        assertThat(bestThreeScores)
                .containsOnly(
                        entry(33, singleton("whizzing")),
                        entry(29, singleton("buzzards")),
                        entry(28, set("mazzard", "dazzling", "grizzled", "puzzled", "unmuzzle", "drizzled", "muzzled", "buzzard", "buzzing"))
                );
    }

    private Set<String> set(String... values) {
        return new HashSet<>(asList(values));
    }

    @Test
    @Parameters({
            "whizzing, 23",
            "quickly, 25",
            "squeezes, 26",
            "buzzards, 19",
            "delated, 7"
    })
    public void score_with_available_letters(String word, int score) {
        assertThat(Scrabble.score(word, AVAILABLE_LETTERS)).isEqualTo(score);
    }
}
