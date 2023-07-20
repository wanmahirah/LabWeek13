package lab13exercise7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import lab13exercise7.FrameServer;
public class ServerTextTranslator
{
	public static void main(String[] args) throws IOException
	{
		// Binding to a port or any other port no you are fancy of
		int portNo = 2345;
		ServerSocket serverSocket = new ServerSocket(portNo);

		// Launch the server frame
		FrameServer serverFrame = new FrameServer();
		serverFrame.setVisible(true);

		// Counter to keep track the number of requested connection
		int totalRequest = 0;

		// Server needs to be alive forever
		//while (true) {

		// Message to indicate server is alive
		serverFrame.updateServerStatus(false);

		// Accept client request for connection
		Socket clientSocket = serverSocket.accept();

		// Read stream data from the client
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
		String input = bufferReader.readLine();
		String translateTo = bufferReader.readLine();
		System.out.println("Input: " + input);
			
		// get the value for the total words using method wordsCount()
		String TranslateWords = TextTranslator.transliterate(input,translateTo);

		// Create stream to write data on the network
		PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());

		printWriter.println(TranslateWords);
		printWriter.flush();
		System.out.println("Translated word sent to client.");
			
		// Close the socket
		clientSocket.close();
			
		printWriter.close();
		bufferReader.close();
			
		// Update the request status
		serverFrame.updateRequestStatus(input + " in " + translateTo + " is " + TranslateWords);
		serverFrame.updateRequestStatus(input);
		serverFrame.updateRequestStatus("Accepted connection to from the " + "client. Total request = " + ++totalRequest);
	}
}