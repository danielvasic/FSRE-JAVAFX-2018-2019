package restoran.model;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Korisnik implements Model {
    private int id;
    private String ime;
    private String prezime;
    private String jmbg;
    private String email;

    private Korisnik () {}

    public Korisnik(int id, String ime, String prezime, String jmbg, String email) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void create() {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement(
                    "INSERT INTO korisnik VALUES (null, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmnt.setString(1, this.ime);
            stmnt.setString(2, this.prezime);
            stmnt.setString(3, this.jmbg);
            stmnt.setString(4, this.email);
            stmnt.executeUpdate();

            ResultSet generatedKeys = stmnt.getGeneratedKeys();
            generatedKeys.next();
            this.id = generatedKeys.getInt(1);
        } catch (SQLException e) {
            System.out.println("Greska prilikom stvaranja korisnika u bazi:"
                    + e.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement(
                    "UPDATE korisnik SET ime=?, prezime=?, email=?, jmbg=? WHERE id=?"
            );
            stmnt.setString(1, this.ime);
            stmnt.setString(2, this.prezime);
            stmnt.setString(3, this.email);
            stmnt.setString(4, this.jmbg);
            stmnt.setInt(5, this.id);
            stmnt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Greska prilikom brisanja korisnika iz baze:"
                    + e.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement(
                    "DELETE FROM korisnik WHERE id=?"
            );
            stmnt.setInt(1, this.id);
            stmnt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Greska prilikom brisanja korisnika iz baze:"
                    + e.getMessage());
        }
    }

    public static Korisnik read (int id) {
        PreparedStatement stmnt = null;
        try {
            stmnt = Database.CONNECTION.prepareStatement(
                    "SELECT * FROM korisnik WHERE id=?"
            );
            stmnt.setInt(1, id);
            ResultSet result = stmnt.executeQuery();
            result.next();
            return new Korisnik(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        } catch (SQLException e) {
            System.out.println("Greska kod dohvacanja korisnika iz baze:"
                    + e.getMessage());
            return null;
        }
    }

    public static List<Korisnik> readAll() {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement(
                    "SELECT * FROM korisnik"
            );
            List<Korisnik> korisnici = new ArrayList<Korisnik>();
            ResultSet result = stmnt.executeQuery();
            while (result.next()) {
                korisnici.add(new Korisnik(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5)
                ));
            }
            return korisnici;
        } catch (SQLException e) {
            System.out.println("Greska kod dohvacanja korisnika iz baze:"
                    + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", jmbg='" + jmbg + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
