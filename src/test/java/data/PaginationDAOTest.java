package data;

import dal.PaginationDAO;
import dto.Pages;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PaginationDAOTest {

    // ============================================
    // TEST 1 — Negative Test
    // Path p1: null content → ek khali page return honi chahiye
    // ============================================
    @Test
    public void testPaginate_NullContent_ShouldReturnOneEmptyPage() {
        // Action — null content do
        List<Pages> result = PaginationDAO.paginate(null);

        // Assert — ek page hona chahiye
        // assertEquals(expected, actual) — dono equal hone chahiye
        assertEquals(1, result.size());

        // Assert — us page ka content khali hona chahiye
        assertEquals("", result.get(0).getPageContent());
    }

    // ============================================
    // TEST 2 — Negative Test
    // Path p2: empty string → ek khali page return honi chahiye
    // ============================================
    @Test
    public void testPaginate_EmptyString_ShouldReturnOneEmptyPage() {
        // Action — khali string do
        List<Pages> result = PaginationDAO.paginate("");

        // Assert — ek page hona chahiye
        assertEquals(1, result.size());

        // Assert — content khali hona chahiye
        assertEquals("", result.get(0).getPageContent());
    }

    // ============================================
    // TEST 3 — Positive Test
    // Path p3: content 100 se kam → sirf ek page
    // ============================================
    @Test
    public void testPaginate_ContentLessThan100Chars_ShouldReturnOnePage() {
        // Setup — 50 characters ka content
        // "a".repeat(50) matlab 50 baar "a" — "aaaaaa...50 times"
        String content = "a".repeat(50);

        // Action
        List<Pages> result = PaginationDAO.paginate(content);

        // Assert — sirf ek page hona chahiye
        assertEquals(1, result.size());

        // Assert — content same hona chahiye
        assertEquals(content, result.get(0).getPageContent());
    }

    // ============================================
    // TEST 4 — Boundary Test
    // Path p4: content exactly 100 chars → sirf ek page
    // ============================================
    @Test
    public void testPaginate_ContentExactly100Chars_ShouldReturnOnePage() {
        // Setup — exactly 100 characters
        // Yeh boundary test hai — bilkul limit pe
        String content = "a".repeat(100);

        // Action
        List<Pages> result = PaginationDAO.paginate(content);

        // Assert — exactly ek page hona chahiye
        assertEquals(1, result.size());

        // Assert — page ka content exactly 100 chars hona chahiye
        assertEquals(100, result.get(0).getPageContent().length());
    }

    // ============================================
    // TEST 5 — Positive Test
    // Path p5: content 100 se zyada → multiple pages
    // ============================================
    @Test
    public void testPaginate_ContentMoreThan100Chars_ShouldReturnMultiplePages() {
        // Setup — 250 characters ka content
        // 250 chars → 3 pages hone chahiye
        // Page 1: 100 chars
        // Page 2: 100 chars
        // Page 3: 50 chars
        String content = "a".repeat(250);

        // Action
        List<Pages> result = PaginationDAO.paginate(content);

        // Assert — 3 pages hone chahiye
        assertEquals(3, result.size());

        // Assert — pehla page 100 chars ka hona chahiye
        assertEquals(100, result.get(0).getPageContent().length());

        // Assert — doosra page 100 chars ka hona chahiye
        assertEquals(100, result.get(1).getPageContent().length());

        // Assert — teesra page 50 chars ka hona chahiye
        assertEquals(50, result.get(2).getPageContent().length());
    }

    // ============================================
    // TEST 6 — Positive Test
    // Page numbers sahi hone chahiye
    // ============================================
    @Test
    public void testPaginate_PageNumbers_ShouldBeSequential() {
        // Setup — 200 chars → 2 pages
        String content = "b".repeat(200);

        // Action
        List<Pages> result = PaginationDAO.paginate(content);

        // Assert — 2 pages
        assertEquals(2, result.size());

        // Assert — pehle page ka number 1 hona chahiye
        assertEquals(1, result.get(0).getPageNumber());

        // Assert — doosre page ka number 2 hona chahiye
        assertEquals(2, result.get(1).getPageNumber());
    }
}