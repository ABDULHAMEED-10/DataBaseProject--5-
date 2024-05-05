import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PurchaseMedDetailsGUI extends JFrame {
		static String Med_Categ ;
		static String Med_Formula;
		static String Med_Exp;
		static String Manf_Name;
		static String Manf_Loc;
		static int Manf_ID;
		static int search;
        static Random rand = new Random();
        static int randomNumber = rand.nextInt(100000);
		
	
    private JComboBox med_category_box;
    private JTextField Medicine_formula_text;
    private JTextField Expiry_Date_text;

    private JTextField manufacturer_ID;
    private JTextField manufacturer_name;
    private JTextField manufacturer_location;


    /////////////////////////////////////////
    private JLabel Formula_label;
    private JLabel med_category_label;
    private JLabel expiry_date_label;
    private JLabel manufacturer_name_label;
    private JLabel manufacturer_ID_label;
    private JLabel manufacturer_location_label;

    private JPanel panel;
    private JLabel main_label;
    ////////////////////////////////////////
    private JButton back_button;
    private JButton next_button;
    private JButton main_menu_button;

    public PurchaseMedDetailsGUI(){
        panel=new JPanel();

        ImageIcon img = new ImageIcon("main_menu2.jpg");
        panel=new JPanel(){

            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(),getHeight(),null);
            }
        };
        panel.setLayout(null);
        main_label=new JLabel("PURCHASE PRODUCT");
        Font f1=new Font("arial",Font.BOLD,30);
        main_label.setFont(f1);
        main_label.setBounds(325,40,800,50);
        main_label.setForeground(Color.WHITE);
///////////////////////////////////////////////////////////
// Text Boxes
        String[] med_category_array={"","Syrup","Tablets","Cap","Drops","Cream"};
        med_category_box=new JComboBox(med_category_array);
        Medicine_formula_text=new JTextField();
        Expiry_Date_text=new JTextField();
        manufacturer_ID=new JTextField();
        manufacturer_name=new JTextField();
        manufacturer_location=new JTextField();




        med_category_box.setBounds(430,130,200,27);
        Expiry_Date_text.setBounds(430,180,200,27);
        Medicine_formula_text.setBounds(430,230,200,27);
        manufacturer_ID.setBounds(430,280,200,27);
        manufacturer_name.setBounds(430,330,200,27);
        manufacturer_location.setBounds(430,380,200,27);



        Expiry_Date_text.setBorder(null);
        Medicine_formula_text.setBorder(null);
        manufacturer_name.setBorder(null);
        manufacturer_ID.setBorder(null);
        manufacturer_location.setBorder(null);




//////////////////////////////////////////////////////////////
// LABELS
        med_category_label = new JLabel("MEDICINE CATEGORY");
        expiry_date_label=new JLabel("EXPIRY DATE");
        Formula_label=new JLabel("MEDICINE FORMULA");
        manufacturer_name_label = new JLabel("MANUFACTURER NAME");
        manufacturer_ID_label = new JLabel("MANUFACTURER ID");
        manufacturer_location_label = new JLabel("MANUFACTURER LOCATION");


        med_category_label.setBounds(260,132,200,30);
        expiry_date_label.setBounds(260,182,200,30);
        Formula_label.setBounds(260,232,200,30);
        manufacturer_ID_label.setBounds(260,282,200,30);
        manufacturer_name_label.setBounds(260,332,200,30);
        manufacturer_location_label.setBounds(260,382,200,30);





//////////////////////////////////////////////////////////////
// back button
        back_button=new JButton("BACK");
        back_button.setBounds(45,500,120,30);
//next button
        next_button=new JButton("Next");
        next_button.setBounds(725,500,120,30);
        next_button.setBackground(Color.BLACK);
        next_button.setForeground(Color.WHITE);

///////////////////////////////////////////////////////
        back_button.addActionListener(new MyHandler());
        next_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////
        main_menu_button=new JButton("MAIN MENU");
        main_menu_button.addActionListener(new MyHandler());
        main_menu_button.setBounds(45,465,120,30);
        main_menu_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////
        panel.add(Formula_label);
        panel.add(Medicine_formula_text);

        panel.add(expiry_date_label);
        panel.add(Expiry_Date_text);

        panel.add(manufacturer_name_label);
        panel.add(manufacturer_ID);

        panel.add(manufacturer_ID_label);
        panel.add(manufacturer_name);

        panel.add(manufacturer_location_label);
        panel.add(manufacturer_location);


        panel.add(main_label);
        panel.add(med_category_box);
        panel.add(med_category_label);

        panel.add(main_menu_button);
        panel.add(next_button);
        panel.add(back_button);


        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Purchase Medicine");
        setVisible(true);


        try{
            java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
            Statement stmt = mycon.createStatement();
            ResultSet result = stmt.executeQuery("select C_ID from MANUFACTURING_COMPANY where C_ID="+randomNumber+"");
            while(result.next()){
                search = result.getInt(1);
            }
        }
        catch(Exception g) {
            g.printStackTrace();
        }
        if(search==0){
            manufacturer_ID.setText(String.valueOf(randomNumber));
        }
        else{
            randomNumber = rand.nextInt(100000);
            manufacturer_ID.setText(String.valueOf(randomNumber));
        }

    }
    
   
    class MyHandler implements ActionListener {
    	// public void IM_C_MF(int P_id, int P_quantity, String P_name, int P_price, String P_type) {
    		       	
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==back_button){
                setVisible(false);
                new PurchaseItemCatogaryGUI();

            }
            if(e.getSource()==next_button){
            	try {
             		Connection orcl = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
             		
             		 Med_Categ = med_category_box.getSelectedItem().toString();
             		 Med_Formula = Medicine_formula_text.getText();
             		 Med_Exp = Expiry_Date_text.getText();
             		 Manf_ID =Integer.parseInt(manufacturer_ID.getText());
             		 Manf_Name = manufacturer_name.getText();
             		 Manf_Loc = manufacturer_location.getText();
             		int  item_id = PurchaseItemDetails.getItemID();
             		int item_quantity = PurchaseItemDetails.getItemquantity();
             		String item_name = PurchaseItemDetails.getItemName();
             		int item_price = PurchaseItemDetails.getitemPrice();
             		String item_type = PurchaseItemDetails.getitemType();
             		Statement stmt = orcl.createStatement();
             		
             		int count = stmt.executeUpdate("INSERT INTO MANUFACTURING_COMPANY Values("+Manf_ID+",'"+Manf_Name+"','"+Manf_Loc+"')");

                    //int c = stmt.executeUpdate("INSERT INTO MEDICINE_CATEGORY Values('"+Med_Categ+"')");
             		int ct = stmt.executeUpdate("INSERT INTO ITEMS Values("+item_id+","+item_quantity+",'"+item_name+"',"+item_price+","+Manf_ID+",'"+item_type+"')");
             		int cont = stmt.executeUpdate("INSERT INTO MEDICINE Values("+item_id+",'"+Med_Formula+"','"+Med_Exp+"','"+Med_Categ+"')");
             		
             		setVisible(false);
                    new Seller_Company_GUI();
            	
            	}
             	catch(Exception g) {
             		System.out.println(g.toString());
             	}
            }

            if(e.getSource()==main_menu_button){
                setVisible(false);
                new MainMenuGUI();
            }


        }
    }

}
