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

        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "heroes.hbs");
        },new HandlebarsTemplateEngine());

        post("/heroes/new",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
//            model.put("name",request.session().attribute("name"));
//            model.put("age",request.session().attribute("age"));

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

        get("/heroes/list",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            ArrayList<Hero> heroes = Hero.getAll();
            model.put("heroes",heroes);

            return new ModelAndView(model,"heroesList.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
