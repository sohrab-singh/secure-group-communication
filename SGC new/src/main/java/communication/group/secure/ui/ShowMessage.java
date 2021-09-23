package communication.group.secure.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ShowMessage extends JFrame {

	private static final long serialVersionUID = -6544350980699668138L;

	private JLabel image;
	private JLabel senderInformationText;
	private JLabel sendText;
	private JLabel senderGroupText;
	private JLabel subjectText;
	private JTextField subject;
	private JTextField mailid;
	private JTextField password;
	private JTextArea textarea;
	private JScrollPane jScrollPane1;
	private JButton accept;
	private JButton exit;
	private JPanel contentPane;
	public String sender, sub, datas;
	public int c;
	public boolean flag = false;
	private String gid, message;

	public ShowMessage(String sender, String gid, String subject, String msg,
			String key) {
		super();
		this.sender = sender;
		this.gid = gid;
		sub = subject;
		message = msg;
		initializeComponent();
		flag = true;
		this.setVisible(true);
	}

	private void initializeComponent() {
		mailid = new JTextField();
		mailid.setFont(new Font("Garamond", Font.BOLD, 15));
		mailid.setText(sender);

		password = new JTextField();
		password.setFont(new Font("Garamond", Font.BOLD, 15));
		password.setText(gid);

		textarea = new JTextArea();
		textarea.setFont(new Font("Garamond", Font.BOLD, 15));
		textarea.setText(message);

		image = new JLabel();
		image.setIcon(new ImageIcon("src/main/resources/images/EULA.JPG"));

		subjectText = new JLabel();
		subjectText.setText("Subject");
		subjectText.setFont(new Font("Garamond", Font.BOLD, 15));

		sendText = new JLabel();
		sendText.setText("Sender Information");
		sendText.setFont(new Font("Garamond", Font.BOLD, 15));

		senderGroupText = new JLabel();
		senderGroupText.setText("Sender Group Name");
		senderGroupText.setFont(new Font("Garamond", Font.BOLD, 15));

		senderInformationText = new JLabel();
		senderInformationText.setText("Sender Information...");
		senderInformationText.setFont(new Font("Garamond", Font.BOLD, 15));

		subject = new JTextField();
		subject.setText(sub);
		subject.setFont(new Font("Garamond", Font.BOLD, 15));
		subject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nsubject_actionPerformed(ActionEvent e) called.");
			}

		});

		jScrollPane1 = new JScrollPane();
		jScrollPane1.setViewportView(textarea);

		accept = new JButton();
		accept.setBackground(new Color(51, 51, 51));
		accept.setForeground(new Color(255, 255, 255));
		accept.setText("Send Request");
		accept.setIcon(new ImageIcon("src/main/resources/images/ok.JPG"));
		accept.setFont(new Font("Garamond", Font.BOLD, 15));
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\naccept_actionPerformed(ActionEvent e) called.");
				setVisible(false);
			}

		});

		exit = new JButton();
		exit.setBackground(new Color(51, 51, 51));
		exit.setForeground(new Color(255, 255, 255));
		exit.setText("Back");
		exit.setIcon(new ImageIcon("src/main/resources/images/back.JPG"));
		exit.setFont(new Font("Garamond", Font.BOLD, 15));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nexit_actionPerformed(ActionEvent e) called.");
				setVisible(false);
			}

		});

		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(245, 245, 245));
		addComponent(contentPane, image, 61, 70, 182, 433);
		addComponent(contentPane, subjectText, 277, 120, 60, 24);
		addComponent(contentPane, senderInformationText, 277, 240, 200, 24);
		addComponent(contentPane, sendText, 277, 160, 350, 24);
		addComponent(contentPane, senderGroupText, 277, 200, 350, 24);
		addComponent(contentPane, subject, 360, 120, 172, 22);
		addComponent(contentPane, mailid, 460, 160, 200, 22);
		addComponent(contentPane, password, 460, 200, 200, 22);
		addComponent(contentPane, jScrollPane1, 277, 280, 571, 167);
		addComponent(contentPane, accept, 350, 480, 115, 28);
		addComponent(contentPane, exit, 500, 480, 115, 28);

		this.setTitle("Message Window");
		this.setLocation(new Point(50, 100));
		this.setSize(new Dimension(912, 600));
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		container.add(c);
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
		new ShowMessage("", "", "", "", "");
	}

}
