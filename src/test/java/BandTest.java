import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Band_instantiatesCorrectly_true() {
    Band myBand = new Band("Rock Band");
    assertEquals(true, myBand instanceof Band);
  }

  @Test
  public void getName_bandInstantiatesWithName_String() {
    Band myBand = new Band("Pop Band");
    assertEquals("Pop Band", myBand.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBandsAreTheSame_true() {
    Band firstBand = new Band("Eerie Similarities To That Other Band");
    Band secondBand = new Band("Eerie Similarities To That Other Band");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesBandCorrectly_1() {
    Band newBand = new Band("Journey");
    newBand.save();
    assertEquals(1, Band.all().size());
  }

  @Test
  public void save_assignsIdToObject_ID() {
    Band myBand = new Band("Boy Band");
    myBand.save();
    Band savedBand = Band.all().get(0);
    assertEquals(myBand.getId(), savedBand.getId());
  }

  @Test
  public void find_findsBandInDatabase_true() {
    Band myBand = new Band("Boston");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void update_updatesBandName_true() {
    Band myBand = new Band("KISS");
    myBand.save();
    myBand.update("HUG");
    assertEquals("HUG", Band.find(myBand.getId()).getName());
  }

  @Test
  public void delete_deletesBandInOwnTableInDatabase_0() {
    Band myBand = new Band("ZZ Top");
    myBand.save();
    myBand.delete();
    assertEquals(0, Band.all().size());
  }
}
