package com.ts.previsao.tempo.home;

import static spark.Spark.get;

public class HomeController {

    public void homeRouter() {
        get("/", (req, res) -> {
            res.type("application/json");
            return new HomeModel().info();
        });
    }

}
