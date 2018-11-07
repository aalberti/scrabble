package arolla.aa;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class ScrabbleTest {
    private static Scrabble scrabble;
    private static Set<String> playedWords;

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
    public void best_played_word_is_whizzing() throws IOException {
        String bestValidWord = scrabble.bestValidWord(playedWords);
        assertThat(bestValidWord).isEqualTo("whizzing");
    }

    @Test
    public void histogram() {
        Map<Integer, Integer> histogram = scrabble.histogram(playedWords);
        assertThat(histogram)
                .hasSize(29)
                .contains(
                        entry(33, 1),
                        entry(28, 9),
                        entry(16, 360),
                        entry(8, 1459),
                        entry(2, 26)
                );
    }
}
