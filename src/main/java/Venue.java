import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Venue {
  private int id;
  private String name;

  public Venue(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static List<Venue> all() {
    String sql = "SELECT * FROM venues";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Venue.class);
    }
  }

  @Override
  public boolean equals(Object otherVenue) {
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getName().equals(newVenue.getName()) &&
             this.getId() == newVenue.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO venues (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Venue find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues WHERE id=:id";
      Venue venue = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Venue.class);
    return venue;
    }
  }

  public void update(String newName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE venues SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", newName)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM venues WHERE id = :id";
        con.createQuery(deleteQuery)
          .addParameter("id", this.id)
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM bands_venues WHERE venue_id = :venueId";
        con.createQuery(joinDeleteQuery)
          .addParameter("venueId", this.id)
          .executeUpdate();
    }
  }

  public void addBand(Band band) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
        con.createQuery(sql)
          .addParameter("venue_id", this.getId())
          .addParameter("band_id", band.getId())
          .executeUpdate();
    }
  }

  public List<Band> getBands() {
    try(Connection con = DB.sql2o.open()) {
      String joinQuery = "SELECT bands.* FROM venues " +
      "JOIN bands_venues ON (venues.id = bands_venues.venue_id) " +
      "JOIN bands ON (bands_venues.band_id = bands.id) " +
      "WHERE venues.id = :venue_id";

      List<Band> bands = con.createQuery(joinQuery)
        .addParameter("venue_id", this.getId())
        .executeAndFetch(Band.class);

      return bands;
    }
  }

}
