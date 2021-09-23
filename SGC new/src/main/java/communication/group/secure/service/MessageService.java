package communication.group.secure.service;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.SignatureException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import communication.group.secure.dao.MessageDao;
import communication.group.secure.security.Encryption;
import communication.group.secure.security.SignatureGeneration;

public class MessageService {

	/** The fin. */
	FileInputStream fin;

	/** The skt 1. */
	Socket skt, skt1;

	/** The con. */
	Connection connection;

	/** The st. */
	Statement st;

	/** The rs. */
	ResultSet rs;

	MessageDao messageDao;

	public boolean send(byte[] msg, String senderId, String receiverId,
			String subject, String senderGroupId, String receiverGroupId)
			throws Exception {
		byte[] signature = generateSignature(msg);
		boolean isReceiverExists = messageDao.isUserExists(receiverId,
				receiverGroupId);
		if (isReceiverExists) {
			boolean isMailIdExists = messageDao.checkMailId(senderId,
					senderGroupId);
			if (isMailIdExists) {
				String ipadrs = messageDao.getHostAddress(receiverId,
						receiverGroupId);
				System.out.println("The Receiver Ip Adrs = " + ipadrs);
				if (subject.equalsIgnoreCase("Leave")) {
					skt = new Socket(ipadrs, 1000);
					String s = new String(msg);
					DataOutputStream dos = new DataOutputStream(
							skt.getOutputStream());
					String packet = senderId + "#xyz#" + senderGroupId + "#"
							+ subject + "#" + s + "#xyz";
					System.out.println("The Packet = " + packet);
					dos.writeUTF(packet);
					/**
					 * DataInputStream dis = new DataInputStream(
					 * skt.getInputStream()); System.out.println(dis.readUTF());
					 */
					dos.close();
				} else {
					skt = new Socket(ipadrs, 3000);
					ObjectOutputStream dos = new ObjectOutputStream(
							skt.getOutputStream());
					String sig = new String(signature);
					FileOutputStream fos = new FileOutputStream("Message.txt");
					fos.write(msg);
					System.out.println("The Message is Write");
					String key = messageDao.getKey(senderId, senderGroupId);
					String packet = senderId + "#" + senderGroupId + "#"
							+ subject + "#" + sig + "#" + key;
					System.out.println(packet);
					byte[] b1 = packet.getBytes();
					dos.writeObject(b1);
					new Encryption(subject + ".txt", key);
					FileInputStream fis = new FileInputStream(subject + ".txt");
					b1 = new byte[fis.available()];
					fis.read(b1);
					dos.writeObject(b1);
					System.out.println("Send the Message to the Receiver");
					/**
					 * ObjectInputStream dis = new ObjectInputStream(
					 * skt.getInputStream()); byte[] bb = (byte[])
					 * dis.readObject(); String s = new String(bb);
					 * System.out.println(s); if (s.equalsIgnoreCase("Success"))
					 * { throw new Exception(
					 * "The Message is Successfully Received by the " +
					 * receiverId); } else { throw new Exception(
					 * "The Message is not Successfully Received by the " +
					 * receiverId); }
					 */

				}
			} else {
				throw new Exception(
						"Your Information is Removed from the Group");
			}
		} else {
			throw new Exception("Your not in the group");
		}

		return false;

	}

	private byte[] generateSignature(byte[] msg) throws Exception,
			SignatureException {
		boolean flag = false;
		byte[] signature = { '0' };
		while (!flag) {
			SignatureGeneration signatureGeneration = new SignatureGeneration(
					msg);
			signatureGeneration.generateKeyPair();
			signature = signatureGeneration.signatureGeneration();
			flag = signatureGeneration.engineVerify(signature);
		}
		return signature;
	}

	public void sendToAll(byte[] msg, String senderId, String receiverId,
			String subject, String senderGroupId, String receiverGroupId) {
		try {
			byte[] signature = generateSignature(msg);
			if (subject.equalsIgnoreCase("Leave")) {
				throw new Exception(
						"Give Your Requisition to Your Group Administrator Only");
			} else {
				MessageDao messageDao = new MessageDao();
				String receivers[] = receiverId.split(",");
				System.out.println("No of receivers" + receivers.length);
				for (int i = 0; i < receivers.length; i++) {

					boolean isUserExists = messageDao.isUserExists(
							receivers[i], receiverGroupId);
					System.out.println("j value:::" + i);
					if (isUserExists) {
						boolean isMailIdExists = messageDao.checkMailId(
								senderId, senderGroupId);
						if (isMailIdExists) {
							System.out.println("The Receiver is "
									+ receivers[i]);
							try {
								String ipadrs = messageDao.getHostAddress(
										receivers[i], receiverGroupId);
								System.out.println("The Receiver Ip Adrs = "
										+ ipadrs);
								skt = new Socket(ipadrs, 3000);
								ObjectOutputStream dos = new ObjectOutputStream(
										skt.getOutputStream());
								String sig = new String(signature);
								FileOutputStream fos = new FileOutputStream(
										"Message.txt");
								fos.write(msg);
								System.out.println("The Message is Write");
								String key = messageDao.getKey(senderId,
										senderGroupId);
								String packet = senderId + "#" + senderGroupId
										+ "#" + subject + "#" + sig + "#" + key;
								System.out.println(packet);
								byte[] b1 = packet.getBytes();
								dos.writeObject(b1);
								new Encryption(subject + ".txt", key);
								FileInputStream fis = new FileInputStream(
										subject + ".txt");
								b1 = new byte[fis.available()];
								fis.read(b1);
								dos.writeObject(b1);
								System.out
										.println("Send the Message to the Receiver");
								// ObjectInputStream dis = new
								// ObjectInputStream(
								// skt.getInputStream());
								// byte[] bb = (byte[]) dis.readObject();
								// String s = new String(bb);
								// System.out.println(s);
								// if (s.equalsIgnoreCase("Success")){
								// JOptionPane.showMessageDialog(this,
								// "The Message is Successfully Received by the "
								// + receivers[i]);
								// }else{
								// JOptionPane.showMessageDialog(this,
								// "The Message is not Successfully Received by the "
								// + receivers[i]);
								// }
							} catch (Exception e) {
								System.out.println("Message not send to the "
										+ receiverId + " " + e);
							}
							Thread.sleep(3000);
						} else {
							throw new Exception(
									"Your Information is Removed from the Group");
						}
					} else {

						System.out.println("else part j value ::" + i);
						throw new Exception("not in the group");

					}
				}

			}
		} catch (Exception e) {
			System.out.println("Not able to tokenize " + e);
		}
	}

}
