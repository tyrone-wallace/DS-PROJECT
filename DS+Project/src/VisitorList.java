/**
 * Written By :
 * Tyrone Wallace - 1706903
 * Dimitri Russell - 1801488
 * Reinaldo Peno - 1803640
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//function: O(1)
public class VisitorList {
	private VisitorNode head, tail;

	//function: O(1)
	public VisitorList() {
		this(null, null);
	}

	//function: O(1)
	public VisitorList(VisitorNode head, VisitorNode tail) {
		super();
		this.head = head;
		this.tail = tail;
	}

	//function: O(1)
	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	//function: O(1)
	@SuppressWarnings("unused")
	public boolean isFull() {
		VisitorNode temp = new VisitorNode();

		if (temp == null) {
			return true;
		} else {
			temp = null;
			return false;
		}
	}

	//function: O(1)
	public void enqueue(Visitor data) {
		if (isFull()) {
			JOptionPane.showMessageDialog(null, "List is Full, unable to add item");
		} else {
			VisitorNode nextItem = new VisitorNode();
			nextItem.setData(data);

			if (isEmpty()) {
				head = nextItem;
				tail = nextItem;
			} else {
				tail.setNext(nextItem);
				tail = tail.getNext();
			}
		}
	}

	//function: O(1)
	@SuppressWarnings("unused")
	public Visitor dequeue() {
		Visitor deletedData = head.getData();
		VisitorNode toBeDeleted = head;

		if (head.getNext() == null) {
			head = null;
			tail = null;
		} else {
			head = head.getNext();
		}

		toBeDeleted = null;

		return deletedData;
	}

	//loads in requests made from file
	//function: O(n)
	public void loadFiles() {
		try {
			File file = new File("requestMade.txt");
			if (file.exists()) {
				Scanner fileReader;
				fileReader = new Scanner(file);

				String reqID, fName, lName, email, attractionID, attractionName, message, dateAndTime;
				while (fileReader.hasNext()) {
					reqID = fileReader.next();
					fName = fileReader.next();
					lName = fileReader.next();
					email = fileReader.next();
					attractionID = fileReader.next();
					attractionName = fileReader.next();
					message = fileReader.next();
					dateAndTime = fileReader.next();

					Visitor visitorData = new Visitor(reqID, fName, lName, email, attractionID, attractionName, message,
							dateAndTime);

					enqueue(visitorData);
				}
				fileReader.close();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Failed to read from file");
		}
	}

	//saves requests made by visitor to file
	//function: O(n)
	public void saveFiles() {
		FileWriter rMade;
		try {
			File file = new File("requestMade.txt");
			rMade = new FileWriter(file, false);

			VisitorNode current = head;

			while (current != null) {
				Visitor data = current.getData();

				rMade.write(data.getReqID() + " " + data.getfName() + " " + data.getlName() + " "
						+ data.getEmail() + " " + data.getAttractionID() + " " + data.getAttractionName() + " "
						+ data.getMessage() + " " + data.getDateAndTime()+"\n");

				current = current.getNext();
			}
			rMade.close();
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null,
					"UNABLE TO STORE INFORMATION. " + "PLEASE CONTACT SYSTEM ADMINISTRATOR THANK YOU");
			e.printStackTrace();
		} // end of try and catch exception handling
	}

	//Outputs the visitor request at the head of the list to the main panel in GUI
	//function: O(1)
	public JTable displayHead() {
		Object[] columnNames = { "Request ID", "First Name", "Last Name", "Email", "Attraction ID", "Attraction Name",
				"Message", "Date and Time" };

		Visitor data = head.getData();
		Object[][] rowData = { { data.getReqID(), data.getfName(), data.getlName(), data.getEmail(),
				data.getAttractionID(), data.getAttractionName(), data.getMessage(), data.getDateAndTime() } };

		JTable table = new JTable(rowData, columnNames);

		for (int i = 0; i < 8; i++) {
			table.getColumnModel().getColumn(i).setMinWidth(200);
		}
		table.setRowHeight(25);
		// scrollPane wont work without this
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		return table;
	}

	//Outputs all requests made by visitor to main panel in GUI
	//function: O(n)
	public JTable display() {
		if (!isEmpty()) {
			Object[] columnNames = { "Request ID", "First Name", "Last Name", "Email", "Attraction ID",
					"Attraction Name", "Message", "Date and Time" };
			Object[][] rowData = {};
			DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);

			VisitorNode current = head;
			while (current != null) {
				Visitor data = current.getData();

				tableModel.addRow(new Object[] { data.getReqID(), data.getfName(), data.getlName(), data.getEmail(),
						data.getAttractionID(), data.getAttractionName(), data.getMessage(), data.getDateAndTime() });

				current = current.getNext();
			}

			JTable table = new JTable(tableModel);
			for (int i = 0; i < 8; i++) {
				table.getColumnModel().getColumn(i).setMinWidth(200);
			}
			table.setRowHeight(25);
			// scrollPane wont work without this
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			return table;
		}

		return null;
	}

	//function: O(1)
	public VisitorNode getHead() {
		return head;
	}

	//function: O(1)
	public void setHead(VisitorNode head) {
		this.head = head;
	}

	//function: O(1)
	public VisitorNode getTail() {
		return tail;
	}

	//function: O(1)
	public void setTail(VisitorNode tail) {
		this.tail = tail;
	}

}
