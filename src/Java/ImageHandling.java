package src.Java;

import java.sql.*;
import java.io.IOException;
import java.nio.file.*;

public class ImageHandling {   

    public byte[] getBin(String title, Database db) {

        byte[] imgBytes = null;

        // gets the image associated with the title passed as string
        try (PreparedStatement ps = db.conn.prepareStatement("SELECT image FROM \"ImagesDB\" WHERE title= ? ")) {
            
            // sets value and executes query
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()){
                    // gets the bytes from the sql bytea type
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

    // given array of bytes writes to the current.jpg file
    public void binToImg (byte[] imgBytes) 
        throws IOException {

        Path path = Paths.get("current.jpg");
        
        Files.write(path, imgBytes);
    }
}
