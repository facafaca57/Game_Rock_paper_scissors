import java.io.*;
import java.net.*;

public class client {
	static Socket fromserver = null;
    static String host = "localhost";
    static int    port = 4444;
	
  public static void main(String[] args) throws IOException {

    if (host=="") {
      System.out.println("Nu este hostul");
      System.exit(-1);
    }
	System.out.println("Connecting..");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    try {
    	fromserver = new Socket(host,port);
    	System.out.println("Server Connected");
    }catch(IOException e){
    	System.out.println("Serverul nu raspunde..");
    	System.exit(-1);
    }
    BufferedReader in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
    PrintWriter    out = new PrintWriter(fromserver.getOutputStream(),true);
    
    BufferedReader l = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader L = new BufferedReader(new InputStreamReader(System.in));
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    String fuser, fserver;

    System.out.println("Alegeti Figura: \n 1 - Foarfece \n 2 - Hartie \n 3 - Piatra");
    
    while ((fuser = l.readLine()) != null) {
    	try {
    		if(fuser.equalsIgnoreCase("exit")) {
        		out.println(fuser);
        		System.exit(-1);
        	} else if(fuser.length() == 0 || Integer.parseInt(fuser) > 3) {
        		System.out.println("Alegeti dintre aceste 3 puncte!");
        	} else {
        		out.println(fuser);

        		String thisLine = null;
        		int i = 0;
        		while ((thisLine = in.readLine()) != null) {
        			System.out.println(thisLine);
        			i++;
        			if(i == 2) { break; }
        		}
        	}
		}
		catch(Exception e) {
			System.out.println("Alegeti dintre aceste 3 puncte!");
		}
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    out.close();
    in.close();
    l.close();
    L.close();
    fromserver.close();
    System.out.println("Close");
  }
}