import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PurchaseItemDetails extends JFrame {
	
	static int id;
    static int quantity;
    static String name;
    static String search_name;
    static int price;
    static int search;
    static String items_type;
    static Random rand = new Random();
    static int randomNumber = rand.nextInt(100000);
    
    
	private JTextField product_id_box;
	private JTextField product_quantity_box;
	private JTextField product_name_box;
	private JTextField product_price_box;
	private JComboBox product_type_combo;

    
    
    /////////////////////////////////////////
    private JLabel product_type_label;
    private JLabel product_name;
    private JLabel product_id;
    private JLabel product_quantity;
    private JLabel product_price;
    private JPanel panel;
    private JLabel main_label;

    ////////////////////////////////////////
    private JButton back_button;
    private JButton next_button;
    private JButton main_menu_button;

    public PurchaseItemDetails(){
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
        main_label=new JLabel("IMPORT PRODUCT");
        Font f1=new Font("arial",Font.BOLD,30);
        main_label.setFont(f1);
        main_label.setBounds(325,40,800,50);
        main_label.setForeground(Color.WHITE);
///////////////////////////////////////////////////////////
// Text Boxes
        product_name_box=new JTextField();
        product_quantity_box=new JTextField();
        product_price_box=new JTextField();
        product_id_box=new JTextField();
        String[] product_array = {"Medicine","Devices","Other_items"};
        product_type_combo = new JComboBox(product_array);


        product_id_box.setBounds(430,130,200,27);
        product_quantity_box.setBounds(430,180,200,27);
        product_name_box.setBounds(430,230,200,27);
        product_price_box.setBounds(430,280,200,27);
        product_type_combo.setBounds(430,330,200,27);

        product_id_box.setBorder(null);
        product_quantity_box.setBorder(null);
        product_price_box.setBorder(null);
        product_name_box.setBorder(null);


//////////////////////////////////////////////////////////////
// LABELS
        product_type_label = new JLabel("PRODUCT TYPE");
        product_id=new JLabel("PRODUCT ID");
        product_name=new JLabel("PRODUCT NAME");
        product_price=new JLabel("PRODUCT PRICE");
        product_quantity=new JLabel("PRODUCT QUANTITY");


        product_id.setBounds(260,132,200,30);
        product_quantity.setBounds(260,182,200,30);
        product_name.setBounds(260,232,200,30);
        product_price.setBounds(260,280,200,30);
        product_type_label.setBounds(260,330,200,30);


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

        ///////////////////////////////////////
        panel.add(product_name);
        panel.add(product_name_box);
        panel.add(main_menu_button);
        panel.add(product_id);
        panel.add(product_id_box);
        panel.add(product_quantity);
        panel.add(product_quantity_box);
        panel.add(product_price);
        panel.add(product_price_box);
        panel.add(main_label);
        panel.add(next_button);
        panel.add(back_button);
        panel.add(product_type_combo);
        panel.add(product_type_label);

        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Details for Purchase");
        setVisible(true);


        try{
            java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
            Statement stmt = mycon.createStatement();
            ResultSet result = stmt.executeQuery("select ITEM_ID from items where ITEM_ID="+randomNumber+"");
            while(result.next()){
                search = result.getInt(1);
            }
        }
        catch(Exception g) {
            System.out.println(g.toString());
        }
        if(search==0){
            product_id_box.setText(String.valueOf(randomNumber));
        }
        else{
            randomNumber = rand.nextInt(100000);
            product_id_box.setText(String.valueOf(randomNumber));
        }


    }
    
    public static int getItemID() {
    	return id;
    }
    
    public static int getItemquantity() {
    	return quantity;
    }
    
    public static String getItemName() {
    	return name;
    }
    
    public static int getitemPrice() {
    	return price;
    }
    
    public static String getitemType() {
    	return items_type;
    }
    
    public void Insert_Item_details() {

    	try{
       
             id = Integer.parseInt(product_id_box.getText());
             quantity = Integer.parseInt(product_quantity_box.getText());
             name = product_name_box.getText();
             price = Integer.parseInt(product_price_box.getText());
             items_type = (String) product_type_combo.getSelectedItem();
                   
        }   
            
        catch(Exception g){
            System.out.println(g.toString());
        }

    }

    
  
    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==back_button){
                setVisible(false);
                new SalePurchaseGUI();

            }
            if(e.getSource()==next_button){
            	Insert_Item_details();
                try{
                    java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    Statement stmt = mycon.createStatement();
                    ResultSet result = stmt.executeQuery("select I_name from items where I_name='"+product_name_box.getText()+"'");

                    while(result.next()){
                        search_name=result.getString(1);
                    }
                    if(search_name!=null){
                        JOptionPane.showMessageDialog(null,"Product Already exist Please update data");
                        setVisible(false);
                        new UpdatePurchaseItemDetails();
                    }
                    else{
                        setVisible(false);
                        new PurchaseItemCatogaryGUI();
                    }

                }catch (Exception g){
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
