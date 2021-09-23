package communication.group.secure.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import communication.group.secure.service.LoginService;

/**
 * The Class Login.
 */
public class SecureLogin extends JFrame {

	private LoginService loginService;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4263042309993989703L;

	/** The j label 1. */
	private JLabel heading;

	/** The j label 2. */
	private JLabel existingUser;

	/** The j label 3. */
	private JLabel userIdText;

	/** The j label 4. */
	private JLabel passwordText;

	/** The j label 5. */
	private JLabel signature;

	/** The j label 6. */
	private JLabel newUsersText;

	/** The j label 7. */
	private JLabel groupText;

	/** The j label 8. */
	private JLabel handsign;

	/** The login. */
	private JLabel login;

	/** The grpinfo. */
	@SuppressWarnings("rawtypes")
	private JComboBox groups;

	/** The userid. */
	private JTextField userid;

	/** The sctkey. */
	private JPasswordField sctkey;

	/** The j separator 1. */
	private JSeparator jSeparator1;

	/** The signup. */
	private JLabel signup;

	/** The content pane. */
	private JPanel contentPane;

	/**
	 * Instantiates a new login.
	 */
	public SecureLogin() {
		super();
		initializeComponent();
		this.setVisible(true);
	}

	/**
	 * Initialize component.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initializeComponent() {
		heading = new JLabel();
		existingUser = new JLabel();
		userIdText = new JLabel();
		passwordText = new JLabel();
		signature = new JLabel();
		newUsersText = new JLabel();
		groupText = new JLabel();
		handsign = new JLabel();
		login = new JLabel();
		signup = new JLabel();
		userid = new JTextField();
		sctkey = new JPasswordField();
		jSeparator1 = new JSeparator();
		groups = new JComboBox();
		groups.setFont(new Font("Garamond", Font.BOLD, 15));
		groups.addItem("Group 1");
		groups.addItem("Group 2");
		userid.setFont(new Font("Garamond", Font.BOLD, 15));
		sctkey.setFont(new Font("Garamond", Font.BOLD, 15));

		contentPane = (JPanel) this.getContentPane();

		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setHorizontalTextPosition(SwingConstants.CENTER);
		heading.setText("Secure Group Login");
		heading.setForeground(new Color(0, 0, 0));
		heading.setFont(new Font("Garamond", Font.BOLD, 30));

		existingUser.setText("Existing Users");
		existingUser.setFont(new Font("Garamond", Font.BOLD, 20));
		existingUser.setForeground(new Color(0, 0, 0));

		userIdText.setText("User ID");
		userIdText.setForeground(new Color(0, 0, 0));
		userIdText.setFont(new Font("Garamond", Font.BOLD, 15));

		passwordText.setText("Password");
		passwordText.setForeground(new Color(0, 0, 0));
		passwordText.setFont(new Font("Garamond", Font.BOLD, 15));

		groupText.setText("Group");
		groupText.setForeground(new Color(0, 0, 0));
		groupText.setFont(new Font("Garamond", Font.BOLD, 15));

		groups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				groupInformationActionPerformed(e);
			}

		});

		signature.setIcon(new ImageIcon(
				"src/main/resources/images/signature.jpg"));
		handsign.setIcon(new ImageIcon("src/main/resources/images/handsign.jpg"));
		newUsersText.setText("New Users");
		newUsersText.setForeground(new Color(0, 0, 0));
		newUsersText.setFont(new Font("Garamond", Font.BOLD, 20));

		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setIcon(new ImageIcon("src/main/resources/images/signin.JPG"));
		login.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loginMouseClicked(e);
			}

		});
		userid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("\nuserid_actionPerformed(ActionEvent e) called.");
			}

		});
		sctkey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out
						.println("\nsctkey_actionPerformed(ActionEvent e) called.");
			}

		});
		signup.setIcon(new ImageIcon("src/main/resources/images/signup.JPG"));
		signup.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				signupMouseClicked(e);
			}

		});

		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createEtchedBorder());
		contentPane.setBackground(new Color(245, 245, 245));
		addComponent(contentPane, heading, 50, 0, 453, 40);
		addComponent(contentPane, existingUser, 50, 50, 350, 40);
		addComponent(contentPane, userIdText, 50, 100, 310, 18);
		addComponent(contentPane, passwordText, 50, 145, 300, 18);
		addComponent(contentPane, groupText, 50, 195, 300, 18);
		addComponent(contentPane, signature, 350, 50, 200, 200);
		addComponent(contentPane, handsign, 0, 320, 600, 100);
		addComponent(contentPane, newUsersText, 50, 275, 350, 40);
		addComponent(contentPane, login, 155, 235, 140, 27);
		addComponent(contentPane, groups, 125, 190, 160, 22);
		addComponent(contentPane, userid, 125, 100, 158, 22);
		addComponent(contentPane, sctkey, 125, 145, 158, 22);
		addComponent(contentPane, jSeparator1, 0, 270, 626, 23);
		addComponent(contentPane, signup, 160, 280, 140, 28);

		this.setTitle("Login Window Page");
		this.setLocation(new Point(75, 100));
		this.setSize(new Dimension(635, 480));
	}

	/**
	 * Adds the component.
	 *
	 * @param container
	 *            the container
	 * @param c
	 *            the c
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	private void addComponent(Container container, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		container.add(c);
	}

	/**
	 * Grpinfo action performed.
	 *
	 * @param e
	 *            the e
	 */
	private void groupInformationActionPerformed(ActionEvent e) {
		System.out.println("\ngrpinfo_actionPerformed(ActionEvent e) called.");

		Object o = groups.getSelectedItem();
		System.out.println(">>" + ((o == null) ? "null" : o.toString())
				+ " is selected.");

	}

	/**
	 * Login mouse clicked.
	 *
	 * @param e
	 *            the e
	 * @throws Exception
	 */
	private void loginMouseClicked(MouseEvent e) {
		System.out.println("\nlogin_mouseClicked(MouseEvent e) called.");

		String id = userid.getText();
		String password = new String(sctkey.getPassword());
		String gid = groups.getSelectedItem().toString();

		boolean isValid = loginService.validateLogin(id, password, gid);
		try {
			if (isValid) {
				new ComposeMsg(id, gid);
			} else {
				JOptionPane.showMessageDialog(this,
						"Please Enter Your ID or Password Correctly");
			}
		} catch (Exception ex) {
			System.out.println("Error in Logging..." + ex);
		}
	}

	/**
	 * Signup mouse clicked.
	 *
	 * @param e
	 *            the e
	 */
	private void signupMouseClicked(MouseEvent e) {
		System.out.println("\nlogin_mouseClicked(MouseEvent e) called.");
		new SendRequest();
	}

}
