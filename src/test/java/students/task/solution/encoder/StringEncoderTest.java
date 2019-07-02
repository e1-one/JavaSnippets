package students.task.solution.encoder;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class StringEncoderTest {
    private StringEncoder stringEncoder = new StringEncoder();

    @Test
    void convert_1() {
        Assert.assertEquals("3a", stringEncoder.encode("aaa"));
    }

    @Test
    void convert_2() {
        Assert.assertEquals("3a2B", stringEncoder.encode("aaaBB"));
    }

    @Test
    void convert_3() {
        Assert.assertEquals("\\1\\2", stringEncoder.encode("12"));
    }

    @Test
    void convert_4() {
        Assert.assertEquals("\\\\", stringEncoder.encode("\\"));
    }

    @Test
    void convert_5() {
        Assert.assertEquals("2a\\5", stringEncoder.encode("aa5"));
    }

    @Test
    void convert_10() {
        Assert.assertEquals("4A3a6B\\\\\\1\\2", stringEncoder.encode("AAAAaaaBBBBBB\\12"));
    }

}