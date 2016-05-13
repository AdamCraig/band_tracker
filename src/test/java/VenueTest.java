import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Venue_instantiatesCorrectly_true() {
    Venue myVenue = new Venue("Rock Venue");
    assertEquals(true, myVenue instanceof Venue);
  }

  @Test
  public void getName_venueInstantiatesWithName_String() {
    Venue myVenue = new Venue("Pop Venue");
    assertEquals("Pop Venue", myVenue.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfVenuesAreTheSame_true() {
    Venue firstVenue = new Venue("Definite Similarities To That Other Venue");
    Venue secondVenue = new Venue("Definite Similarities To That Other Venue");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesVenueCorrectly_1() {
    Venue newVenue = new Venue("Madison Square Garden");
    newVenue.save();
    assertEquals(1, Venue.all().size());
  }

  @Test
  public void save_assignsIdToObject_ID() {
    Venue myVenue = new Venue("JJ's Diner");
    myVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(myVenue.getId(), savedVenue.getId());
  }

  @Test
  public void find_findsVenueInDatabase_true() {
    Venue myVenue = new Venue("Some Dive Bar");
    myVenue.save();
    Venue savedVenue = Venue.find(myVenue.getId());
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void update_updatesVenueName_true() {
    Venue myVenue = new Venue("TD Garden");
    myVenue.save();
    myVenue.update("DT Garden");
    assertEquals("DT Garden", Venue.find(myVenue.getId()).getName());
  }

  @Test
  public void delete_deletesVenueInOwnTableInDatabase_0() {
    Venue myVenue = new Venue("Giant Arena");
    myVenue.save();
    myVenue.delete();
    assertEquals(0, Venue.all().size());
  }

  @Test
  public void addBand_addsBandToVenue_true() {
    Venue myVenue = new Venue("Super Big Arena");
    myVenue.save();
    Band myBand = new Band("The Doors");
    myBand.save();
    myVenue.addBand(myBand);
    Band savedBand = myVenue.getBands().get(0);
    assertTrue(myBand.equals(savedBand));
  }
}
