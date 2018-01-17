package ys;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello,Calculate {

    public Server() {}
    
    @Override
    public String sayHello(String sender) {
    	String req = sender == null ? "unknwon" : sender;
    	System.err.println(req + " is requesting hello...");
        	return "Hello " + req + "!";
    }
    @Override
	public int add(int a, int b, String sender) throws RemoteException {
    	String req = sender == null ? "unknwon" : sender;
    	System.err.println(req + " is requesting calculation add...");
		return a+b;
	}

    public static void rmiBindHello(String name, String host) {
    	try {
    		Server objHello = new Server();
        	Hello stubHello = (Hello) UnicastRemoteObject.exportObject(objHello, 0);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind(name, stubHello);
            System.err.println("Server Ready. \nHostname: " + host);
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
		}
    	
    }
    
    public static void rmiBindAdd(String name, String host) {
    	try {
    		Server objCal = new Server();
    		Calculate stubCal = (Calculate) UnicastRemoteObject.exportObject(objCal, 0);
    		Registry registry = LocateRegistry.getRegistry();
    		registry.rebind(name, stubCal);
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
		}
    }
    
    public static void main(String args[]) {
        String host = (args.length < 1) ? "127.0.0.1" : args[0];
        System.setProperty("java.rmi.server.hostname", host);
        rmiBindHello("Hello", host);
        rmiBindAdd("Add", host);
    }

	
}
