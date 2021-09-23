package communication.group.secure;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import communication.group.secure.dao.MessageDao;
import communication.group.secure.ui.ShowAdminMsg;

public class AdminMsg {
	Socket s;
	ServerSocket ss;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	MessageDao messageDao;

	public AdminMsg() {
		try {
			System.out.println("Waiting for the Message");
			ss = new ServerSocket(1000);
			while (true) {
				s = ss.accept();
				System.out.println("Server socket accepts socket request");

				dis = new DataInputStream(s.getInputStream());
				String packet = dis.readUTF();
				System.out.println("The Received Packet = " + packet);
				String info[] = packet.split("#");
				new ShowAdminMsg(info[0], info[1], info[2], info[3], info[4],
						info[5]);
				System.out
						.println("Show the Requisition Information to the Admin...");

				boolean found = messageDao.getResultInfo(info[0], info[1], info[2]);
				if (found) {
					dos = new DataOutputStream(s.getOutputStream());
					dos.writeUTF("Accepted");
				} else {
					dos = new DataOutputStream(s.getOutputStream());
					dos.writeUTF("Rejected");
				}
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	public static void main(String args[]) {
		new AdminMsg();

	}
}