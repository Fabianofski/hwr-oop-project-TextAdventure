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
        IOutputBuffer outputBuffer;
        @BeforeEach
        void setUp() {
            outputBuffer = new OutputBuffer();
            door = new Door(outputBuffer);
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

        @Test
        void door_writeEventOutputBuffer_writesToOutputBuffer() {
            door.writeEventOutputBuffer();

            assertThat(outputBuffer.getOutputBuffer()).isEqualTo("\nYou don't have a Key to open the door.");
        }
    }
    @Nested
    class NothingTests{
        Nothing nothing;
        IOutputBuffer outputBuffer;

        @BeforeEach
        void setUp() {
            outputBuffer = new OutputBuffer();
            nothing = new Nothing(outputBuffer);
        }

        @Test
        void nothing_isFieldObject(){
            nothing = new Nothing(outputBuffer);
            assertThat(nothing)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(Nothing.class);
        }

        @Test
        void nothing_getObjectIcon_iconIsEmptyString() {
            String icon = nothing.getObjectIcon();
            assertThat(icon).isEqualTo(" ");
        }

        @Test
        void nothing_writeEventOutputBuffer_writesToOutputBuffer() {
            nothing.writeEventOutputBuffer();

            assertThat(outputBuffer.getOutputBuffer()).isEqualTo("\nNothing happens!");
        }
    }
    @Nested
    class NPCTests{
        NPC npc;
        IOutputBuffer outputBuffer;


        @BeforeEach
        void setUp() {
            outputBuffer = new OutputBuffer();
            npc = new NPC(outputBuffer);
        }

        @Test
        void npc_isFieldObject(){
            npc = new NPC(outputBuffer);
            assertThat(npc)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(NPC.class);
        }

        @Test
        void npc_getObjectIcon_iconIsOmega() {
            String icon = npc.getObjectIcon();
            assertThat(icon).isEqualTo("Ω");
        }

        @Test
        void npc_writeEventOutputBuffer_writesToOutputBuffer() {
            npc.writeEventOutputBuffer();

            assertThat(outputBuffer.getOutputBuffer()).isEqualTo("\nTalking to NPC.");
        }
    }
    @Nested
    class WallTests{

        Wall wall;
        IOutputBuffer outputBuffer;

        @BeforeEach
        void setUp() {
            outputBuffer = new OutputBuffer();
            wall = new Wall(outputBuffer);
        }

        @Test
        void wall_isFieldObject(){
            assertThat(wall)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(Wall.class);
        }

        @Test
        void wall_getObjectIcon_iconIsHashtag() {
            String icon = wall.getObjectIcon();
            assertThat(icon).isEqualTo("#");
        }

        @Test
        void wall_writeEventOutputBuffer_writesToOutputBuffer() {
            wall.writeEventOutputBuffer();

            assertThat(outputBuffer.getOutputBuffer()).isEqualTo("\nYou shouldn't be stuck in the wall!");
        }
    }

}
