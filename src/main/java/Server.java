import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

	
	public class Server{

		int count = 1;
		int portNumber = 0;
		ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
		TheServer server;
		private Consumer<Serializable> callback;
		BaccaratGame bacGame;
		
		Server(Consumer<Serializable> call, int portNumber){
			this.portNumber = portNumber;
			callback = call;
			server = new TheServer();
			server.start();
		}
		
		
		public class TheServer extends Thread{
			
			public void run() {
			
				try(ServerSocket socket = new ServerSocket(portNumber);){
				callback.accept("Server is waiting for a client!");
				
			    while(true) {
			    	
					ClientThread c = new ClientThread(socket.accept(), count);
					callback.accept("Client has connected to server: " + "client #" + count);
					callback.accept("There are " + count + " clients connected to the server");
					clients.add(c);
					c.start();
					
					count++;
					
				    }
				}//end of try
					catch(Exception e) {
						callback.accept("Server socket did not launch");
					}
				}//end of while
			}
		

			class ClientThread extends Thread{
			    
				Socket connection;
				int count;
				ObjectInputStream in;
				ObjectOutputStream out;
				double totalWinnings = 0;
				
				
				ClientThread(Socket s, int count){
					this.connection = s;
					this.count = count;	
				}
				
				public void run(){
						
					try {
						in = new ObjectInputStream(connection.getInputStream());
						out = new ObjectOutputStream(connection.getOutputStream());
						connection.setTcpNoDelay(true);	
					}
					catch(Exception e) {
						System.out.println("Streams not open");
					}
						
					 while(true) {
						    try {
						    	// this here would be BaccaratInfo to read from it
						    	BaccaratInfo clientInfo = (BaccaratInfo)in.readObject();
						    	System.out.println("information recieved from client");
						    	System.out.println(clientInfo);
						    	bacGame = new BaccaratGame(clientInfo.bettingAmount, clientInfo.bettingType);
						    	clientInfo.currentWinnings = bacGame.evaluateWinnings();
						    	totalWinnings += clientInfo.currentWinnings;
						    	clientInfo.playerHand = new ArrayList<String>();
						    	clientInfo.bankerHand = new ArrayList<String>();						    	
						    	for (Card c: bacGame.playerHand) {
						    		clientInfo.playerHand.add(c.toString());
						    	}
						    	
						    	for (Card c: bacGame.bankerHand) {
						    		clientInfo.bankerHand.add(c.toString());
						    	}
						    	clientInfo.naturalWin = bacGame.naturalWin;
						    	clientInfo.playerDraw = bacGame.playerDraw;
						    	clientInfo.bankerDraw = bacGame.bankerDraw;
						    	clientInfo.gameResult = bacGame.gameResult;
						    	clientInfo.totalWinnings = this.totalWinnings;
						    	System.out.println(clientInfo.totalWinnings);
						    	System.out.println("information updated in the server");
						    	System.out.println(clientInfo);
						    	send(clientInfo);
						    	
						    	
						    	}
						    catch(Exception e) {
						    	callback.accept("client: " + count + " left the server");
						    	callback.accept("There are " + count + " clients connected to the server");
						    	clients.remove(this);
						    	break;
						    }
						}
					}//end of run
				public void send(BaccaratInfo clientInfo) {
					try {
						System.out.println("information sent to client");
						out.writeObject(clientInfo);
											
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}//end of client thread
	}


