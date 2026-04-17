package business;

import bll.SearchWord;
import dto.Documents;
import dto.Pages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SearchWordTest {

    // yeh variables har test mein use honge
    private List<Documents> docs;
    private List<Pages> pages;

    @BeforeEach
    public void setUp() {
        // har test se pehle fresh data banao
        // jaise factory mein har test se pehle machine reset karna
        docs = new ArrayList<>();
        pages = new ArrayList<>();
    }

    // ============================================
    // TEST 1 — Negative Test
    // Path p1: keyword 2 letters → exception aani chahiye
    // ============================================
    @Test
    public void testSearchKeyword_TwoLetterKeyword_ShouldThrowException() {
        // Setup — 2 letter keyword
        String keyword = "ab";

        // Action + Assert — exception expect kar rahe hain
        // assertThrows matlab: "mujhe expect hai ke yeh exception aayegi"
        // agar exception nahi aayi toh test FAIL
        assertThrows(
            IllegalArgumentException.class,
            () -> SearchWord.searchKeyword(keyword, docs)
        );
    }

    // ============================================
    // TEST 2 — Negative Test
    // Path p2: docs list bilkul khali → empty result
    // ============================================
    @Test
    public void testSearchKeyword_EmptyDocsList_ShouldReturnEmptyList() {
        // Setup — valid keyword lekin docs khali hain
        String keyword = "hello";
        // docs already khali hai BeforeEach se

        // Action
        List<String> result = SearchWord.searchKeyword(keyword, docs);

        // Assert — result khali honi chahiye
        // assertTrue matlab: "yeh condition true honi chahiye"
        assertTrue(result.isEmpty());
    }

    // ============================================
    // TEST 3 — Boundary Test
    // Path p3: keyword exactly 3 letters → valid, exception nahi
    // ============================================
    @Test
    public void testSearchKeyword_ExactlyThreeLetters_ShouldNotThrowException() {
        // Setup — exactly 3 letter keyword
        String keyword = "ali";

        // Action + Assert — koi exception nahi aani chahiye
        // assertDoesNotThrow matlab: "yeh code bina error ke chalna chahiye"
        assertDoesNotThrow(
            () -> SearchWord.searchKeyword(keyword, docs)
        );
    }

    // ============================================
    // TEST 4 — Positive Test
    // Path p4: keyword page mein hai → result return hona chahiye
    // ============================================
    @Test
    public void testSearchKeyword_KeywordFoundInPage_ShouldReturnResult() {
        // Setup — ek document banao jisme keyword hai
        String keyword = "hello";

        // Page banao content ke saath
        Pages page = new Pages(1, 1, 1, "world hello there");
        pages.add(page);

        // Document banao us page ke saath
        Documents doc = new Documents(1, "testFile.txt", "abc123",
                "2026-01-01", "2026-01-01", pages);
        docs.add(doc);

        // Action
        List<String> result = SearchWord.searchKeyword(keyword, docs);

        // Assert — result mein kuch hona chahiye
        // assertFalse(result.isEmpty()) matlab: result khali nahi honi chahiye
        assertFalse(result.isEmpty());
    }

    // ============================================
    // TEST 5 — Negative Test
    // Path p5: keyword page mein nahi → empty result
    // ============================================
    @Test
    public void testSearchKeyword_KeywordNotFound_ShouldReturnEmptyList() {
        // Setup — keyword page mein hai hi nahi
        String keyword = "xyz";

        Pages page = new Pages(1, 1, 1, "hello world java");
        pages.add(page);

        Documents doc = new Documents(1, "testFile.txt", "abc123",
                "2026-01-01", "2026-01-01", pages);
        docs.add(doc);

        // Action
        List<String> result = SearchWord.searchKeyword(keyword, docs);

        // Assert — empty honi chahiye
        assertTrue(result.isEmpty());
    }

    // ============================================
    // TEST 6 — Boundary Test
    // Path p6: keyword pehla word hai (i=0) → prefix empty hona chahiye
    // ============================================
    @Test
    public void testSearchKeyword_KeywordAtFirstPosition_ShouldHaveEmptyPrefix() {
        // Setup — keyword page ka pehla word hai
        String keyword = "hello";

        Pages page = new Pages(1, 1, 1, "hello world there");
        pages.add(page);

        Documents doc = new Documents(1, "testFile.txt", "abc123",
                "2026-01-01", "2026-01-01", pages);
        docs.add(doc);

        // Action
        List<String> result = SearchWord.searchKeyword(keyword, docs);

        // Assert — result mein prefix khali honi chahiye
        // " hello..." mein space ke baad seedha keyword
        assertFalse(result.isEmpty());
        assertTrue(result.get(0).contains(" hello..."));
    }

    // ============================================
    // TEST 7 — Positive Test
    // Path p7: keyword middle mein → prefix word hona chahiye
    // ============================================
    @Test
    public void testSearchKeyword_KeywordWithPrefix_ShouldIncludePrefixWord() {
        // Setup — keyword middle mein hai, pehle "world" hai
        String keyword = "hello";

        Pages page = new Pages(1, 1, 1, "world hello there");
        pages.add(page);

        Documents doc = new Documents(1, "testFile.txt", "abc123",
                "2026-01-01", "2026-01-01", pages);
        docs.add(doc);

        // Action
        List<String> result = SearchWord.searchKeyword(keyword, docs);

        // Assert — result mein "world" prefix hona chahiye
        assertFalse(result.isEmpty());
        assertTrue(result.get(0).contains("world hello..."));
    }
}