
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ex1_serveur {
    public static void main(String argv[]) {
        int port = 0; 
        Scanner clavier = new Scanner(System.in);
        
        // Demander d'entrer le numéro de port d’écoute
        System.out.print("Port d'écoute : ");
        try {
            port = clavier.nextInt();
        } catch (NumberFormatException e) { 
            System.err.println("Le paramètre n'est pas un entier."); 
              System.err.println("Usage : java ServeurUDP   port-serveur");
             System.exit(-1); 
        }
        
        try {
            // Création d'un objet ServerSocket lié au port spécifié
            ServerSocket socketServeur = new ServerSocket(port);
            
            // Accepte les connexions entrantes des clients
            Socket socket = socketServeur.accept();
            
            // Création des flux d'entrée et de sortie pour communiquer avec le client
            ObjectOutputStream sortie = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entree = new ObjectInputStream(socket.getInputStream());
            
            // Lecture du message envoyé par le client
            String chaine = (String) entree.readObject();
            System.out.println(" reçu : " + chaine); 
            
            // Affichage des informations sur l'adresse et le port du client
            System.out.println(" Cela vient de : " + socket.getInetAddress() + ":" + socket.getPort());
            
            // Envoi d'un message de réponse au client
            sortie.writeObject(new String("bien reçu"));
            
            
        } catch (Exception e) { 
            System.err.println("Erreur : " + e); 
        }
    }
}
