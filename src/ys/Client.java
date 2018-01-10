package ys;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void lookupHello(String name, String host, String sender) {
    	try {	
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup(name);
            String response = stub.sayHello(sender);
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public static void lookupAdd(String name, String host, int a, int b, String sender) {
    	try {	
            Registry registry = LocateRegistry.getRegistry(host);
            Calculate stub = (Calculate) registry.lookup(name);
            int response = stub.add(a, b, sender);
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
    	if(args.length < 1) {
    		System.err.println("invalid argument");
    		return;
    	} else {
    		String host = args[0];
        	String sender = (args.length < 2) ? null : args[1];
        	String name = (args.length < 3) ? null : args[2];
        	switch (name) {
			case "Hello":
				lookupHello(name, host, sender);
				break;
			case "Cal":
				lookupAdd(name, host, Integer.parseInt(args[3]), Integer.parseInt(args[4]), sender);
				break;
			default:
				System.err.println("invalid function name");
			}
        	
        	
    	}
    }
}
