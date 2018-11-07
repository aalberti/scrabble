package arolla.aa;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class ScrabbleTest {
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
        List<String> dictionary = Files.lines(Paths.get("dictionary.txt")).collect(toList());
        Scrabble scrabble = new Scrabble(dictionary);
        List<String> playedWords = Files.lines(Paths.get("playedwords.txt")).collect(toList());
        String bestValidWord = scrabble.bestValidWord(playedWords);
        assertThat(bestValidWord).isEqualTo("whizzing");
    }
}
