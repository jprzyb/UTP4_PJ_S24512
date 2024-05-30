package zad1;

import java.util.ListResourceBundle;

public class MessagesBundle_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                { "jezioro", "lake" },
                { "morze", "sea" },
                { "góry", "mountains" },
        };
    }
}
