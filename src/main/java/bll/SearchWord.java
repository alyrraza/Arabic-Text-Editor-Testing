package bll;

import java.util.ArrayList;
import java.util.List;

import dto.Documents;
import dto.Pages;

public class SearchWord {
    public static List<String> searchKeyword(String keyword, List<Documents> docs) {
        
        // keyword check — 3 letters se kam nahi hona chahiye
        List<String> getFiles = new ArrayList<>();
        if (keyword.length() < 3) {
            throw new IllegalArgumentException(
                "Could not Search, Please Enter at least 3 letter to search"
            );
        }

        // har document mein search karo
        for (Documents doc : docs) {
            for (Pages page : doc.getPages()) {
                String pageContent = page.getPageContent();
                
                // agar page mein keyword hai
                if (pageContent.contains(keyword)) {
                    String[] words = pageContent.split("\\s+");

                    // exact word match dhundho
                    for (int i = 0; i < words.length; i++) {
                        if (words[i].equalsIgnoreCase(keyword)) {
                            
                            // prefix word — keyword se pehle wala word
                            String prefixWord;
                            if (i > 0) {
                                prefixWord = words[i - 1];
                            } else {
                                prefixWord = "";
                            }
                            getFiles.add(
                                doc.getName() + " - " + prefixWord + " " + keyword + "..."
                            );
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return getFiles;
    }
}