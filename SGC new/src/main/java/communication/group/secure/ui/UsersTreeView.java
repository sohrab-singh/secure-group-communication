package communication.group.secure.ui;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import communication.group.secure.dao.MessageDao;

/**
 * The Class UsersTreeView.
 */
public class UsersTreeView extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6007793016834230547L;

	/** The dao. */
	private MessageDao dao;

	/**
	 * Instantiates a new users tree view.
	 */
	public UsersTreeView() {

		try {
			DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
					"Group");

			rootNode.add(getUsersForGroup("Group 1"));
			rootNode.add(getUsersForGroup("Group 2"));
			JTree tree = new JTree(rootNode);
			tree.setEditable(true);
			JScrollPane sp = new JScrollPane(tree);
			getContentPane().add(sp, BorderLayout.CENTER);

		} catch (Exception e) {

		}

		this.setSize(300, 300);
		this.setLocation(350, 250);
		this.setVisible(true);
		this.setTitle("Group View");

	}

	/**
	 * Gets the users for group.
	 *
	 * @param groupId
	 *            the group id
	 * @return the usersfor group
	 * @throws Exception
	 *             the exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	private DefaultMutableTreeNode getUsersForGroup(String groupId)
			throws Exception, SQLException {
		DefaultMutableTreeNode groupNode1 = new DefaultMutableTreeNode(groupId);
		int groupNodeCount1 = dao.getUsersCount(groupId);
		String[] groupUsers1 = dao.getUsers(groupId, groupNodeCount1);
		DefaultMutableTreeNode[] groupNode = new DefaultMutableTreeNode[groupUsers1.length];
		for (int i = 0; i < groupUsers1.length; i++) {
			groupNode[i] = new DefaultMutableTreeNode(groupUsers1[i]);
			groupNode1.add(groupNode[i]);

		}
		return groupNode1;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
		new UsersTreeView();

	}
}
