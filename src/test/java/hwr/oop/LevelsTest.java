package hwr.oop;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LevelsTest {
    @Nested
    class LevelsTests{
        Levels levels;

        @Test
        void getLevel2_givesLevel2() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            levels=new Levels();
            levels.getLevel(2);
            String expected = "Level2";
            assertThat(expected).isEqualTo(levels.getCurrentLevel());

        }
        @Test
        void getLevel3_givesLevel3() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            levels=new Levels();
            levels.getLevel(3);
            String expected = "Level3";
            assertThat(expected).isEqualTo(levels.getCurrentLevel());

        }
        @Test
        void getLevel1_givesLevel1() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            levels=new Levels();
            levels.getLevel(1);
            String expected = "Level1";
            assertThat(expected).isEqualTo(levels.getCurrentLevel());

        }

    }
}
