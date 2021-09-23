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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import communication.group.secure.service.MessageService;

/**
 * The Class ComposeMsg.
 */
public class ComposeMsg extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7803145405756515668L;

	private MessageService messageService;

	/** The j label 1. */
	private JLabel alignedbookImage;

	/** The j label 2. */
	private JLabel groupText;

	/** The j label 4. */
	private JLabel messageText;

	/** The label. */
	private JLabel subjectText;

	/** The j label 5. */
	private JLabel sendToText;

	/** The grpinfo. */
	@SuppressWarnings("rawtypes")
	private JComboBox groupinformation;

	/** The sendto. */
	private JTextField sendTo;

	/** The subject. */
	private JTextField subject;

	/** The textarea. */
	private JTextArea textArea;

	/** The j scroll pane 1. */
	private JScrollPane jScrollPanel;

	/** The send. */
	private JButton send;

	/** The sendtoall. */
	private JButton sendToAll;

	/** The clear. */
	private JButton clear;

	/** The tree. */
	private JButton back;

	/** The content pane. */
	private JPanel contentPane;

	/** The id. */
	private String groupId;

	private String userId;

	/** The sub. */
	private String to, sub;

	/**
	 * Instantiates a new compose msg.
	 *
	 * @param uid
	 *            the uid
	 * @param gid
	 *            the gid
	 */
	public ComposeMsg(String uid, String gid) {
		super();
		this.userId = uid;
		this.groupId = gid;
		initializeComponent();
		this.setVisible(true);
	}

	/**
	 * Initialize component.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initializeComponent() {
		alignedbookImage = new JLabel();
		groupText = new JLabel();
		messageText = new JLabel();
		sendToText = new JLabel();
		subjectText = new JLabel();
		sendTo = new JTextField();
		sendTo.setFont(new Font("Garamond", Font.BOLD, 15));
		subject = new JTextField();
		subject.setFont(new Font("Garamond", Font.BOLD, 15));
		textArea = new JTextArea();
		textArea.setFont(new Font("Garamond", Font.BOLD, 15));

		groupinformation = new JComboBox();
		groupinformation.setFont(new Font("Garamond", Font.BOLD, 15));
		groupinformation.addItem("Group 1");
		groupinformation.addItem("Group 2");
		jScrollPanel = new JScrollPane();
		send = new JButton();
		sendToAll = new JButton();
		clear = new JButton();
		back = new JButton();
		contentPane = (JPanel) this.getContentPane();

		alignedbookImage.setIcon(new ImageIcon(
				"src/main/resources/images/EULA.JPG"));
		groupText.setText("Select Group");
		groupText.setFont(new Font("Garamond", Font.BOLD, 15));

		messageText.setText("Your Message...");
		messageText.setFont(new Font("Garamond", Font.BOLD, 15));

		sendToText.setText("Send To");
		sendToText.setFont(new Font("Garamond", Font.BOLD, 15));

		subjectText.setText("Subject");
		subjectText.setFont(new Font("Garamond", Font.BOLD, 15));

		sendTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("\nsendto_actionPerformed(ActionEvent e) called.");
			}

		});

		subject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("\nsubject_actionPerformed(ActionEvent e) called.");
			}

		});

		groupinformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("\ngrpinfo_actionPerformed(ActionEvent e) called.");

				Object o = groupinformation.getSelectedItem();
				System.out.println(">>" + ((o == null) ? "null" : o.toString())
						+ " is selected.");
			}

		});

		jScrollPanel.setViewportView(textArea);
		jScrollPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPanel
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		send.setIcon(new ImageIcon("src/main/resources/images/sendto.JPG"));
		send.setBackground(new Color(0, 0, 0));
		send.setForeground(new Color(255, 255, 255));
		send.setText("Send To");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performSendAction(e);
			}

		});

		sendToAll.setIcon(new ImageIcon(
				"src/main/resources/images/sendtoall.JPG"));
		sendToAll.setBackground(new Color(51, 51, 51));
		sendToAll.setForeground(new Color(255, 255, 255));
		sendToAll.setText("Send To All");
		sendToAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performSendAllAction(e);
			}

		});

		clear.setIcon(new ImageIcon("images\\clear.JPG"));
		clear.setBackground(new Color(0, 0, 0));
		clear.setForeground(new Color(255, 255, 255));
		clear.setText("Clear Text");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performClearAction(e);
			}

		});

		back.setIcon(new ImageIcon("src/main/resources/images/tree.JPG"));
		back.setBackground(new Color(51, 51, 51));
		back.setForeground(new Color(255, 255, 255));
		back.setText("Back to Inbox");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performBackAction(e);
			}

		});

		contentPane.setLayout(null);
		contentPane.setBackground(new Color(245, 245, 245));
		addComponent(contentPane, alignedbookImage, 61, 70, 182, 433);
		addComponent(contentPane, subjectText, 274, 130, 121, 29);
		addComponent(contentPane, sendToText, 274, 94, 60, 18);
		addComponent(contentPane, messageText, 274, 200, 121, 29);
		addComponent(contentPane, groupText, 274, 170, 121, 29);
		addComponent(contentPane, sendTo, 365, 90, 264, 22);
		addComponent(contentPane, subject, 365, 130, 268, 22);
		addComponent(contentPane, groupinformation, 365, 170, 200, 22);
		addComponent(contentPane, jScrollPanel, 274, 231, 571, 167);
		addComponent(contentPane, send, 284, 440, 110, 30);
		addComponent(contentPane, sendToAll, 426, 440, 110, 30);
		addComponent(contentPane, clear, 571, 440, 110, 30);
		addComponent(contentPane, back, 711, 440, 110, 30);

		this.setTitle("Compose Window");
		this.setLocation(new Point(50, 51));
		this.setSize(new Dimension(912, 554));
	}

	private void addComponent(Container container, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		container.add(c);
	}

	private void performSendAction(ActionEvent e) {
		System.out.println("\nsend_actionPerformed(ActionEvent e) called.");
		try {
			to = sendTo.getText().trim();
			sub = subject.getText().trim();
			byte[] msg = textArea.getText().getBytes();
			String receiverGroupId = groupinformation.getSelectedItem().toString();
			messageService.send(msg, userId, to, sub, groupId,receiverGroupId);

		} catch (Exception ex) {
			System.out.println("Exception arised " + ex);
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	private void performSendAllAction(ActionEvent e) {
		System.out
				.println("\nsendtoall_actionPerformed(ActionEvent e) called.");
		try {
			to = sendTo.getText();
			sub = subject.getText();
			byte[] msg = textArea.getText().getBytes();
			String receiverGroupId = groupinformation.getSelectedItem().toString();
			messageService.sendToAll(msg, userId, to, sub, groupId ,receiverGroupId);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Exception arised " + ex);
		}
	}

	private void performClearAction(ActionEvent e) {
		System.out.println("\nclear_actionPerformed(ActionEvent e) called.");
		textArea.setText("");
		sendTo.setText("");
		subject.setText("");
	}

	private void performBackAction(ActionEvent e) {
		System.out.println("\nback_actionPerformed(ActionEvent e) called.");
		new UsersTreeView();

	}

	public static void main(String[] args) {
		new ComposeMsg("dscq", "cd");

	}

}
