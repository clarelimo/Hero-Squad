import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Hero;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/heroes/new",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));

            String[] powersAdd = request.queryMap().toMap().get("power");
            ArrayList<String> powers = new ArrayList<String>();
            for (int i=0;i<powersAdd.length;i++){
                powers.add(powersAdd[i]);
            }
            model.put("powers",powers);

            String[] weaknessAdd =  request.queryMap().toMap().get("weakness");
            ArrayList<String> weaknesses = new ArrayList<String>();
            for (String s:weaknessAdd){
                weaknesses.add(s);
            }
            model.put("weaknesses",weaknesses);

            Hero hero = new Hero(name,age,powers,weaknesses);

            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "heroes.hbs");
        },new HandlebarsTemplateEngine());

        get("/heroes/list",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            ArrayList<Hero> heroes = Hero.getAll();
            model.put("heroes",heroes);

            return new ModelAndView(model,"heroesList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/delete", (request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            Hero.clearAllHeros();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(request.params("id"));
            Hero foundHero = Hero.findById(idOfHeroToFind);
            model.put("hero",foundHero);
            return new ModelAndView(model,"heroesList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToEdit = Integer.parseInt(request.params("id"));

            Hero editHero = Hero.findById(idOfHeroToEdit);
            model.put("editHero", editHero);

            return  new ModelAndView(model, "heroes.hbs");

        }, new HandlebarsTemplateEngine());

        post("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("name");
            int newAge = Integer.parseInt(request.queryParams("age"));
            int idOfHeroToEdit = Integer.parseInt(request.params("id"));
            String[] powersAdd = request.queryMap().toMap().get("power");
            ArrayList<String> powers = new ArrayList<String>();
            for (int i=0;i<powersAdd.length;i++){
                powers.add(powersAdd[i]);
            }
            model.put("powers",powers);

            String[] weaknessAdd =  request.queryMap().toMap().get("weakness");
            ArrayList<String> weaknesses = new ArrayList<String>();
            for (String s:weaknessAdd){
                weaknesses.add(s);
            }
            Hero editHero = Hero.findById(idOfHeroToEdit);
            editHero.update(newName, newAge, powers, weaknesses);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(request.params("id"));

            Hero deleteHero = Hero.findById(idOfHeroToDelete);
            deleteHero.deleteHero();

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
