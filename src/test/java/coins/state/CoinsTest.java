package coins.state;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CoinsTest {

    private Coins state1 = new Coins(7, 3); // the original initial state

    private Coins state2; // the goal state
    {
        BitSet bs = new BitSet(7);
        bs.set(0, 7);
        state2 = new Coins(7, 3, bs);
    }

    @BeforeEach
    void setUp(){
        state1 = new Coins(7,3);

        BitSet bitSet = new BitSet(7);
        bitSet.set(0,7);
        state2 = new Coins(7,3,bitSet);
    }

    @AfterEach
    void tearDown(){
        state1 = null;
        state2 = null;
    }

    @Test
    void isGoalTrueTest(){
        assertTrue(state2.isGoal());
    }

    @Test
    void isGoalFalseTest(){
        assertFalse(state1.isGoal());
        assertEquals(false,state1.isGoal());
    }

    @Test
    void canFlipTestFalse(){
        BitSet bitSet = new BitSet(state1.getN());
        bitSet.set(0, state1.getM() - 1);
        bitSet.set(state1.getN());
        assertFalse(state1.canFlip(bitSet));

        BitSet bitSet2 = new BitSet(state1.getM());
        bitSet2.set(0, state1.getM() - 2);
        bitSet2.set(state1.getN());
        assertFalse(state1.canFlip(bitSet2));

        BitSet bitSet3 = new BitSet(state1.getN());
        assertFalse(state1.canFlip(bitSet3));
    }

    @Test
    void canFlipTestTrue(){
        BitSet bitSet = new BitSet(state1.getN());
        bitSet.set(0, state1.getM());
        assertTrue(state1.canFlip(bitSet));
    }

    @Test
    void flipTest() {
        BitSet bitSet = new BitSet(state1.getN());
        bitSet.set(0, state1.getN());
        state1.flip(bitSet);
        assertEquals(bitSet, state1.getCoins());
    }

    @Test
    void getFlipsTestTrue() {
        assertTrue(state1.getFlips().size() != 0);
    }

    @Test
    void getFlipsTestFalse() {
        assertFalse(state1.getFlips().size() == 0);
    }

    @Test
    void equalsTestFalse(){
        BitSet bitSet = new BitSet(4);
        bitSet.set(0, 2);
        Coins coin1 = new Coins(2, 2, bitSet);
        Coins coin2 = new Coins(4, 2, bitSet);
        Coins coin3 = new Coins(3, 3, bitSet);
        Coins coin4 = new Coins(4, 1, bitSet);
        Coins coin5 = new Coins(6, 1, new BitSet(6));

        assertFalse(coin1.equals(coin2));
        assertFalse(coin1.equals(coin3));
        assertFalse(coin1.equals(coin4));
        assertFalse(coin1.equals(coin5));
        assertFalse(coin1.equals(new Object()));
    }

    @Test
    void equalsTestTrue(){
        BitSet bitSet = new BitSet(5);

        Coins coin1 = new Coins(5, 1, bitSet);
        Coins coin2 = new Coins(5, 1, bitSet);
        assertTrue(coin1.equals(coin1));
        assertTrue(coin1.equals(coin2));
    }

    @Test
    void hashCodeTestTrue() {
        assertTrue(state1.hashCode() == Objects.hash(state1.getN(), state1.getM(), state1.getCoins()));
    }

    @Test
    void hashCodeTestFalse() {
        assertFalse(state1.hashCode() == state2.hashCode());
    }

    @Test
    void checkArgumentsTest() {
        BitSet bitSet = new BitSet(6);
        bitSet.set(4);
        assertThrows(IllegalArgumentException.class, () -> new Coins(2, 1, bitSet));
    }

    @Test
    void generateFlipsTest(){
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(4, 0));
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(2, 9));
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(0, 5));
    }

    @Test
    void toStringTest(){
        assertEquals(state2.toString(), "1|1|1|1|1|1|1");
        assertEquals(state1.toString(), "O|O|O|O|O|O|O");
    }


}