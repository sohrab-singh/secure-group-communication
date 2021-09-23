package communication.group.secure.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import communication.group.secure.dao.LoginDao;
import communication.group.secure.service.MessageService;

public class SendRequest extends JFrame {
	
	private static final long serialVersionUID = -7308369129418847121L;

	private MessageService messageService;
	
	private JLabel image;
	private JLabel requestText;
	private JLabel idText;
	private JLabel passwordText;
	private JLabel sendToText;
	private JLabel subjectText;
	private JTextField subject;
	private JTextField mailid;
	private JPasswordField password;
	@SuppressWarnings("rawtypes")
	private JComboBox groupInformation;
	private JTextArea textarea;
	private JScrollPane jScrollPane1;
	private JButton send;
	private JButton clear;
	private JButton exit;
	private JPanel contentPane;
	public String sender, sub, datas;
	public int c;
	public boolean flag;

	public SendRequest() {
		super();
		initializeComponent();
		this.flag = true;
		this.setVisible(true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked"})
	private void initializeComponent() {
		image = new JLabel();
		image.setIcon(new ImageIcon("src/main/resources/images/EULA.JPG"));
		
		requestText = new JLabel();
		requestText.setText("Your Requisition...");
		requestText.setFont(new Font("Garamond", Font.BOLD, 15));

		idText = new JLabel();
		idText.setText("Give Your Id");
		idText.setFont(new Font("Garamond", Font.BOLD, 15));
		
		passwordText = new JLabel();
		passwordText.setText("Give Your Password");
		passwordText.setFont(new Font("Garamond", Font.BOLD, 15));
		
		sendToText = new JLabel();
		sendToText.setText("Send To");
		sendToText.setFont(new Font("Garamond", Font.BOLD, 15));
		
		subjectText = new JLabel();
		subjectText.setText("Subject");
		subjectText.setFont(new Font("Garamond", Font.BOLD, 15));
		subject.setText("join");
		
		subject = new JTextField();
		subject.setFont(new Font("Garamond", Font.BOLD, 15));
		subject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out
				.println("\nsubject_actionPerformed(ActionEvent e) called.");
			}
		});
		
		mailid = new JTextField();
		mailid.setFont(new Font("Garamond", Font.BOLD, 15));
		
		password = new JPasswordField();
		password.setFont(new Font("Garamond", Font.BOLD, 15));
		
