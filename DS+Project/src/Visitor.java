
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({ "serial", "unused" })
public class Visitor extends JFrame {

	private JPanel contentPane;
	private JTextField dateBox;
	private JTextField textField;
	private static JTextField txtInstructions;
	private static JTextField commandTextField;
	private static Panel hostPanel;

	private String attractionID, reqID, fName, lName, email, attractionName, message, dateAndTime;

	/**
	 * Launch the Visitor Frame. Default constructors are the main display.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visitor frame = new Visitor();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} // Exception Handling
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Visitor(String defaultValue) {
		this(defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue,
				defaultValue);
	}

	public Visitor(String reqID, String fName, String lName, String email, String attractionID, String attractionName,
			String message, String dateAndTime) throws HeadlessException {
		super();
		this.attractionID = attractionID;
		this.reqID = reqID;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.attractionName = attractionName;
		this.message = message;
		this.dateAndTime = dateAndTime;
	}

	public Visitor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 627);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Main Header
		JLabel lblDIGoodPlaceDem = new JLabel("!!! Di Good Place Dem !!!");
		lblDIGoodPlaceDem.setHorizontalAlignment(SwingConstants.CENTER);
		lblDIGoodPlaceDem.setForeground(Color.BLACK);
		lblDIGoodPlaceDem.setFont(new Font("Tw Cen MT Condensed", Font.BOLD | Font.ITALIC, 44));
		lblDIGoodPlaceDem.setBackground(Color.WHITE);
		lblDIGoodPlaceDem.setBounds(141, 14, 595, 68);
		contentPane.add(lblDIGoodPlaceDem);

		// Date: Display Current Date
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateBox = new JTextField();
		dateBox.setEditable(false);
		dateBox.setText(dateFormat.format(currentDate));

		dateBox.setColumns(10);
		dateBox.setBounds(57, 14, 86, 22);
		contentPane.add(dateBox);

		// Date: Label
		JLabel label = new JLabel("DATE");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(10, 13, 51, 28);
		contentPane.add(label);

		// Separates sections of display with a line
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 78, 848, 2);
		contentPane.add(separator);

		// Panel in Frame
		Panel panel = new Panel();
		panel.setBounds(10, 86, 848, 47);
		contentPane.add(panel);
		panel.setLayout(null);

		// Display
		JButton btnUseCreole = new JButton("View Creole Commands");
		btnUseCreole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					viewCreoleList();
				} catch (Exception e) {
					e.printStackTrace();
				} // Catches Exception if thrown from function
			}
		});
		btnUseCreole.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnUseCreole.setBounds(0, 0, 411, 47);
		panel.add(btnUseCreole);

		JButton btnMakeRequest = new JButton("Make Request");
		btnMakeRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeRequest();
			}
		});

		btnMakeRequest.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnMakeRequest.setBounds(409, 0, 439, 47);
		panel.add(btnMakeRequest);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 139, 848, 2);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 552, 846, 2);
		contentPane.add(separator_2);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("Copyright \u00A9 2019");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(377, 552, 116, 28);
		contentPane.add(textField);

		hostPanel = new Panel();
		hostPanel.setBackground(Color.GRAY);
		hostPanel.setLayout(null);
		hostPanel.setBounds(10, 147, 848, 399);
		contentPane.add(hostPanel);

	}// end of Visitor

	/**
	 * Utilities .
	 */

