package arolla.aa;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class ScrabbleTest {

    private static Scrabble scrabble;

    @BeforeClass
    public static void setUpClass() throws Exception {
        Set<String> dictionary = Files.lines(Paths.get("dictionary.txt")).collect(toSet());
        scrabble = new Scrabble(dictionary);
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
        Set<String> playedWords = Files.lines(Paths.get("playedwords.txt")).collect(toSet());
        String bestValidWord = scrabble.bestValidWord(playedWords);
        assertThat(bestValidWord).isEqualTo("whizzing");
    }
}
