package src.Java;

import java.io.File;

public class InsertValues {

    private String title;
    private String theme;
    private File image;

    public InsertValues(String title, String theme, File image) {
        this.title = title;
        this.theme = theme;
        this.image = image;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTheme() {
        return this.theme;
    }

    public File getImage() {
        return this.image;
    }
}