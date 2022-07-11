package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LevelMangerTest {
    @Nested
    class LevelsTests{
        LevelManager levelManager;

        @BeforeEach
        void setUp() {
            IOHandler ioHandler = new IOHandler(System.in, System.out);
            levelManager =new LevelManager(ioHandler);
        }

        @Test
        void getLevel1_givesLevel1() {
            Game game = levelManager.getLevel(0);
            int expected = 0;
            assertThat(expected).isEqualTo(levelManager.getCurrentLevel());
        }
        @Test
        void getLevel2_givesLevel2() {
            Game game = levelManager.getLevel(1);
            int expected = 1;
            assertThat(expected).isEqualTo(levelManager.getCurrentLevel());

        }
        @Test
        void getLevel3_givesLevel3() {
            Game game = levelManager.getLevel(2);
            int expected = 2;
            assertThat(expected).isEqualTo(levelManager.getCurrentLevel());

        }
        @Test
        void getLevel4_doesntExist() {
            Game game = levelManager.getLevel(3);
            assertThat(game).isNull();

        }
    }
}
