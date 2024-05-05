import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePurchaseMedDetailsGUI extends JFrame {
		static String Med_Categ ;
		static String Med_Formula;
		static String Med_Exp;
		static String Manf_Name;
		static String Manf_Loc;
		static int Manf_ID;
		static int sellerID;
    static String [][]MedName=new String[20][1];
    static String[][] array = new String[17][6];
		
	
    private JComboBox med_category_box;
    private JTextField Medicine_formula_text;
    private JTextField Expiry_Date_text;

    private JTextField manufacturer_ID;
    private JTextField manufacturer_name;
    private JTextField manufacturer_location;
    private JTextField medName_text;

    /////////////////////////////////////////
    private JLabel Formula_label;
    private JLabel med_category_label;
    private JLabel expiry_date_label;
    private JLabel manufacturer_name_label;
    private JLabel manufacturer_ID_label;
    private JLabel manufacturer_location_label;
    private JLabel medName_label;
    private JPanel panel;
    private JLabel main_label;
    ////////////////////////////////////////
    private JButton back_button;
    private JButton Done_button;
    private JButton main_menu_button;
    private JButton Search_button;
    public UpdatePurchaseMedDetailsGUI(){
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
        main_label=new JLabel("UPDATE PRODUCT");
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
        medName_text=new JTextField();



        med_category_box.setBounds(430,130,200,27);
        Expiry_Date_text.setBounds(430,180,200,27);
        Medicine_formula_text.setBounds(430,230,200,27);
        manufacturer_ID.setBounds(430,280,200,27);
        manufacturer_name.setBounds(430,330,200,27);
        manufacturer_location.setBounds(430,380,200,27);
        medName_text.setBounds(300,500,200,27);


        Expiry_Date_text.setBorder(null);
        Medicine_formula_text.setBorder(null);
        manufacturer_name.setBorder(null);
        manufacturer_ID.setBorder(null);
        manufacturer_location.setBorder(null);
        medName_text.setBorder(null);
        //setting item name.
        medName_text.setText(UpdatePurchaseItemDetails.getItemName());


//////////////////////////////////////////////////////////////
// LABELS
        med_category_label = new JLabel("MEDICINE CATEGORY");
        expiry_date_label=new JLabel("EXPIRY DATE");
        Formula_label=new JLabel("MEDICINE FORMULA");
        manufacturer_name_label = new JLabel("MANUFACTURER NAME");
        manufacturer_ID_label = new JLabel("MANUFACTURER ID");
        manufacturer_location_label = new JLabel("MANUFACTURER LOCATION");
        medName_label = new JLabel("SEARCH NAME");

        med_category_label.setBounds(260,132,200,30);
        expiry_date_label.setBounds(260,182,200,30);
        Formula_label.setBounds(260,232,200,30);
        manufacturer_ID_label.setBounds(260,282,200,30);
        manufacturer_name_label.setBounds(260,332,200,30);
        manufacturer_location_label.setBounds(260,382,200,30);
        medName_label.setBounds(200,500,200,30);




//////////////////////////////////////////////////////////////
// back button
        back_button=new JButton("BACK");
        back_button.setBounds(45,500,120,30);
//next button
        Done_button=new JButton("Done");
        Done_button.setBounds(725,500,120,30);
        Done_button.setBackground(Color.BLACK);
        Done_button.setForeground(Color.WHITE);
        Search_button= new JButton("SEARCH");
        Search_button.setBounds(550,500,120,30);
        Search_button.setBackground(Color.BLACK);
        Search_button.setForeground(Color.WHITE);
        Search_button.addActionListener(new MyHandler());
///////////////////////////////////////////////////////
        back_button.addActionListener(new MyHandler());
        Done_button.addActionListener(new MyHandler());
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
        panel.add(Done_button);
        panel.add(back_button);
        panel.add(medName_label);
        panel.add(medName_text);
        panel.add(Search_button);

        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Purchase Medicine");
        setVisible(true);

    }
    
   
    class MyHandler implements ActionListener {
    	// public void IM_C_MF(int P_id, int P_quantity, String P_name, int P_price, String P_type) {
    		       	
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==back_button){
                setVisible(false);
                new UpdatePurchaseItemDetails();

            }
            if(e.getSource()==Done_button){
                try {
                    Connection orcl = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    Med_Categ = med_category_box.getSelectedItem().toString();
                    Med_Formula = Medicine_formula_text.getText();
                    Med_Exp = Expiry_Date_text.getText();
                    Manf_ID =Integer.parseInt(manufacturer_ID.getText());
                    Manf_Name = manufacturer_name.getText();
                    Manf_Loc = manufacturer_location.getText();
                    int  item_id = UpdatePurchaseItemDetails.getItemID();
                    int item_quantity = UpdatePurchaseItemDetails.getItemquantity();
                    String item_name = UpdatePurchaseItemDetails.getItemName();
                    int item_price = UpdatePurchaseItemDetails.getitemPrice();
                    String item_type = UpdatePurchaseItemDetails.getitemType();

                    Statement stmt2 = orcl.createStatement();
                    ResultSet result=stmt2.executeQuery("Select SELLER_COMPANY_C_ID from PURCHASED_ITEMS where ITEMS_ITEM_ID="+item_id+"");
                    while(result.next()) {
                        sellerID = result.getInt(1);
                    }

                    Statement stmt = orcl.createStatement();
                    int count1 = stmt.executeUpdate("UPDATE MANUFACTURING_COMPANY set C_ID="+Manf_ID+",C_NAME='"+Manf_Name+"',C_LOCATION='"+Manf_Loc+"' WHERE C_ID="+Manf_ID+"");
                    int count2 = stmt.executeUpdate("UPDATE ITEMS set ITEM_ID="+item_id+",Quantity="+item_quantity+",I_name='"+item_name+"',price="+item_price+",MANUFACTURING_COMPANY_C_ID="+Manf_ID+",ITEMS_TYPE='"+item_type+"' WHERE ITEM_ID="+item_id+"");
                    int count3 = stmt.executeUpdate("UPDATE MEDICINE set ITEM_ID="+item_id+",MED_FORMULA='"+Med_Formula+"',EXP_DATE='"+Med_Exp+"',MEDICINE_CATEGORY_MED_CATEGORY='"+Med_Categ+"' WHERE ITEM_ID="+item_id+"");
                    int count4 = stmt.executeUpdate("UPDATE PURCHASED_ITEMS set NAMEE='"+item_name+"',Quantity="+item_quantity+",ITEMS_ITEM_ID="+item_id+",SELLER_COMPANY_C_ID="+sellerID+",typee='"+item_type+"' WHERE ITEMS_ITEM_ID="+item_id+"");


                }

                catch(Exception g) {
                    System.out.println(g.toString());
                }
                setVisible(false);
                new MainMenuGUI();

            }

            if(e.getSource()==main_menu_button){
                setVisible(false);
                new MainMenuGUI();
            }

            if(e.getSource()==Search_button){
                try{
                    java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    Statement stmt = mycon.createStatement();
                    ResultSet result = stmt.executeQuery("select MEDICINE_CATEGORY.MED_CATEGORY , medicine.med_formula , medicine.exp_date ,items.manufacturing_company_c_id,manufacturing_company.c_name,manufacturing_company.c_location from Medicine  inner join items on Medicine.item_id=Items.item_id inner join manufacturing_company on items.manufacturing_company_c_id=manufacturing_company.c_id inner join MEDICINE_CATEGORY on medicine.medicine_category_med_category=medicine_category.med_category where I_name='"+medName_text.getText()+"'");
                    int k=0 ;
                    while(result.next()){
                        array[k][0] = result.getString(1);
                        array[k][1] = result.getString(2);
                        array[k][2] = result.getString(3);
                        array[k][3] = String.valueOf(result.getInt(4));
                        array[k][4] = result.getString(5);
                        array[k][5] = result.getString(6);
                        k++;
                    }

                        med_category_box.setSelectedItem(array[0][0]);
                        Medicine_formula_text.setText(array[0][1]);
                        Expiry_Date_text.setText(array[0][2]);
                        manufacturer_ID.setText(array[0][3]);
                        manufacturer_name.setText(array[0][4]);
                        manufacturer_location.setText(array[0][5]);
                }
                catch(Exception g) {
                    System.out.println(g.toString());
                }

            }

        }
    }
    
   
    

}