		groupInformation = new JComboBox();
		groupInformation.setFont(new Font("Garamond", Font.BOLD, 15));
		groupInformation.addItem("Group 1");
		groupInformation.addItem("Group 2");
		groupInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grpinfo_actionPerformed(e);
			}
		});
		
		textarea = new JTextArea();
		textarea.setFont(new Font("Garamond", Font.BOLD, 15));
		
		send = new JButton();
		send.setBackground(new Color(51, 51, 51));
		send.setForeground(new Color(255, 255, 255));
		send.setText("Send Request");
		send.setIcon(new ImageIcon("src/main/resources/images/submit.JPG"));
		send.setFont(new Font("Garamond", Font.BOLD, 15));
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send_actionPerformed(e);
			}
			
		});
		
		clear = new JButton();
		clear.setBackground(new Color(51, 51, 51));
		clear.setForeground(new Color(255, 255, 255));
		clear.setText("Clear Text");
		clear.setIcon(new ImageIcon("images\\clear.JPG"));
		clear.setFont(new Font("Garamond", Font.BOLD, 15));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear_actionPerformed(e);
			}
			
		});
		
		exit = new JButton();
		exit.setBackground(new Color(51, 51, 51));
		exit.setForeground(new Color(255, 255, 255));
		exit.setText("Back");
		exit.setIcon(new ImageIcon("images\\back.JPG"));
		exit.setFont(new Font("Garamond", Font.BOLD, 15));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit_actionPerformed(e);
			}
			
		});

		jScrollPane1 = new JScrollPane();
		jScrollPane1.setViewportView(textarea);
		
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(12, 145, 244));
		
		addComponent(contentPane, image, 61, 70, 182, 433);
		addComponent(contentPane, sendToText, 277, 80, 60, 24);
		addComponent(contentPane, subjectText, 277, 120, 60, 24);
		addComponent(contentPane, requestText, 277, 240, 200, 24);
		addComponent(contentPane, idText, 277, 160, 350, 24);
		addComponent(contentPane, passwordText, 277, 200, 350, 24);
		addComponent(contentPane, subject, 360, 120, 172, 22);
		addComponent(contentPane, mailid, 460, 160, 200, 22);
		addComponent(contentPane, password, 460, 200, 200, 22);
		addComponent(contentPane, groupInformation, 360, 80, 172, 22);
		addComponent(contentPane, jScrollPane1, 277, 280, 571, 167);
		addComponent(contentPane, send, 350, 480, 115, 28);
		addComponent(contentPane, clear, 500, 480, 115, 28);
		addComponent(contentPane, exit, 650, 480, 115, 28);

		this.setTitle("Send Request Window");
		this.setLocation(new Point(50, 100));
		this.setSize(new Dimension(912, 600));
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		container.add(c);
	}

	private void grpinfo_actionPerformed(ActionEvent e) {
		System.out.println("\ngrpinfo_actionPerformed(ActionEvent e) called.");
		Object o = groupInformation.getSelectedItem();
		System.out.println(">>" + ((o == null) ? "null" : o.toString())
				+ " is selected.");
	}

	@SuppressWarnings("deprecation")
	private void send_actionPerformed(ActionEvent e) {
		System.out.println("\nsend_actionPerformed(ActionEvent e) called.");
		
		try {
			String gid = groupInformation.getSelectedItem().toString();
			sub = subject.getText().trim();
			String mail = mailid.getText().trim();
			String pass = password.getPassword().toString().trim();
			if (pass.length() > 10)
				JOptionPane.showMessageDialog(this,
						"Give Your Password Within 10 Characters");
			else {
				LoginDao db = new LoginDao();
				int result = db.checkMailId(mail, gid);
				System.out.println("The Result " + result);
				if (result == 1)
					JOptionPane.showMessageDialog(this,
							"Your Information is Already Avaliable...");
				else {
					datas = textarea.getText();
					sendTo(mail, pass, gid, sub, datas);
				}
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
					"Your Request is not send to the Group Controller");
			ex.printStackTrace();
		}
	}

	private void clear_actionPerformed(ActionEvent e) {
		System.out.println("\nclear_actionPerformed(ActionEvent e) called.");
		textarea.setText("");
		mailid.setText("");
		password.setText("");
	}

	private void exit_actionPerformed(ActionEvent e) {
		System.out.println("\nexit_actionPerformed(ActionEvent e) called.");
		setVisible(false);
	}

	public void sendTo(String mailid, String pass, String gid, String subject,
			String msg) {
		try {
			LoginDao db = new LoginDao();
			String grpid = "";
			if (gid.equalsIgnoreCase("Group 1"))
				grpid = "G1Admin";
			else
				grpid = "G2Admin";
			String ipadrs = db.getHostAdrs(grpid);
			System.out.println("The Admin IpAdrs = " + ipadrs);
			String usradrs = InetAddress.getLocalHost().toString();
			usradrs = usradrs.substring(0, usradrs.indexOf("/"));
			System.out.println("The User Ip Adrs = " + usradrs);
			Socket skt = new Socket(ipadrs, 1000);
			DataOutputStream dos = new DataOutputStream(skt.getOutputStream());
			String packet = mailid + "#" + pass + "#" + gid + "#" + subject
					+ "#" + msg + "#" + usradrs;
			System.out.println(packet);
			dos.writeUTF(packet);
			DataInputStream dis = new DataInputStream(skt.getInputStream());
			String feedback = dis.readUTF();
			dos.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"Your Request is not send to the Group Controller");
		}
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		new SendRequest();
	}

}
