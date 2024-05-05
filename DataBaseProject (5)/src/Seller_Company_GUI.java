import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Seller_Company_GUI extends JFrame {
    static Random rand = new Random();
    static int randomNumber = rand.nextInt(100000);
	static int SellerID;
	static int search;

	public static int getSellerID() {
		return SellerID;
	}

    private JTextField company_ID;
    private JTextField company_name;
    private JTextField company_location;



    /////////////////////////////////////////
    private JLabel company_ID_label;
    private JLabel company_name_label;
    private JLabel company_location_label;


    private JPanel panel;
    private JLabel main_label;
    ////////////////////////////////////////
    private JButton back_button;
    private JButton Done_button;
    private JButton main_menu_button;
    public Seller_Company_GUI(){
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
        main_label=new JLabel("SELLER COMPANY");
        Font f1=new Font("arial",Font.BOLD,30);
        main_label.setFont(f1);
        main_label.setBounds(325,40,800,50);
        main_label.setForeground(Color.WHITE);
///////////////////////////////////////////////////////////
// Text Boxes



        company_ID=new JTextField();
        company_name=new JTextField();
        company_location=new JTextField();

        company_ID.setBounds(430,280,200,27);
        company_name.setBounds(430,330,200,27);
        company_location.setBounds(430,380,200,27);



        company_name.setBorder(null);
        company_ID.setBorder(null);
        company_location.setBorder(null);



//////////////////////////////////////////////////////////////
// LABELS


        company_ID_label= new JLabel("COMPANY ID ");
        company_name_label = new JLabel("COMPANY NAME");
        company_location_label = new JLabel("COMPANY LOCATION");


        company_ID_label.setBounds(260,282,200,30);
        company_name_label.setBounds(260,332,200,30);
        company_location_label.setBounds(260,382,200,30);




//////////////////////////////////////////////////////////////
// back button
        back_button=new JButton("BACK");
        back_button.setBounds(45,500,120,30);
//next button
        Done_button=new JButton("Done");
        Done_button.setBounds(725,500,120,30);
        Done_button.setBackground(Color.BLACK);
        Done_button.setForeground(Color.WHITE);
///////////////////////////////////////////////////////
        back_button.addActionListener(new MyHandler());
        Done_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////
        main_menu_button=new JButton("MAIN MENU");
        main_menu_button.addActionListener(new MyHandler());
        main_menu_button.setBounds(45,465,120,30);
        main_menu_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////


        panel.add(company_ID_label);
        panel.add(company_ID);

        panel.add(company_name_label);
        panel.add(company_name);

        panel.add(company_location_label);
        panel.add(company_location);


        panel.add(main_label);


        panel.add(main_menu_button);
        panel.add(Done_button);
        panel.add(back_button);

        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Seller company");
        setVisible(true);

        try{
            java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
            Statement stmt = mycon.createStatement();
            ResultSet result = stmt.executeQuery("select C_ID from SELLER_COMPANY where C_ID="+randomNumber+"");
            while(result.next()){
                search = result.getInt(1);
            }
        }
        catch(Exception g) {
            System.out.println(g.toString());
        }
        if(search==0){
            company_ID.setText(String.valueOf(randomNumber));
        }
        else{
            randomNumber = rand.nextInt(100000);
            company_ID.setText(String.valueOf(randomNumber));
        }

    }
    

    

   
    
    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==back_button){
                setVisible(false);
                new PurchaseMedDetailsGUI();

            }
            if(e.getSource()==Done_button){
                try {
                    Connection orcl = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    SellerID = Integer.parseInt(company_ID.getText());
                    String SellerName = company_name.getText();
                    String SellerLoc = company_location.getText();

                    int  item_id = PurchaseItemDetails.getItemID();
                    int item_quantity = PurchaseItemDetails.getItemquantity();
                    String item_name = PurchaseItemDetails.getItemName();
                    String item_type = PurchaseItemDetails.getitemType();
                    Statement stmt = orcl.createStatement();

                    int count = stmt.executeUpdate("INSERT INTO Seller_Company Values("+SellerID+",'"+SellerName+"','"+SellerLoc+"')");
                    int ct = stmt.executeUpdate("INSERT INTO PURCHASED_ITEMS Values('"+item_name+"',"+item_quantity+","+item_id+","+SellerID+",'"+item_type+"')");
                    int ctt = stmt.executeUpdate("INSERT INTO ITEM_SELLER Values("+item_id+","+SellerID+")");


                }
                catch(Exception g) {
                    System.out.println(g.toString());
                }
                setVisible(false);
                new SalePurchaseGUI();

            }

            if(e.getSource()==main_menu_button){
                setVisible(false);
                new MainMenuGUI();
            }

        }
    }

}
