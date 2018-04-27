package com.ts.previsao.tempo;

import static spark.Spark.get;

import com.ts.previsao.tempo.home.HomeController;

public class Main {

	public static void main(String[] args) {

		new HomeController().homeRouter();
		get("/hello", (req, res) -> "Hello World");
	}

}
