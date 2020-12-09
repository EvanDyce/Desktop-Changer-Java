package src.Java;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import java.io.FileInputStream;


public class Database {
    private final String url = "LINK TO SQL SERVER USING JDBC";
    private final String user = "SQL USER";
    private final String password = "SQL PASSWORD";
    public Connection conn;
    private int counter = 0;

    public Database() {

        // Constructor makes connection to the SQL database when new object is initialized
        try {
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Select function is just for checking what is in the database
    public void Select() {
        // makes a prepared selection statement
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM \"ImagesDB\"")) {

            ResultSet result = stmt.executeQuery();

            // iterates through the results of the query and prints to the console
            while (result.next()) {
                int id = result.getInt("img_id");
                String title = result.getString("title");
                String theme = result.getString("theme");
                String time = result.getString("time");
                System.out.println(id);
                System.out.println(title);
                System.out.println(theme);
                System.out.println(time);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    // removes every thing from the database
    // i call this at the beginning of main function before inserting new values
    // not the most efficient, but because the db is small it isn't a big deal
    public void Clear() {
        try (PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM \"ImagesDB\"")) {

            stmt.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Insert function loads values into the database
    // takes parameter of one InsertValues type
    public void Insert(InsertValues value) {

        // formatting for the data insertion into db
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        try (PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO \"ImagesDB\" VALUES (?,?,?,?,?)")) {

            // sets the query values to the values of the InsertValues object
            stmt.setInt(1, this.counter);
            stmt.setString(2, value.getTitle());
            stmt.setString(3, value.getTheme());
            // takes the image from the value and makes into binary file stream that sql can use
            stmt.setBinaryStream(4, new FileInputStream(value.getImage()), value.getImage().length());
            stmt.setString(5, format.format(date));
            // uses a counter to keep track of primary key values
            this.counter++;
            stmt.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // chooses an image from the database based on the date
    // can add anything else here to limit the range of pictures it chooses from
    public String ChooseRandom() {

        String theme = null;
        String rs = null;

        // selects random row from db based off of the theme value set below
        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT title FROM (SELECT * FROM \"ImagesDB\" WHERE theme=?) AS temp ORDER BY RANDOM() LIMIT 1")) {

            // if it's december uses christmas backgrounds
            if (LocalDate.now().getMonth() == Month.DECEMBER) {

                theme = "Christmas"; 

            } else if (LocalDate.now().getMonth() == Month.JANUARY && LocalDate.now().getDayOfMonth() == 1) {
                
                theme = "Birthday";

            } else if (LocalDate.now().getMonth() == Month.JANUARY && (LocalDate.now().getDayOfMonth() == 24 || LocalDate.now().getDayOfMonth() == 25 || LocalDate.now().getDayOfMonth() == 26 || LocalDate.now().getDayOfMonth() == 27 || LocalDate.now().getDayOfMonth() == 28 || LocalDate.now().getDayOfMonth() == 29 || LocalDate.now().getDayOfMonth() == 30 || LocalDate.now().getDayOfMonth() == 31)) {

                theme = "Halloween";

            } else {theme = "Normal";}

            // sets query value to the theme
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

