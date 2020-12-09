package src.Java;

import java.util.ArrayList;
import java.io.File;

public class Directory {
    private File root;
    private ArrayList<File> subDirs = new ArrayList<File>();
    private ArrayList<InsertValues> images = new ArrayList<InsertValues>();


    public Directory() {
        this.root = new File("Images/");
        this.listSubs(this.root);
        this.themesImages(this.subDirs);
    }

    public ArrayList<File> getSubs() {
        return this.subDirs;
    }

    public ArrayList<InsertValues> getImages() {
        return this.images;
    }

    public static void main(String[] args) {

        Directory test = new Directory();

        // ArrayList<ArrayList<Object>> images = test.themesImages(test.subDirs);

        for (InsertValues value : test.images) {
            System.out.println(value.getTitle() + " title");
            System.out.println(value.getTheme() + " theme");
            System.out.println(value.getImage() + " image");
        }
    }
    
    public ArrayList<File> listSubs(File dirname) {
        File[] fileList = dirname.listFiles();

        for (File file : fileList) {
            this.subDirs.add(file);
        }

        return this.subDirs;
    }

    public ArrayList<InsertValues> themesImages(ArrayList<File> dirs) {

        for (File sub : dirs) {
            File[] fileList = sub.listFiles();

            for (File image : fileList) {
                String title = image.toString().substring(sub.toString().length()+1, image.toString().length()-4);
                String theme = sub.toString().substring(7);
                InsertValues temp = new InsertValues(title, theme, image);
                this.images.add(temp);
            }
        }

        return this.images;
    }
}
