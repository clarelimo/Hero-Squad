import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/heros/new",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            model.put("name",request.session().attribute("name"));
            model.put("age",request.session().attribute("age"));
            String[] powersAdd = request.session().attribute("power");
            ArrayList<String> powers = new ArrayList<String>();
            for (int i=0;i<powersAdd.length;i++){
                powers.add(powersAdd[i]);
            }
            model.put("powers",powers);

            String[] weaknessAdd = request.session().attribute("weakness");
            ArrayList<String> weaknesses = new ArrayList<String>();
            for (int i=0;i<weaknessAdd.length;i++){
                weaknesses.add(weaknessAdd[i]);
            }
            model.put("weaknesses",weaknesses);

           return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
