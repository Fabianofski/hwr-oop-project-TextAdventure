package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FixedObjectsTest {

    @Nested
    class DoorTests{
        Door door;
        @BeforeEach
        void setUp() {
            door = new Door();
        }

        @Test
        void door_isFieldObject(){
            assertThat(door)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(Door.class);
        }

        @Test
        void door_getObjectIcon_iconIsPi() {
            String icon = door.getObjectIcon();
            assertThat(icon).isEqualTo("Π");
        }
    }
    @Nested
    class NothingTests{
        Nothing nothing;

        @BeforeEach
        void setUp() {
            nothing = new Nothing();
        }

        @Test
        void nothing_isFieldObject(){
            nothing = new Nothing();
            assertThat(nothing)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(Nothing.class);
        }

        @Test
        void nothing_getObjectIcon_iconIsEmptyString() {
            String icon = nothing.getObjectIcon();
            assertThat(icon).isEqualTo(" ");
        }
    }
    @Nested
    class NPCTests{
        NPC npc;

        @BeforeEach
        void setUp() {
            npc = new NPC();
        }

        @Test
        void npc_isFieldObject(){
            npc = new NPC();
            assertThat(npc)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(NPC.class);
        }

        @Test
        void npc_getObjectIcon_iconIsOmega() {
            String icon = npc.getObjectIcon();
            assertThat(icon).isEqualTo("Ω");
        }
    }
    @Nested
    class WallTests{

        Wall wall;

        @BeforeEach
        void setUp() {
            wall = new Wall();
        }

        @Test
        void wall_isFieldObject(){
            wall = new Wall();
            assertThat(wall)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(Wall.class);
        }

        @Test
        void wall_getObjectIcon_iconIsHashtag() {
            String icon = wall.getObjectIcon();
            assertThat(icon).isEqualTo("#");
        }
    }

}
