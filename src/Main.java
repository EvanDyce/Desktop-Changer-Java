package src;
import src.Java.*;


class Main {

    private static Database db = new Database();
    private static Directory dir = new Directory();
    private static ImageHandling handler = new ImageHandling();

    public static void main(String[] args) {
        
        db.Select();
        db.Clear();
        for (InsertValues values : dir.getImages()) {
            try {
                
                db.Insert(values);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        String hi = db.ChooseRandom();
        handler.getBin(hi, db);
        try {
            handler.binToImg(handler.getBin(hi, db));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        db.Select();
        new dllFuncs();
    }
}