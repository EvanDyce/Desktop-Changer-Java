package src.Java;

import java.sql.*;
import java.io.IOException;
import java.nio.file.*;

public class ImageHandling {   

    public byte[] getBin(String title, Database db) {

        byte[] imgBytes = null;

        try (PreparedStatement ps = db.conn.prepareStatement("SELECT image FROM \"ImagesDB\" WHERE title= ? ")) {
            
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()){
                    imgBytes = rs.getBytes(1);
                    return imgBytes;
                }
                rs.close();
            }
            ps.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return imgBytes;
    }

    public void binToImg (byte[] imgBytes) 
        throws IOException {

        Path path = Paths.get("current.jpg");
        
        Files.write(path, imgBytes);
    }
}
