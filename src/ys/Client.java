package ys;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {
    	if(args.length < 1) {
    		System.err.println("invalid argument");
    		return;
    	} else {
    		String host = args[0];
    	}
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
	    String sender = (args.length < 2) ? null : args[1];
            String response = stub.sayHello(sender);
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