	protected static void viewCreoleList() {
		JButton command1 = new JButton(
				"Gimmi all a di place dem inna [Parish Name]- (Eng: Give me all the places in [Parish Name])");
		command1.setToolTipText("");
		command1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				commandTextField.setText("Gimmi all a di place dem inna ");
			}
		});

		txtInstructions = new JTextField();
		txtInstructions.setBounds(0, 0, 848, 28);
		hostPanel.add(txtInstructions);
		txtInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		txtInstructions.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtInstructions.setText("PLEASE SELECT ONE OF THE OPTIONS BELOW OR TYPE COMMAND MANUALLY, THEN PRESS ENTER.");
		txtInstructions.setEditable(false);
		txtInstructions.setColumns(10);
		command1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		command1.setBounds(0, 27, 848, 56);
		hostPanel.add(command1);

		JButton command2 = new JButton(
				"Which part have di cheapest [Attraction Name]- (Eng: Which location has the cheapest [Attraction Name])");
		command2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandTextField.setText("Which part have di cheapest ");
			}
		});
		command2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		command2.setBounds(0, 82, 848, 56);
		hostPanel.add(command2);

		JButton command3 = new JButton(
				"Gi mi all a di infamation fi [Attraction Name]- (Eng: Give me all the information for [Attraction Name])");
		command3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandTextField.setText("Gi mi all a di infamation fi ");
			}
		});
		command3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		command3.setBounds(0, 138, 848, 61);
		hostPanel.add(command3);

		JButton command4 = new JButton("Tell mi bout [Place Name]- (Eng: Tell me about [Place Name])");
		command4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandTextField.setText("Tell mi bout ");
			}
		});
		command4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		command4.setBounds(0, 196, 848, 56);
		hostPanel.add(command4);

		JButton command5 = new JButton("A wah time di place dem open inna [Parish Name]- "
				+ "(Eng: What at the opening hours for the places in [Parish Name])");
		command5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandTextField.setText("A wah time di place dem open inna ");
			}
		});
		command5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		command5.setBounds(0, 249, 848, 56);
		hostPanel.add(command5);

		commandTextField = new JTextField();
		commandTextField.setHorizontalAlignment(SwingConstants.CENTER);
		commandTextField.setBounds(0, 304, 848, 53);
		hostPanel.add(commandTextField);
		commandTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		commandTextField.setColumns(10);

		JButton btnCommandEnter = new JButton("ENTER");
		btnCommandEnter.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCommandEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onEnter();
			}
		});
		btnCommandEnter.setBounds(352, 355, 151, 44);
		hostPanel.add(btnCommandEnter);
	}// end of viewCreoleList()

	protected void makeRequest() {
		// NB: An generated ID show be included here one for request and one for
		// attraction.
		JTextField fNameField = new JTextField(), lNameField = new JPasswordField(), emailField = new JPasswordField(),
				attractionIDField = new JPasswordField(), attractionNameField = new JPasswordField(),
				messageField = new JPasswordField();

		Object form[] = { "First Name", fNameField, "Last name", lNameField, "Email", emailField, "Attraction ID",
				attractionIDField, "Attraction Name", attractionNameField, "Message", messageField };

		int option = JOptionPane.showConfirmDialog(null, form, "User Login Form", JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			if ((fNameField.getText()).isEmpty() || (lNameField.getText()).isEmpty() || (emailField.getText()).isEmpty()
					|| (attractionIDField.getText()).isEmpty() || (attractionNameField.getText()).isEmpty()
					|| (messageField.getText()).isEmpty()) {

				JOptionPane.showMessageDialog(null, "No field should be left empty, request adding cancelled");
			} 
			else {
				reqID = "200";// make into something unique
				fName = fNameField.getText();
				lName = lNameField.getText();
				email = emailField.getText();
				attractionID = attractionIDField.getText();
				attractionName = attractionNameField.getText();
				message = messageField.getText();
				dateAndTime = dateBox.getText();

				JOptionPane.showMessageDialog(null, "Request successfully added");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Adding request cancelled");
		}

		saveRequestMade();
	}

	private void saveRequestMade() {
		FileWriter rMade;
		try {
			File file = new File("requestMade.txt");
			rMade = new FileWriter(file, true);

			rMade.write(reqID + " " + fName + " " + lName + " " + email + " " + attractionID + " " + attractionName
					+ " " + message + " " + dateAndTime + "\n");

			rMade.close();
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null,
					"UNABLE TO STORE INFORMATION. " + "PLEASE CONTACT SYSTEM ADMINISTRATOR THANK YOU");
			e.printStackTrace();
		} // end of try and catch exception handling

		JOptionPane.showMessageDialog(null, "Request successfully saved");
	}// end of saveRequestMade()

	protected static void onEnter() {
		runCommand(parseCommand());
	}// end of on

	public String getAttractionID() {
		return attractionID;
	}

	public void setAttractionID(String attractionID) {
		this.attractionID = attractionID;
	}

	public String getReqID() {
		return reqID;
	}

	public void setReqID(String reqID) {
		this.reqID = reqID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAttractionName() {
		return attractionName;
	}

	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public static String parseCommand() {
		String command = commandTextField.getText();// receives the input front the text field
		String[] commandSplit = command.split(" ");// separates the input into smaller string
		int commandNum = 0, parishCode = 0, commandLen = commandSplit.length;// gets the length of words in the command
		String attractionString = "";

		if (commandSplit[0].equals("Gimmi")) {
			commandNum = 1;

			try {
				if (commandSplit[7].equals("St") || commandSplit[7].equals("St.") || commandSplit[7].equals("st")
						|| commandSplit[7].equals("st.")) {
					commandSplit[7] = "St" + commandSplit[8];
				}

				commandSplit[7] = commandSplit[7].toLowerCase();

				if (commandSplit[7].equals("kingston") || commandSplit[7].equals("standrew")
						|| commandSplit[7].equals("st.andrew")) {
					parishCode = 1;
				} else if (commandSplit[7].equals("stthomas") || commandSplit[7].equals("st.thomas")) {
					parishCode = 2;
				} else if (commandSplit[7].equals("portland")) {
					parishCode = 3;
				} else if (commandSplit[7].equals("stmary") || commandSplit[7].equals("st.mary")) {
					parishCode = 4;
				} else if (commandSplit[7].equals("stcatherine") || commandSplit[7].equals("st.catherine")) {
					parishCode = 5;
				} else if (commandSplit[7].equals("clarendon")) {
					parishCode = 6;
				} else if (commandSplit[7].equals("manchester")) {
					parishCode = 7;
				} else if (commandSplit[7].equals("stann") || commandSplit[7].equals("st.ann")) {
					parishCode = 8;
				} else if (commandSplit[7].equals("stelizabeth") || commandSplit[7].equals("stelizabeth")) {
					parishCode = 9;
				} else if (commandSplit[7].equals("stjames") || commandSplit[7].equals("st.james")) {
					parishCode = 10;
				} else if (commandSplit[7].equals("hanover")) {
					parishCode = 11;
				} else if (commandSplit[7].equals("westmoreland")) {
					parishCode = 12;
				} else if (commandSplit[7].equals("trelawny")) {
					parishCode = 13;
				} // this is a nested-if to derive the parish code

				JOptionPane.showMessageDialog(null,
						commandSplit[0] + " | it finish parse | " + parishCode + " " + commandNum);
				return (parishCode + " " + commandNum);/*
														 * the Parish name is returned along with the command number in
														 * string format to better aid in function processing
														 */
			} catch (Exception e) {
				System.out.println("Error in parish name parsing");
			}
		} else if (commandSplit[0].equals("Which")) {
			commandNum = 2;
			try {
				attractionString = command.replace("Which part have di cheapest ", "");
				attractionString = attractionString.replace(" ", "");
				attractionString = attractionString.toLowerCase();

				JOptionPane.showMessageDialog(null,
						commandSplit[0] + " | it finish parse | " + attractionString + " " + commandNum);
				return (attractionString + " "
						+ commandNum);/*
										 * the Attraction name is returned along with the command number in string
										 * format to better aid in function processing
										 */
			} catch (Exception e) {
				System.out.println("Error in attraction name parsing");
			}
		} // this nested-if is to derive which function has been inputed by the user
		return (attractionString + " " + 0);

	}// end of the parseCommand

	public static void runCommand(String commandInfo) {
		String[] parameter = commandInfo.split(" ");

		switch (Integer.parseInt(parameter[1])) {// switches case dependent on the command
		case 1:
			JOptionPane.showMessageDialog(null, "it reach case 1");
			viewParishList(Integer.parseInt(parameter[0]));// view list of attractions in parish specified
			break;

		case 2:
			JOptionPane.showMessageDialog(null, "it reach case 2 " + parameter[0]);
			viewAttraction(parameter[0]);// view attraction details for attraction specified
			break;

		default:
			JOptionPane.showMessageDialog(null,
					"Statement Incorrect...\n\nPlease re-check statement before pressing Enter again.");
			break;
		}
	}// end of runCommand

	public static void viewParishList(int parishCode) {
		String line, fileName = parishCode + ".txt";
		Object[][] rowData = {};
		String[] singleRow, columnNames = { "ID#", "Name", "Description", "Address", "Parish Code", "Entry Cost",
				"Opening Hours", "Contact #", "Photo Link", "Main Attraction" };

		int i = 0;
		JTable table;
		JFrame frame = new JFrame();
		DefaultTableModel defaultTable = new DefaultTableModel(rowData, columnNames);

		try {

			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);

				singleRow = line.split(" ");
				defaultTable.addRow(singleRow);
			}
			bufferedReader.close();

			table = new JTable(defaultTable);
			JScrollPane scrollPane = new JScrollPane(table);
			frame.add(scrollPane);

			frame.setVisible(true);
			frame.setSize(300, 300);

			JOptionPane.showMessageDialog(null, "it reach Table");
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}// end of viewParishList

	public static void viewAttraction(String attraction) {
		attraction = attraction.toLowerCase();
		String[] singleLine, columnNames = { "Name", "Address", "Contact #", "Main Attraction" };
		Object[][] rowData = {};
		String line, fileName = "placeList.txt";

		int parishCode = 0;
		JTable table;
		JFrame frame = new JFrame();
		DefaultTableModel defaultTable = new DefaultTableModel(rowData, columnNames);

		try {

			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);

				singleLine = line.split(" ");

				if (singleLine[1] == attraction) {
					String[] singleRow = { singleLine[1], singleLine[3], singleLine[7], singleLine[9] };
					defaultTable.addRow(singleRow);
				}
			}
			bufferedReader.close();

			table = new JTable(defaultTable);
			JScrollPane scrollPane = new JScrollPane(table);
			frame.add(scrollPane);

			frame.setVisible(true);
			frame.setSize(300, 300);

			JOptionPane.showMessageDialog(null, "it reach Table");
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}

	}// end of viewAttraction
}// end of class
