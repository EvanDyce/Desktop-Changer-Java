package src.Java;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import java.io.FileInputStream;


public class Database {
    private final String url = "jdbc:postgresql://localhost/desktop_images";
    private final String user = "evan";
    private final String password = "SQLserver16";
    public Connection conn;
    private int counter = 0;

    public Database() {
        try {
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void Select() {
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM \"ImagesDB\"")) {

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int id = result.getInt("img_id");
                String title = result.getString("title");
                String theme = result.getString("theme");
                int image = result.getString("image").length();
                String time = result.getString("time");
                System.out.println(id);
                System.out.println(title);
                System.out.println(theme);
                System.out.println(image);
                System.out.println(time);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void Clear() {
        try (PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM \"ImagesDB\"")) {

            stmt.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void Insert(InsertValues value) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO \"ImagesDB\" VALUES (?,?,?,?,?)")) {

            stmt.setInt(1, this.counter);
            stmt.setString(2, value.getTitle());
            stmt.setString(3, value.getTheme());
            stmt.setBinaryStream(4, new FileInputStream(value.getImage()), value.getImage().length());
            stmt.setString(5, format.format(date));
            this.counter++;
            stmt.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String ChooseRandom() {

        String theme = null;
        String rs = null;

        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT title FROM (SELECT * FROM \"ImagesDB\" WHERE theme=?) AS hi ORDER BY RANDOM() LIMIT 1")) {

            if (LocalDate.now().getMonth() == Month.DECEMBER) {

                theme = "Christmas"; 

            } else if (LocalDate.now().getMonth() == Month.JANUARY && LocalDate.now().getDayOfMonth() == 1) {
                
                theme = "Birthday";

            } else if (LocalDate.now().getMonth() == Month.JANUARY && (LocalDate.now().getDayOfMonth() == 24 || LocalDate.now().getDayOfMonth() == 25 || LocalDate.now().getDayOfMonth() == 26 || LocalDate.now().getDayOfMonth() == 27 || LocalDate.now().getDayOfMonth() == 28 || LocalDate.now().getDayOfMonth() == 29 || LocalDate.now().getDayOfMonth() == 30 || LocalDate.now().getDayOfMonth() == 31)) {

                theme = "Halloween";
            } else {theme = "Normal";}


            stmt.setString(1, theme);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                
                rs = result.getString("title");
                return rs;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}

