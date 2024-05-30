package zad1;

import java.util.ListResourceBundle;

public class MessagesBundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                { "jezioro", "lake" },
                { "morze", "sea" },
                { "góry", "mountains" },
                { "lake", "jezioro" },
                { "mountains", "góry" },
                { "sea", "morze" }
        };
    }
}
