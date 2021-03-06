import java.util.Map;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      List<Band> allBands = Band.all();
      model.put("allBands", allBands);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      String band = request.queryParams("band");
      Band newBand = new Band(band);
      newBand.save();
      response.redirect("/");
      return null;
    });

    get("/bands/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      model.put("band", band);
      model.put("venues", band.getVenues());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/:id/delete", (request, response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      Band band = Band.find(bandId);
      band.delete();
      response.redirect("/");
      return null;
    });

    post("/bands/:id/update", (request, response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      Band band = Band.find(bandId);
      String newName = request.queryParams("update");
      band.update(newName);
      response.redirect("/bands/" + band.getId());
      return null;
    });

    post("/bands/:id/venue/new", (request, response) -> {
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      String venue = request.queryParams("venue");
      List<Venue> allVenues = Venue.all();
      Venue newVenue = new Venue(venue);
      boolean matchFound = false;

      for(Venue oldVenue : allVenues) {
        if (newVenue.getName().equals(oldVenue.getName())) {
          band.addVenue(oldVenue);
          matchFound = true;
          break;
        }
      }

      if (matchFound == false) {
        newVenue.save();
        band.addVenue(newVenue);
      }

      response.redirect("/bands/" + band.getId());
      return null;
    });

    get("/venues", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      List<Venue> allVenues = Venue.all();
      model.put("allVenues", allVenues);
      model.put("template", "templates/venues.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venues", (request, response) -> {
      String venue = request.queryParams("venue");
      Venue newVenue = new Venue(venue);
      newVenue.save();
      response.redirect("/venues");
      return null;
    });

    get("/venues/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Venue venue = Venue.find(Integer.parseInt(request.params(":id")));
      model.put("venue", venue);
      model.put("bands", venue.getBands());
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venues/:id/delete", (request, response) -> {
      int venueId = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(venueId);
      venue.delete();
      response.redirect("/venues");
      return null;
    });

    post("/venues/:id/update", (request, response) -> {
      int venueId = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(venueId);
      String newName = request.queryParams("updateVenue");
      venue.update(newName);
      response.redirect("/venues/" + venue.getId());
      return null;
    });

    post("/venues/:id/band/new", (request, response) -> {
      Venue venue = Venue.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("band");
      List<Band> allBands = Band.all();
      Band newBand = new Band(name);
      boolean matchFound = false;

      for(Band oldBand : allBands) {
        if (newBand.getName().equals(oldBand.getName())) {
          venue.addBand(oldBand);
          matchFound = true;
          break;
        }
      }

      if (matchFound == false) {
        newBand.save();
        venue.addBand(newBand);
      }

      response.redirect("/venues/" + venue.getId());
      return null;
    });

  }
}
