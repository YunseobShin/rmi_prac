package ys;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculate extends Remote{
	int add(int a, int b, String sender) throws RemoteException;
}
