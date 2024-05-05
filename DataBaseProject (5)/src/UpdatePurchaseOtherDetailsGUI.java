import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePurchaseOtherDetailsGUI extends JFrame {

    static String [][]MedName=new String[20][1];
    static String[][] array = new String[17][6];
	static String Manf_Name;
	static String Manf_Loc;
	static int Manf_ID;
	static int sellerID;

	private JTextField manufacturing_date_text;
    private JTextField manufacturer_ID;
    private JTextField manufacturer_name;
    private JTextField manufacturer_location;
    private JTextField medName_text;



    /////////////////////////////////////////
    private JLabel manuf_date_label;
    private JLabel manufacturer_ID_label;
    private JLabel manufacturer_name_label;
    private JLabel manufacturer_location_label;
    private JLabel medName_label;


    private JPanel panel;
    private JLabel main_label;
    ////////////////////////////////////////
    private JButton back_button;
    private JButton done_button;
    private JButton main_menu_button;
    private JButton Search_button;
    public UpdatePurchaseOtherDetailsGUI(){
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


        manufacturing_date_text=new JTextField();

        manufacturer_ID=new JTextField();
        manufacturer_name=new JTextField();
        manufacturer_location=new JTextField();
        medName_text=new JTextField();

        manufacturing_date_text.setBounds(430,230,200,27);
        manufacturer_ID.setBounds(430,280,200,27);
        manufacturer_name.setBounds(430,330,200,27);
        manufacturer_location.setBounds(430,380,200,27);
        medName_text.setBounds(300,500,200,27);



        manufacturing_date_text.setBorder(null);
        manufacturer_name.setBorder(null);
        manufacturer_ID.setBorder(null);
        manufacturer_location.setBorder(null);
        medName_text.setBorder(null);
        //setting item name.
        medName_text.setText(UpdatePurchaseItemDetails.getItemName());



//////////////////////////////////////////////////////////////
// LABELS


        manuf_date_label =new JLabel("MANUFACTURING DATE");
        manufacturer_ID_label= new JLabel("MANUFACTURER ID ");
        manufacturer_name_label = new JLabel("MANUFACTURER NAME");
        manufacturer_location_label = new JLabel("MANUFACTURER LOCATION");
        medName_label = new JLabel("SEARCH NAME");


        manuf_date_label.setBounds(260,232,200,30);
        manufacturer_ID_label.setBounds(260,282,200,30);
        manufacturer_name_label.setBounds(260,332,200,30);
        manufacturer_location_label.setBounds(260,382,200,30);
        medName_label.setBounds(200,500,200,30);




//////////////////////////////////////////////////////////////
// back button
        back_button=new JButton("BACK");
        back_button.setBounds(45,500,120,30);
//next button
        done_button=new JButton("Next");
        done_button.setBounds(725,500,120,30);
        done_button.setBackground(Color.BLACK);
        done_button.setForeground(Color.WHITE);
        Search_button= new JButton("SEARCH");
        Search_button.setBounds(550,500,120,30);
        Search_button.setBackground(Color.BLACK);
        Search_button.setForeground(Color.WHITE);
        Search_button.addActionListener(new MyHandler());
///////////////////////////////////////////////////////
        back_button.addActionListener(new MyHandler());
        done_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////
        main_menu_button=new JButton("MAIN MENU");
        main_menu_button.addActionListener(new MyHandler());
        main_menu_button.setBounds(45,465,120,30);
        main_menu_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////
        panel.add(manufacturing_date_text);
        panel.add(manuf_date_label);


        panel.add(manufacturer_ID_label);
        panel.add(manufacturer_ID);

        panel.add(manufacturer_name_label);
        panel.add(manufacturer_name);

        panel.add(manufacturer_location_label);
        panel.add(manufacturer_location);


        panel.add(main_label);


        panel.add(main_menu_button);
        panel.add(done_button);
        panel.add(back_button);
        panel.add(medName_label);
        panel.add(medName_text);
        panel.add(Search_button);

        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Purchase Other items");
        setVisible(true);

    }
    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==back_button){
                setVisible(false);
                new UpdatePurchaseItemDetails();

            }
            
            if(e.getSource() == done_button){

                try {
                    Connection orcl = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    String manufacturing_date = manufacturing_date_text.getText();
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
                    int count3 = stmt.executeUpdate("UPDATE OTHER_ITEMS set ITEM_ID="+item_id+",MANF_DATE='"+manufacturing_date+"' WHERE ITEM_ID="+item_id+"");
                    int count4 = stmt.executeUpdate("UPDATE PURCHASED_ITEMS set NAMEE='"+item_name+"',Quantity="+item_quantity+",ITEMS_ITEM_ID="+item_id+",SELLER_COMPANY_C_ID="+sellerID+",typee='"+item_type+"' WHERE ITEMS_ITEM_ID="+item_id+"");

                }

                catch(Exception g) {
                    g.printStackTrace();
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
                    ResultSet result = stmt.executeQuery("select other_items.manf_date,MANUFACTURING_COMPANY.C_id,MANUFACTURING_COMPANY.C_name,MANUFACTURING_COMPANY.C_Location from MANUFACTURING_COMPANY inner join  Items on manufacturing_company.c_id=items.manufacturing_company_c_id inner join Other_items on items.item_id=other_items.item_id where I_name='"+medName_text.getText()+"'");
                    int k=0 ;
                    while(result.next()){
                        array[k][0] = result.getString(1);
                        array[k][1] = String.valueOf(result.getInt(2));
                        array[k][2] = result.getString(3);
                        array[k][3] = result.getString(4);
                        k++;
                    }

                    manufacturing_date_text.setText(array[0][0]);
                    manufacturer_ID.setText(array[0][1]);
                    manufacturer_name.setText(array[0][2]);
                    manufacturer_location.setText(array[0][3]);
                }
                catch(Exception g) {
                    System.out.println(g.toString());
                }

            }

        }
    }

}
