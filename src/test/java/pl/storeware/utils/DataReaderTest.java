package pl.storeware.utils;

import org.junit.Test;
import pl.storeware.errors.NotSuchOperatorException;
import java.io.File;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataReaderTest {

    @Test
    public void checkTheFileIsPresent() {
        //given
        File file1 = new File("src/test/java/pl/storeware/utils/testFile1.txt");
        File file2 = new File("src/test/java/pl/storeware/utils/testFile11.txt");

        //when
        boolean test1 = file1.exists();
        boolean test2 = file2.exists();

        //then
        assertTrue(test1);
        assertFalse(test2);
    }

    @Test(expected = NotSuchOperatorException.class)
    public void shouldThrowOperatorException() {
        DataReader reader = new DataReader();
        File file1 = new File("src/test/java/pl/storeware/utils/testFile1.txt");
        reader.getEquation(file1);
    }
}