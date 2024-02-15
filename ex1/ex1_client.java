import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ex1_client {
    public static void main(String argv[]) {
        int port = 0; 
        String host = ""; 
        Scanner clavier = new Scanner(System.in); 
        
        // Demande à l'utilisateur d'entrer le nom du serveur
        System.out.print("Nom du serveur : ");
        host = clavier.next(); // Lecture du nom du serveur entré par l'utilisateur
        System.out.print("Port du serveur : ");
        
        try {
            port = clavier.nextInt(); 
        } catch (NumberFormatException e) { 
            System.err.println("Le second paramètre n'est pas un entier."); 
            System.exit(-1); 
        }
        // gestion des opérations spécifiques de communication avec le serveur
        try {
            // Résolution de l'adresse IP du serveur à partir du nom de l'hôte
            InetAddress adr = InetAddress.getByName(host);
            
            // Création d'une socket et connexion au serveur spécifié
            Socket socket = new Socket(adr, port);
            
            // Création des flux d'entrée et de sortie pour communiquer avec le serveur
            ObjectOutputStream sortie = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entree = new ObjectInputStream(socket.getInputStream());
            
            // Envoi d'un message au serveur
            sortie.writeObject(new String("ma première socket"));
            
            // Lecture de la réponse du serveur
            String chaine = (String) entree.readObject();
            System.out.println(" reçu du serveur : " + chaine);
        } catch (Exception e) { 
            System.err.println("Erreur : " + e); 
        }
    }
}


