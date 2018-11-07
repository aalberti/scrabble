package arolla.aa;

import org.junit.Test;

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
}
