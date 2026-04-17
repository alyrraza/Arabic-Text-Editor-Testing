package dal;

import java.util.ArrayList;
import java.util.List;

import dto.Pages;

public class PaginationDAO {

    public static List<Pages> paginate(String fileContent) {
        
        int pageSize = 100;
        int pageNumber = 1;
        String pageContent = "";
        List<Pages> pages = new ArrayList<Pages>();

        // agar content null ya khali hai
        if (fileContent == null || fileContent.isEmpty()) {
            pages.add(new Pages(0, 0, pageNumber, pageContent.toString()));
            return pages;
        }

        // har character ek ek karke page mein add karo
        for (int i = 0; i < fileContent.length(); i++) {
            pageContent += fileContent.charAt(i);

            // agar page full ho gaya ya last character hai
            if (pageContent.length() == pageSize || i == fileContent.length() - 1) {
                pages.add(new Pages(0, 0, pageNumber, pageContent));
                pageNumber++;
                pageContent = "";
            }
        }
        return pages;
    }
}