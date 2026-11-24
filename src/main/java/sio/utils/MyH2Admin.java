package sio.utils;
import org.h2.tools.Server;

public class MyH2Admin {

	public static void main(String[] args) {
		try {
			// Création du serveur Web H2 en mode TCP
			Server serverWeb;
			// Connection au serveur pour requêtes...
			serverWeb = Server.
					createWebServer("-web",
							"-webAllowOthers",
							"-webPort","8082");
			serverWeb.start();
			System.
			out.println("Serveur web pour H2-console démarré.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
