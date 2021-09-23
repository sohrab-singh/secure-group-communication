package communication.group.secure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import communication.group.secure.security.Decryption;
import communication.group.secure.security.ElGamalPublicKey;
import communication.group.secure.security.SignatureGeneration;
import communication.group.secure.ui.ShowMessage;

public class MessageReceiver {
	private ServerSocket serverSocket;
	private Socket socket;
	String str, id, sctkey, sender, receiver, subject, fname, flag, gettable;
	Connection con, con1;
	Statement st, st1;
	ResultSet rs;

	public MessageReceiver() {
		try {

			System.out.println("Waiting for the Message");
			serverSocket = new ServerSocket(3000);

			while (true) {
				socket = serverSocket.accept();
				System.out.println("Server socket accepts socket request");
				ObjectInputStream dis = new ObjectInputStream(
						socket.getInputStream());
				byte[] bb = (byte[]) dis.readObject();
				String message = new String(bb);
				System.out.println(message);

				String info[] = message.split("#");
				String sender = info[0];
				String gid = info[1];
				String subject = info[2];
				String key = info[4];
				String values[] = info[3].split(",");
				BigInteger p = new BigInteger(values[2]);
				BigInteger g = new BigInteger(values[3]);
				BigInteger y = new BigInteger(values[4]);
				new ElGamalPublicKey(p, g, y);
				bb = (byte[]) dis.readObject();
				String s1 = null;
				try {
					FileOutputStream fos = new FileOutputStream(subject
							+ ".txt");
					fos.write(bb);
					System.out.println("The Encrypted File is Written");
					new Decryption(subject + ".txt", key);
					System.out.println("Successfully Decrypted");
					FileInputStream fis = new FileInputStream("Receive.txt");
					byte[] b = new byte[fis.available()];
					fis.read(b);
					s1 = new String(b);
					System.out.println("The Received Message ========= "
							+ message);
					s1 = s1.substring(0, s1.indexOf("�"));
				} catch (Exception e) {
					System.out.println("Exception :" + e);
				}
				SignatureGeneration sg = new SignatureGeneration(
						message.getBytes());
				message = values[0] + "," + values[1];
				byte[] signature = message.getBytes();
				boolean flag = sg.engineVerify(signature);
				ObjectOutputStream dos = new ObjectOutputStream(
						socket.getOutputStream());
				if (flag) {
					System.out.println("Signature is Verified Successfully");
					new ShowMessage(sender, gid, subject, s1, key);
					message = new String("Success");
					dos.writeObject(message.getBytes());
				} else {
					System.out
							.println("Signature is Not Verified Successfully");
					message = new String("failure");
					dos.writeObject(message.getBytes());
				}
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	public static void main(String args[]) {
		new MessageReceiver();

	}
}