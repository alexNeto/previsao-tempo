package com.ts.previsao.tempo;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        get("/", (req, res) -> "digite /hello para ir para o hello");
        get("/hello", (req, res) -> "Hello World");
    }

}
