package by.kunavich.task_thread.data;

import by.kunavich.task_tread.data.VanParser;
import by.kunavich.task_tread.model.Van;
import org.junit.Assert;
import org.junit.Test;

public class VanParserTest {
    @Test
    public void testParseShouldReturnTaxisWhenInputIsCorrect() {
        VanParser parser = new VanParser();

        Van actual = parser.parse("false 17");

        Van expected = new Van(false,17);
        Assert.assertEquals(expected, actual);
    }
}
