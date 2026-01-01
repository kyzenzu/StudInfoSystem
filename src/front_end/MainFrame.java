package front_end;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;
import back_end.*;

public class MainFrame {
	private User user;
	private	JFrame frame;
	private	Container container;
	private	StuInfoList list;
	private JLabel currentClass;
	private JLabel scoreLabel;
	private	JTextArea scoreArea;
	private JMenuBar menuBar;
	
	public MainFrame(User user) {
		this.user = user;
		frame = new JFrame();
		container = frame.getContentPane();
		frame.setLayout(null);
		frame.setTitle("主界面");	
		
		currentClass = new JLabel();
		currentClass.setText("当前班级: " + user.getFirstClass());
		currentClass.setBounds(0, 5, 106, 15);
		container.add(currentClass);
		
		list = new StuInfoList(this, user.getFirstClass());

		MyScrollPane scrollPane = new MyScrollPane(list.getList());
		scrollPane.setBounds(0, 25, 140, 200);
		container.add(scrollPane);
		
		scoreLabel = new JLabel();
		scoreLabel.setText("成绩");
		scoreLabel.setBounds(167, 10, 42, 15);
		container.add(scoreLabel);
		
		scoreArea = new JTextArea();
		scoreArea.setLineWrap(false);
		scoreArea.setBounds(158, 30, 52, 39);
		container.add(scoreArea);
		
		menuBar = new JMenuBar();
		
		JMenu operation = new JMenu("操作");
		JMenuItem addStu = new JMenuItem("添加");
		JMenuItem searchStu = new JMenuItem("查找");
		JMenuItem selectClass= new JMenuItem("选择班级");
		JMenuItem resetClass = new JMenuItem("重置班级");
		addStu.addActionListener(e -> {
			new AddStudentFrame(list);
		});
		searchStu.addActionListener(e -> {
			new SearchFrame(list);
		});
		selectClass.addActionListener(e -> {
			new SelectClassFrame(this, user);
		});
		resetClass.addActionListener(e -> {
			new ResetClassFrame(this, user);
		});
		operation.add(addStu);
		operation.add(searchStu);
		operation.add(selectClass);
		operation.add(resetClass);
		menuBar.add(operation);
		
		JMenu userMenu = new JMenu("用户");
		JMenuItem nameItem = new JMenuItem("用户名");
		JMenuItem modiPassword = new JMenuItem("修改密码");
		JMenuItem logout = new JMenuItem("注销");
		JMenuItem aboutMe = new JMenuItem("关于我");
		nameItem.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "用户名: " + user.getUsername(), "消息", -1); 
		});
		modiPassword.addActionListener(e -> {
			frame.setVisible(false);
			new ModifyPasswordFrame(user);
		});
		logout.addActionListener(e -> {
			int n = JOptionPane.showConfirmDialog(null,"确定是否注销","确定界面",JOptionPane.YES_NO_CANCEL_OPTION);
			if (n == 0) {
				frame.setVisible(false);
				new LoginFrame();
			}
		});
		aboutMe.addActionListener(e -> {
			StringBuffer sb = new StringBuffer("");
			ArrayList<String> c = user.getClasses();
			for (int i = 0; i < c.size(); i++) {
				sb.append(c.get(i));
				if (i != c.size() - 1)
					sb.append(", ");
			}
			String classes = sb.toString();
			JOptionPane.showMessageDialog(null, "任教班级:" + classes, "消息", -1); 
		});
		userMenu.add(nameItem);
		userMenu.add(modiPassword);
		userMenu.add(logout);
		userMenu.add(aboutMe);
		menuBar.add(userMenu);
		
		JMenu helpMenu = new JMenu("帮助");
		JMenuItem aboutItem = new JMenuItem("关于");
		JMenuItem quit = new JMenuItem("退出");
		aboutItem.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "作者:张三, 李四", "消息", -1); 
		});
		quit.addActionListener(e -> {
			frame.setVisible(false);
			System.exit(0);
		});
		helpMenu.add(aboutItem);
		helpMenu.add(quit);
		menuBar.add(helpMenu);
		
		frame.setJMenuBar(menuBar);
		
		container.setLayout(null);
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public Container getContainer() {
		return container;
	}
	public JFrame getFrame() {
		return frame;
	}
	public StuInfoList getList() {
		return list;
	}
	public JTextArea getTextArea() {
		return scoreArea;
	}
	public JLabel getCurrentClass() {
		return currentClass;
	}
	public User getUser() {
		return user;
	}
	public static void main(String[] args) {
		User u = new User("admin", "admin");
		ArrayList<String> c = new ArrayList<String>();
		c.add("软工22-1");
		c.add("软工22-2");
		c.add("软工22-3");
		u.setClasses(c);
		new MainFrame(u);
	}
}
