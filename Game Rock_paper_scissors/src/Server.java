import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Random;

public class Server {

	static HashMap<Integer, String> shapes = new HashMap<Integer, String>();
    static BufferedReader in = null;
    static PrintWriter    out= null;
    static ServerSocket   servers = null;
    static Socket         fromclient = null;
    static int            port = 4444;
    static Random random = new Random();
    
  public static void main(String[] args) throws IOException {

	  shapes.put(1, "Foarfece");
	  shapes.put(2, "Hartie");
	  shapes.put(3, "Piatra");
	  Connect();
  }
  
  public static void Connect() throws IOException {
	    //////////////////////////////////////////////////////////////////// create server socket
	    try {
	      servers = new ServerSocket(port);
	    } catch (IOException e) {
	      System.out.println("Nu am putut conecta la portul: "+ port);
	      System.exit(-1);
	    }
	    try {
	      System.out.print("Asteptarea clientului... ");
	      fromclient= servers.accept();
	      System.out.println("Clientul sa connectat");
	    } catch (IOException e) {
	      System.out.println("Clientul nu sa connectat");
	      System.exit(-1);
	    }
	    
	/////////////////////////////////////////////////////////////////////////////////////////////
	    in  = new BufferedReader(new 
	     InputStreamReader(fromclient.getInputStream()));
	 
	    out = new PrintWriter(fromclient.getOutputStream(),true);
	    
	    String input, output;

	////////////////////////////////////////////////////////////////////////////////////////////
	    try{
		    while ((input = in.readLine()) != null) {
				 if (input.equalsIgnoreCase("exit")) {
					 System.out.println("Serverul oprit");
					 System.exit(-1);
				 }
				 Evaluate(input);
		    }
	    } catch (SocketException ex) {
	    	System.out.println(ex.getMessage());
        }
	    
	////////////////////////////////////////////////////////////////////////////////////////////
	    out.close();
	    in.close();
	    fromclient.close();
	    servers.close();
	    Connect();
  }
  public static void Evaluate(String num) {
	 int rand = Rand();
	 out.println("Clientul alege: " + shapes.get(Int(num)) + ", Serverul alege: " + shapes.get(rand));
	 if (Int(num) == 1 && rand == 2 || Int(num) == 2 && rand == 3 || Int(num) == 3 && rand == 1) {
		 out.println("Clientul a Castigat! :) | <" + shapes.get(Int(num)) + "> : " + shapes.get(rand));
	 } else if (Int(num) == 1 && rand == 3 || Int(num) == 2 && rand == 1 || Int(num) == 3 && rand == 2) {
		 out.println("Serverul a Castigat! :) | " + shapes.get(Int(num)) + " : <" + shapes.get(rand) + ">");
	 } else {
		 out.println("Serverul a egalat cu clientul! :) | <" + shapes.get(Int(num)) + "> : <" + shapes.get(rand) + ">");
	 }
  }
  
  public static int Int(String n) {
	  return Integer.parseInt(n);
  }
  
  public static int Rand() {
	  return random.nextInt(3) + 1;
  }
}