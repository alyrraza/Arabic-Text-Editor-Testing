package data;

import dal.HashCalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashCalculatorTest {

    // ============================================
    // TEST 1 — Positive Test
    // Same input → same hash hamesha
    // ============================================
    @Test
    public void testCalculateHash_SameInput_ShouldReturnSameHash() throws Exception {
        // Setup
        String content = "Hello World";

        // Action — same content do do baar
        String hash1 = HashCalculator.calculateHash(content);
        String hash2 = HashCalculator.calculateHash(content);

        // Assert — dono hash same hone chahiye
        // Jaise same insaan ki fingerprint hamesha same hoti hai
        assertEquals(hash1, hash2);
    }

    // ============================================
    // TEST 2 — Positive Test
    // Hash hamesha 32 characters ka hona chahiye (MD5)
    // ============================================
    @Test
    public void testCalculateHash_ShouldReturn32CharacterHash() throws Exception {
        // Setup
        String content = "Test content";

        // Action
        String hash = HashCalculator.calculateHash(content);

        // Assert — MD5 hash hamesha 32 characters ka hota hai
        assertEquals(32, hash.length());
    }

    // ============================================
    // TEST 3 — Positive Test
    // Alag input → alag hash
    // ============================================
    @Test
    public void testCalculateHash_DifferentInput_ShouldReturnDifferentHash() throws Exception {
        // Setup — do alag contents
        String content1 = "Hello World";
        String content2 = "Hello World!"; // sirf ek ! add kiya

        // Action
        String hash1 = HashCalculator.calculateHash(content1);
        String hash2 = HashCalculator.calculateHash(content2);

        // Assert — hash alag hone chahiye
        // assertNotEquals matlab: dono equal nahi hone chahiye
        assertNotEquals(hash1, hash2);
    }

    // ============================================
    // TEST 4 — Positive Test
    // Hash mein sirf uppercase letters aur numbers hone chahiye
    // ============================================
    @Test
    public void testCalculateHash_ShouldReturnUpperCaseHexString() throws Exception {
        // Setup
        String content = "Arabic Text";

        // Action
        String hash = HashCalculator.calculateHash(content);

        // Assert — hash uppercase hex hona chahiye
        // matches("[A-F0-9]+") matlab: sirf A-F aur 0-9 characters
        assertTrue(hash.matches("[A-F0-9]+"));
    }

    // ============================================
    // TEST 5 — Boundary Test
    // Khali string ka bhi hash banana chahiye
    // ============================================
    @Test
    public void testCalculateHash_EmptyString_ShouldReturnValidHash() throws Exception {
        // Setup — khali string
        String content = "";

        // Action
        String hash = HashCalculator.calculateHash(content);

        // Assert — hash null nahi hona chahiye
        assertNotNull(hash);

        // Assert — 32 characters ka hona chahiye
        assertEquals(32, hash.length());
    }

    // ============================================
    // TEST 6 — Positive Test
    // File edit karne ke baad hash badalna chahiye
    // Yeh assignment ki exact requirement hai
    // ============================================
    @Test
    public void testCalculateHash_EditedContent_ShouldChangHash() throws Exception {
        // Setup — original content
        String originalContent = "Yeh mera original document hai";

        // Hash calculate karo import ke waqt
        String importHash = HashCalculator.calculateHash(originalContent);

        // Ab content edit kiya
        String editedContent = "Yeh mera edited document hai";

        // Hash calculate karo edit ke baad
        String editedHash = HashCalculator.calculateHash(editedContent);

        // Assert — dono hash alag hone chahiye
        // Original hash preserve hona chahiye — edit se change hona chahiye
        assertNotEquals(importHash, editedHash);
    }
}