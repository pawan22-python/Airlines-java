import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class View_Details {
	View_Details(String usertype) {
		Connect connect = new Connect();

		JFrame frame = new JFrame("View Flight");
		frame.setSize(800, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		/*
		 * JLabel filter = new JLabel("Filter:-"); filter.setBounds(20,5,100,30);
		 * filter.setFont(new Font("Moonspace",Font.BOLD,20)); frame.add(filter);
		 */

		JLabel search = new JLabel("SEARCH :");
		search.setBounds(10, 10, 100, 30);
		frame.add(search);

		JTextField searchfield = new JTextField();
		searchfield.setBounds(80, 15, 90, 20);
		frame.add(searchfield);
		// hint rakheko

		//

		// `flightid`,`from2`,`to2`,`arrival`,`departure`,`adultprice`,
		// `childprice`,`seatno`
		DefaultTableModel autotablerow = new DefaultTableModel();
		String[] viewcolumn = { "flightid","planeid", "from", "to", "departure", "arrival", "price" };

		JTable viewtable = new JTable();
		autotablerow.setColumnIdentifiers(viewcolumn);
		viewtable.setModel(autotablerow);

		viewtable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane viewsp = new JScrollPane(viewtable);
		viewsp.setBounds(20, 50, 750, 300);
		frame.add(viewsp);

		try {
			String sql = "SELECT * FROM flight";
			ResultSet rs = connect.display(sql);
			while (rs.next()) {
				autotablerow.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7) });
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		JButton update = new JButton("UPDATE");
		update.setBounds(300, 370, 100, 30);

		searchfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String sql;
				if (searchfield.getText().length() != 0) {
					sql = "SELECT * FROM `flight` WHERE `fid` LIKE '" + searchfield.getText().toString()
							+ "%'";
				} else {
					sql = "SELECT * FROM `flight`";
				}

				try {
					ResultSet rs = connect.display(sql);
					viewsp.repaint();
					autotablerow.getDataVector().removeAllElements();

					while (rs.next()) {
						autotablerow.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) });
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		update.addActionListener(new ActionListener() {
			String flightid;

			public void actionPerformed(ActionEvent ae) {
				int[] row = viewtable.getSelectedRows();
				for (int i = 0; i < row.length; i++) {
					flightid = (String) viewtable.getValueAt(row[i], 0);
				}
				frame.dispose();
                                new UpdateFlight(flightid);
                                
			}
		});
		JButton delete = new JButton("DELETE");
		delete.setBounds(450, 370, 80, 30);

		if (usertype.equals("admin")) {
			frame.add(update);
			frame.add(delete);
		}

		delete.addActionListener(new ActionListener() {
			String flightid;

			public void actionPerformed(ActionEvent ae) {
				int[] row = viewtable.getSelectedRows();
				for (int i = 0; i < row.length; i++) {
					flightid = (String) viewtable.getValueAt(row[i], 0);

					String sqldel = "DELETE FROM `flight` where `fid`='" + flightid + "' ";
					connect.append(sqldel, "deleted");
                                        
                                         viewtable.repaint();
                                        autotablerow.getDataVector().removeAllElements();
                                        String stringsql2="SELECT * FROM `flight`";
                                        try {
					ResultSet rs = connect.display(stringsql2);
					viewsp.repaint();
					autotablerow.getDataVector().removeAllElements();

					while (rs.next()) {
						autotablerow.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				}
			}
		});

	}
 public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new View_Details("admin");
            }
        });
    }
}
