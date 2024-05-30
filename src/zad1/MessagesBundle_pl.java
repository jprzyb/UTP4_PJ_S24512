package zad1;

import java.util.ListResourceBundle;

public class MessagesBundle_pl extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                { "lake", "jezioro" },
                { "mountains", "góry" },
                { "sea", "morze" }
        };
    }
}
