package com.ts.previsao.tempo;

import static spark.Spark.port;

import com.ts.previsao.tempo.home.HomeController;

public class Main {

	public static void main(String[] args) {
		port(getHerokuAssignedPort());

		new HomeController().homeRouter();
	}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567; 
	}
}
