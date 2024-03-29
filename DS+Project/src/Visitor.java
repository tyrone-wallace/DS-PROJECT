/**
 * Written By :
 * Tyrone Wallace - 1706903
 * Dimitri Russell - 1801488
 * Reinaldo Peno - 1803640
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({ "serial", "unused" })
//class: O(n)
public class Visitor extends JFrame {

	private JPanel contentPane;
	private JTextField dateBox;
	private JTextField textField;
	private static JTextField txtInstructions;
	private static JTextField commandTextField;
	private static Panel hostPanel;

	private String attractionID, reqID, fName, lName, email, attractionName, message, dateAndTime;
	private VisitorList requestList = new VisitorList();
	private static Tree locationTree = new Tree();

	
	/**
	 * Launch the Visitor Frame. Default constructors are the main display.
	 */

	//function: O(nlog(n))
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visitor frame = new Visitor();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.loadID();
					// populateTree();
					locationTree.populate();
				} catch (Exception e) {
					e.printStackTrace();
				} // Exception Handling
			}
		});
	}

	/**
	 * Create the frame.
	 */

	//function: O(1)
	public Visitor(String defaultValue) {
		this(defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue, defaultValue,
				defaultValue);
	}

	//function: O(1)
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

	/*Creates visitor frame in GUI*/
	//function: O(1)
	public Visitor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 590);
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
		lblDIGoodPlaceDem.setBounds(102, 24, 495, 56);
		contentPane.add(lblDIGoodPlaceDem);

		// Date: Display Current Date
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateBox = new JTextField();
		dateBox.setEditable(false);
		dateBox.setText(dateFormat.format(currentDate));

		dateBox.setColumns(10);
		dateBox.setBounds(54, 4, 86, 22);
		contentPane.add(dateBox);

		// Date: Label
		JLabel label = new JLabel("DATE");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(10, 0, 51, 28);
		contentPane.add(label);

		// Separates sections of display with a line
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 78, 691, 2);
		contentPane.add(separator);

		// Panel in Frame
		Panel panel = new Panel();
		panel.setBounds(10, 86, 691, 47);
		contentPane.add(panel);
		panel.setLayout(null);

		// Display
		JButton btnUseCreole = new JButton("Enter Creole Commands");
		btnUseCreole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					viewCreoleList();
				} catch (Exception e) {
					e.printStackTrace();
				} // Catches Exception if thrown from function
			}
		});
		btnUseCreole.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUseCreole.setBounds(0, 0, 297, 47);
		panel.add(btnUseCreole);

		JButton btnMakeRequest = new JButton("Make Request");
		btnMakeRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeRequest();
			}
		});

		btnMakeRequest.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMakeRequest.setBounds(395, 0, 297, 47);
		panel.add(btnMakeRequest);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 139, 691, 2);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 552, 846, 2);
		contentPane.add(separator_2);

		hostPanel = new Panel();
		hostPanel.setBackground(Color.GRAY);
		hostPanel.setLayout(null);
		hostPanel.setBounds(10, 147, 691, 311);
		contentPane.add(hostPanel);

		textField = new JTextField();
		textField.setBounds(295, 526, 116, 28);
		contentPane.add(textField);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("Copyright \u00A9 2019");
		textField.setEditable(false);
		textField.setColumns(10);

		JButton btnViewCreolePhrases = new JButton("View Creole Phrases");
		btnViewCreolePhrases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewCreoleTranslation();
			}
		});
		btnViewCreolePhrases.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnViewCreolePhrases.setBounds(10, 464, 297, 47);
		contentPane.add(btnViewCreolePhrases);

		JButton btnSaveExit = new JButton("Save & Exit");
		btnSaveExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestList.saveFiles();
				saveID();
				System.exit(0);
			}
		});
		btnSaveExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSaveExit.setBounds(404, 464, 297, 47);
		contentPane.add(btnSaveExit);
	}// end of Visitor

	/**
	 * Utilities .
	 */

	//Outputs image of creole statements and their translations to the main Panel in GUI
	//function: O(1)
	public void viewCreoleTranslation() {
		try{
            File image2 = new File("creolephrases.jpg");
            final Image image;
            image = ImageIO.read(image2);
            
            final JLabel imageLabel = new JLabel(new ImageIcon(image));
    		final JScrollPane scroll = new JScrollPane(imageLabel);
    		
    		hostPanel.removeAll();
    		hostPanel.setLayout(new BorderLayout());
    		hostPanel.add(scroll);
    		hostPanel.revalidate();
        }
        catch (IOException e){
            e.printStackTrace();
        }
	}

	//Loads id number from file
	//function: O(1)
	public void loadID() {
		try {
			File file = new File("startingID.txt");

			Scanner fileReader;
			fileReader = new Scanner(file);

			attractionID = fileReader.next();
			reqID = fileReader.next();

			fileReader.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Failed to read from file");
		}
	}

	//Saves id number to file
	//function: O(1)
	public void saveID() {
		try {
			File file = new File("startingID.txt");

			FileWriter fileWriter = new FileWriter(file, false);

			fileWriter.write(attractionID + " " + reqID);

			fileWriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error saving to file");
		}
	}

	//Outputs the creole commands used to search for certain criteria to the main panel in GUI
	//function: O(1)
	public static void viewCreoleList() {
		hostPanel.removeAll();
		hostPanel.setLayout(new GridLayout(5,1));

		JButton command1 = new JButton("Gimmi all a di place dem inna [Parish Name]");

		command1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				commandTextField.setText("Gimmi all a di place dem inna ");
			}
		});

		txtInstructions = new JTextField();
		txtInstructions.setBounds(0, 0, 691, 35);

		txtInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		txtInstructions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInstructions.setText("SELECT ONE OF THE OPTIONS BELOW OR TYPE COMMAND MANUALLY, THEN PRESS ENTER.");
		txtInstructions.setEditable(false);
		hostPanel.add(txtInstructions);

		command1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		command1.setBounds(0, 35, 691, 56);
		hostPanel.add(command1);

		JButton command2 = new JButton("Which part have di cheapest [Attraction Name]");
		command2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandTextField.setText("Which part have di cheapest ");
			}
		});
		command2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		command2.setBounds(0, 91, 691, 56);
		hostPanel.add(command2);

		commandTextField = new JTextField();
		commandTextField.setHorizontalAlignment(SwingConstants.CENTER);
		commandTextField.setBounds(0, 147, 691, 53);
		hostPanel.add(commandTextField);
		commandTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		commandTextField.setColumns(10);

		JButton btnCommandEnter = new JButton("ENTER");
		btnCommandEnter.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCommandEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onEnter();
			}
		});
		btnCommandEnter.setBounds(280, 200, 151, 44);
		hostPanel.add(btnCommandEnter);

		hostPanel.revalidate();
	}// end of viewCreoleList()

	//Displays the information of the location that is at the file line passed as an argument
	//function: O(1)
	public int displayPlace(int line) {
		int choice = 0;
		try {
			String location = Files.readAllLines(Paths.get("placeList.txt")).get(line-1);
			choice = JOptionPane.showConfirmDialog(null, location, "Is this the location you are requesting for?",
					JOptionPane.YES_NO_OPTION);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Cause not display");
			e1.printStackTrace();
		}

		return choice;
	}

	//Allows visitor to make request on place added to system
	//function: O(n)
	public void makeRequest() {
		int arrSize = Integer.parseInt(attractionID) - 100;

		if (arrSize == 0) {
			JOptionPane.showMessageDialog(null, "No places have been added to request information for");
		} else {

			String[] availableID = new String[arrSize];

			for (int i = 0; i < arrSize; i++) {
				availableID[i] = Integer.toString(100 + i);
			}

			JTextField fName = new JTextField(), lName = new JTextField(), email = new JTextField(),
					attractionName = new JTextField(), message = new JTextField();

			Object form[] = { "First Name", fName, "Last Name", lName, "Email Address", email, "Attraction Name",
					attractionName, "Message", message };

			String requestAttractionID = (String) JOptionPane.showInputDialog(null,
					"Select attraction ID# from list below\n\n", "Attraction ID's Found", JOptionPane.QUESTION_MESSAGE,
					null, availableID, availableID[0]);

			if (requestAttractionID == null)
				JOptionPane.showMessageDialog(null, "Operation cancelled");
			else {
				int locationInFile = locationTree.search(Integer.parseInt(requestAttractionID));
				int choice = displayPlace(locationInFile);

				if (choice == JOptionPane.OK_OPTION) {
					choice = JOptionPane.showConfirmDialog(null, form, "Enter The Following Location Information",
							JOptionPane.OK_CANCEL_OPTION);

					if (choice == JOptionPane.OK_OPTION) {
						if ((fName.getText()).isEmpty() || (lName.getText()).isEmpty() || (email.getText()).isEmpty()
								|| (attractionName.getText()).isEmpty() || (message.getText()).isEmpty()) {

							JOptionPane.showMessageDialog(null,
									"No field should be left empty, request adding canceled");
						} else {
							Visitor data = new Visitor(reqID, fName.getText().replace(" ", "_"),
									lName.getText().replace(" ", "_"), email.getText().replace(" ", "_"),
									requestAttractionID.replace(" ", "_"), attractionName.getText().replace(" ", "_"),
									message.getText().replace(" ", "_"), dateBox.getText());
							requestList.enqueue(data);

							reqID = Integer.toString(Integer.parseInt(reqID) + 1);

							JOptionPane.showMessageDialog(null, "Request successfully made");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Operation Cancelled");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Confirm location id and retry");
				}
			}
		}
	}

	public static void onEnter() {
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

		if (commandSplit[0].equals("Gimmi")) {// looks for the first command
			commandNum = 1;

			try {
				if (commandSplit[7].equals("St") || commandSplit[7].equals("St.") || commandSplit[7].equals("st")
						|| commandSplit[7].equals("st.")) {// edits the string for ease of selection
					commandSplit[7] = "St" + commandSplit[8];
				}

				commandSplit[7] = commandSplit[7].toLowerCase();// all information is kept in lower case to increase
																// efficiency of search

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

				return (parishCode + " " + commandNum);/*
														 * the Parish name is returned along with the command number in
														 * string format to better aid in function processing
														 */
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Error in parish name parsing");
			}
		} else if (commandSplit[0].equals("Which")) {// looks for the second command
			commandNum = 2;
			try {
				attractionString = command.replace("Which part have di cheapest ", "");// removes unwanted section of
																						// string
				attractionString = attractionString.replace(" ", "");// removes spaces to make search easier
				attractionString = attractionString.toLowerCase();// all information is kept in lower case to increase
																	// efficiency of search

				return (attractionString + " "
						+ commandNum);/*
										 * the Attraction name is returned along with the command number in string
										 * format to better aid in function processing
										 */
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Error in attraction name parsing");
			}
		} // this nested-if is to derive which function has been inputed by the user
		return (attractionString + " " + 0);// returns the string to search for along with the command number default to
											// call onto an error

	}// end of parseCommand

	public static void runCommand(String commandInfo) {// runs the command called onto
		String[] parameter = commandInfo.split(" ");

		switch (Integer.parseInt(parameter[1])) {// switches case dependent on the command
		case 1:
			viewParishList(Integer.parseInt(parameter[0]));// view list of attractions in parish specified
			break;

		case 2:
			viewAttraction(parameter[0]);// view attraction details for cheapest attraction specified
			break;

		default:
			JOptionPane.showMessageDialog(null,
					"Statement Incorrect...\n\nPlease re-check statement before pressing Enter again.");// error message
																										// displayed
			break;
		}
	}// end of runCommand

	//function: O(n)
	public static void viewParishList(int parishCode) {// makes a table with all the attractions in a parish
		String line, fileName = parishCode + ".txt";
		Object[][] rowData = {};
		String[] singleRow, columnNames = { "ID#", "Name", "Description", "Address", "Parish Code", "Entry Cost",
				"Opening Hours", "Contact #", "Photo Link", "Main Attraction" };

		JTable table;
		JFrame frame = new JFrame();
		DefaultTableModel defaultTable = new DefaultTableModel(rowData, columnNames);

		try {

			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {// adds all lines of the parish file to the table
				singleRow = line.split(" ");
				defaultTable.addRow(singleRow);
			}
			bufferedReader.close();

			if (defaultTable.getRowCount() > 0) {// formats table
				table = new JTable(defaultTable);

				for (int i = 0; i < 10; i++) {
					table.getColumnModel().getColumn(i).setMinWidth(200);
				}
				table.setRowHeight(25);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

				JScrollPane scrollPane = new JScrollPane(table);
				frame.getContentPane().add(scrollPane);

				frame.setSize(600, 400);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Table does not contain any data.");// error message to warn user of
																						// an empty parish
			}

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "File does not exist.");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "File Reading Error.");
		}
	}// end of viewParishList

	//function: O(n)
	public static void viewAttraction(String attraction) {// searches for the attraction name in a file containing all
															// attractions then sorts the cheapest of the attraction
															// into the table
		attraction = attraction.toLowerCase();
		String[] singleLine, columnNames = { "Name", "Address", "Contact #", "Main Attraction" };
		Object[][] rowData = {};
		String line, fileName = "placeList.txt";

		int cheapest = 0;
		JTable table;
		JFrame frame = new JFrame();
		DefaultTableModel defaultTable = new DefaultTableModel(rowData, columnNames);

		try {

			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {// reads from the file
				singleLine = line.split(" ");

				if (singleLine[1].equals(attraction)) {// confirms the attraction name is equivalent
					if (cheapest == 0) {// sets the cheapest of the attraction based on the first of its name
						cheapest = Integer.parseInt(singleLine[5]);
						String[] singleRow = { singleLine[1], singleLine[3], singleLine[7], singleLine[9] };
						defaultTable.addRow(singleRow);
					} else if (Integer.parseInt(singleLine[5]) < cheapest) {// finds cheaper attractions of its name
						cheapest = Integer.parseInt(singleLine[5]);
						defaultTable.setRowCount(0);
						String[] singleRow = { singleLine[1], singleLine[3], singleLine[7], singleLine[9] };
						defaultTable.addRow(singleRow);
					} else if (Integer.parseInt(singleLine[5]) == cheapest) {// adds more of the attraction to the
																				// cheapest table given they have the
																				// same cost
						cheapest = Integer.parseInt(singleLine[5]);
						String[] singleRow = { singleLine[1], singleLine[3], singleLine[7], singleLine[9] };
						defaultTable.addRow(singleRow);
					}
				}
			}

			bufferedReader.close();

			if (defaultTable.getRowCount() > 0) {// formats table
				table = new JTable(defaultTable);

				for (int i = 0; i < 4; i++) {
					table.getColumnModel().getColumn(i).setMinWidth(200);
				}
				table.setRowHeight(25);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

				JScrollPane scrollPane = new JScrollPane(table);
				frame.getContentPane().add(scrollPane);

				frame.setSize(600, 400);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Table does not contain any data.");// error message to warn the
																						// user that no attractions were
																						// found
			}

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "File does not exist.");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "File Reading Error.");
		}
	}// end of viewAttraction
}// end of class
