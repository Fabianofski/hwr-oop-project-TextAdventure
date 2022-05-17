package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FixedObjectsTest {

    @Nested
    class DoorTests{
        @Test
        void door_isFieldObject(){
            Door door = new Door();
            assertThat(door)
                    .isInstanceOf(FieldObject.class)
                    .isInstanceOf(Door.class);
        }
    }
    @Nested
    class NothingTests{
        @Test
        void nothing_isFieldObject(){
            Nothing nothing = new Nothing();
            assertThat(nothing)
                    .isInstanceOf(FieldObject.class)
                    .isInstanceOf(Nothing.class);
        }
    }
    @Nested
    class NPCTests{
        @Test
        void npc_isFieldObject(){
            NPC npc = new NPC();
            assertThat(npc)
                    .isInstanceOf(FieldObject.class)
                    .isInstanceOf(NPC.class);
        }
    }
    @Nested
    class WallTests{
        @Test
        void wall_isFieldObject(){
            Wall wall = new Wall();
            assertThat(wall)
                    .isInstanceOf(FieldObject.class)
                    .isInstanceOf(Wall.class);
        }
    }

}
