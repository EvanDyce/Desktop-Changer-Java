package src;
import src.Java.*;


class Main {

    private static Database db = new Database();
    private static Directory dir = new Directory();
    private static ImageHandling handler = new ImageHandling();

    public static void main(String[] args) {
        
        // clears the database and then re inserts the image files along with any new ones that have been added
        db.Clear();
        for (InsertValues values : dir.getImages()) {
            try {
                
                db.Insert(values);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        // gets random image title from database
        String image = db.ChooseRandom();
        // converts bytea value into byte array and writes the file into current.jpg
        handler.getBin(image, db);
        try {
            handler.binToImg(handler.getBin(image, db));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // creates instance calls constructor which actually switches the desktop image
        new dllFuncs();
    }
}