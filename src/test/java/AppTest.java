import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Rock The World!");
  }

  @Test
  public void bandIsCreatedAndDisplayedTest() {
    goTo("http://localhost:4567/");
    fill("#band").with("Aerosmith");
    submit("#bandSubmit");
    assertThat(pageSource()).contains("Aerosmith");
  }

  @Test
  public void bandPageIsDisplayedTest() {
    Band testBand = new Band("The 1975");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    assertThat(pageSource()).contains("The 1975");
  }

  @Test
  public void bandIsDeleted() {
    Band testBand = new Band("A Bad Band");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    submit("#delete-band");
    assertFalse(pageSource().contains("A Bad Band"));
  }

  @Test
  public void bandNameIsUpdated() {
    Band testBand = new Band("Panic At The Disco");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    fill("#update").with("Panic! At The Disco");
    submit("#update-submit");
    assertThat(pageSource().contains("Panic! At The Disco"));
  }

  @Test
  public void newVenueIsAddedToBand() {
    Band testBand = new Band("A Good Band");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    fill("#venue").with("A Good Venue");
    submit("#venue-submit");
    assertThat(pageSource().contains("A Good Venue"));
  }

  @Test
  public void venuesPageIsDisplayedTest() {
    Venue testVenue = new Venue("Another Venue");
    testVenue.save();
    goTo("http://localhost:4567/venues");
    assertThat(pageSource()).contains("Another Venue");
  }

  @Test
  public void venueIsCreatedAndDisplayedTest() {
    goTo("http://localhost:4567/venues");
    fill("#new-venue").with("Super Arena");
    submit("#venueSubmit");
    assertThat(pageSource()).contains("Super Arena");
  }

  @Test
  public void venuePageIsDisplayedTest() {
    Venue testVenue = new Venue("Ultradome");
    testVenue.save();
    String url = String.format("http://localhost:4567/venues/%d", testVenue.getId());
    goTo(url);
    assertThat(pageSource()).contains("Ultradome");
  }

  @Test
  public void venueIsDeleted() {
    Venue testVenue = new Venue("A Bad Venue");
    testVenue.save();
    String url = String.format("http://localhost:4567/venues/%d", testVenue.getId());
    goTo(url);
    submit("#delete-venue");
    assertFalse(pageSource().contains("A Bad Venue"));
  }

  @Test
  public void venueNameIsUpdated() {
    Venue testVenue = new Venue("Test Arena");
    testVenue.save();
    String url = String.format("http://localhost:4567/venues/%d", testVenue.getId());
    goTo(url);
    fill("#updateVenue").with("Super Test Arena");
    submit("#update-submit");
    assertThat(pageSource().contains("Super Test Arena"));
  }

  @Test
  public void newBandIsAddedToVenue() {
    Venue testVenue = new Venue("Fenway Park");
    testVenue.save();
    String url = String.format("http://localhost:4567/venues/%d", testVenue.getId());
    goTo(url);
    fill("#band").with("The Boston Red Sox");
    submit("#band-submit");
    assertThat(pageSource().contains("The Boston Red Sox"));
  }

}
