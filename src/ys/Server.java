package ys;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {

    public Server() {}

    public String sayHello(String sender) {
    	String req = sender == null ? "unknwon" : sender;
    	System.err.println(req + " is requesting...");
        	return "Hello " + req + "!";
    }
    
    

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            String host = (args.length < 1) ? "127.0.0.1" : args[0];
            System.setProperty("java.rmi.server.hostname", host);
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            
            registry.bind("Hello", stub);

            System.err.println("Server Ready. \nHostname: " + host);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
