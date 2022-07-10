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
        void getLevel1_givesLevel1() {
            levels=new Levels();
            levels.getLevel(1);
            String expected = "Level1";
            assertThat(expected).isEqualTo(levels.getCurrentLevel());

        }
        @Test
        void getLevel2_givesLevel2() {
            levels=new Levels();
            levels.getLevel(2);
            String expected = "Level2";
            assertThat(expected).isEqualTo(levels.getCurrentLevel());

        }
        @Test
        void getLevel3_givesLevel3() {
            levels=new Levels();
            levels.getLevel(3);
            String expected = "Level3";
            assertThat(expected).isEqualTo(levels.getCurrentLevel());

        }
        @Test
        void getLevel4_doesntExist() {
            levels = new Levels();
            Game game = levels.getLevel(4);
            assertThat(game).isNull();

        }
        @Test
        void getIohandler_game(){
            Levels level = new Levels();
            assertThat(level.getIOHandler().getOutputBuffer()).isEqualTo("");
        }

    }
}
