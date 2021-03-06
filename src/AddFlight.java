
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class AddFlight {
    void clear(JTextField objname){
        objname.setText("");
    }
    
    
	public AddFlight() {
		Connect connect = new Connect();
		
		
		JFrame frame=new JFrame("Add Flight");
		frame.setSize(400, 350);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		//new
		JLabel flightid = new JLabel("Flight Id");
		flightid.setBounds(5,10,80,30);
		frame.add(flightid);
		
		/*
		 * JButton btn=new JButton("Search"); btn.setBounds(200,15,80,20);
		 * frame.add(btn);
		 */
		JTextField tflightid = new JTextField();
		tflightid.setBounds(90,15,80,20);
		frame.add(tflightid);
		
		
		
		//new
				JLabel from = new JLabel("From");
				from.setBounds(5,40,80,30);
				frame.add(from);
				
                                
                                
                                //
                                	JComboBox<String> Cfrom = new JComboBox<String>();
			Cfrom.setBounds(90,45,80,20);
			frame.add(Cfrom);
			try {
			String sqllocationinput = "SELECT * FROM location";
			ResultSet rs = connect.display(sqllocationinput);
			while (rs.next()) {
				Cfrom.addItem(rs.getString(1));

			}

		} catch (Exception abc) {
			JOptionPane.showMessageDialog(null, abc);
		}

                                
                                
				//
                        JLabel to = new JLabel("To");
				to.setBounds(5,70,80,30);
				frame.add(to);
                                
                        JComboBox<String> Cto = new JComboBox<String>();
			Cto.setBounds(90,75,80,20);
			frame.add(Cto);
			try {
			String sqllocationinput = "SELECT * FROM location";
			ResultSet rs = connect.display(sqllocationinput);
			while (rs.next()) {
				Cto.addItem(rs.getString(1));

			}

		} catch (Exception abc) {
			JOptionPane.showMessageDialog(null, abc);
		}
                        //
				
				
				//new
				
				
				
				
				
				//new
				JLabel arrival = new JLabel("Arrival");
				arrival.setBounds(5,100,80,30);
				frame.add(arrival);
				
				JTextField tarrival = new JTextField();
				tarrival.setBounds(90,105,80,20);
				frame.add(tarrival);
		
				
				
				//new
				JLabel departure= new JLabel("Departure");
				departure.setBounds(5,130,80,30);
				frame.add(departure);
				
				JTextField tdeparture = new JTextField();
				tdeparture.setBounds(90,135,80,20);
				frame.add(tdeparture);

				
				
				//new
				JLabel aprice= new JLabel("Adult price");
				aprice.setBounds(5,160,80,30);
				frame.add(aprice);
				
				JTextField taprice = new JTextField();
				taprice.setBounds(90,165,80,20);
				frame.add(taprice);
				
				
				
				//new
				
				
				
				JLabel seat=new JLabel("Seats");
				seat.setBounds(5,190,80,30);
				frame.add(seat);
				
				JTextField texts=new JTextField();
				texts.setBounds(90,195,80,20);
				frame.add(texts);		
				
				
				//new buttons\				
				
				
				JButton addflights = new JButton("Add");
				addflights.setBounds(60,270,80,20);
		
				frame.add(addflights);
		/*
		 * JButton reset = new JButton("Update"); reset.setBounds(150,270,80,20);
		 * frame.add(reset); JButton delete=new JButton("Delete");
		 * delete.setBounds(240,270,80,20); frame.add(delete);
		 */
				
				addflights.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						try {
							//string collection
							
							
							  String sflightid = tflightid.getText().toString(); 
							  String sfrom =  Cfrom.getSelectedItem().toString();
							  String stto = Cto.getSelectedItem().toString();
							  String starrival = tarrival.getText().toString();
							  String stdeparture = tdeparture.getText().toString();
							  int staprice=  Integer.parseInt(taprice.getText().toString());
							  
							  int sseat= Integer.parseInt(texts.getText().toString());
							  
							  String sqlins="INSERT INTO flightdetails(`flightid`,`from2`,`to2`,`arrival`,`departure`,`price`,`seatno`) VALUES('"+sflightid+"','"+sfrom+"','"+stto+"','"+starrival+"','"+stdeparture+"','"+staprice+"','"+sseat+"')";
								
							  
							  //string collection ends here			
							connect.append(sqlins, "inserted");
                                                        clear(tflightid);
                                                        clear(tarrival);
                                                        clear(tdeparture);
                                                        clear(taprice);
                                                        clear(texts);
                                                                                                                
						}catch(Exception e) {
							JOptionPane.showMessageDialog(null,e);
						}
						
						
					}
				});
				
				
			
	
	
	
	
	
	}
	
	public static void main(String[]args) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
			new AddFlight();
		}
		});
	}
}





