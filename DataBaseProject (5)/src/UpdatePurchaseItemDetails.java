import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePurchaseItemDetails extends JFrame {
	
	static int id;
    static int quantity;
    static String name;
    static int price;
    static String items_type;

    static String prod_Name;
    static String[][] array = new String[17][5];
    
    
	private JTextField product_id_box;
	private JTextField product_quantity_box;
	private JTextField product_name_box;
	private JTextField product_price_box;
	private JTextField product_type_text;
    private  JTextField productName;
    
    
    /////////////////////////////////////////
    private JLabel product_type_label;
    private JLabel product_name;
    private JLabel product_id;
    private JLabel product_quantity;
    private JLabel product_price;
    private JPanel panel;
    private JLabel main_label;
    private JLabel medName_label;
    ////////////////////////////////////////
    private JButton back_button;
    private JButton next_button;
    private JButton main_menu_button;
    private JButton Search_button;
    public UpdatePurchaseItemDetails(){
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
        product_name_box=new JTextField();
        product_quantity_box=new JTextField();
        product_price_box=new JTextField();
        product_id_box=new JTextField();
        product_type_text=new JTextField();
        productName=new JTextField();

        product_id_box.setBounds(430,130,200,27);
        product_quantity_box.setBounds(430,180,200,27);
        product_name_box.setBounds(430,230,200,27);
        product_price_box.setBounds(430,280,200,27);
        product_type_text.setBounds(430,330,200,27);
        productName.setBounds(300,500,200,27);

        productName.setBorder(null);
        product_id_box.setBorder(null);
        product_quantity_box.setBorder(null);
        product_price_box.setBorder(null);
        product_name_box.setBorder(null);
        product_type_text.setBorder(null);


//////////////////////////////////////////////////////////////
// LABELS
        product_type_label = new JLabel("PRODUCT TYPE");
        product_id=new JLabel("PRODUCT ID");
        product_name=new JLabel("PRODUCT NAME");
        product_price=new JLabel("PRODUCT PRICE");
        product_quantity=new JLabel("PRODUCT QUANTITY");
        medName_label = new JLabel("SEARCH NAME");

        product_id.setBounds(260,132,200,30);
        product_quantity.setBounds(260,182,200,30);
        product_name.setBounds(260,232,200,30);
        product_price.setBounds(260,280,200,30);
        product_type_label.setBounds(260,330,200,30);
        medName_label.setBounds(200,500,200,30);



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
        Search_button= new JButton("SEARCH");
        Search_button.setBounds(550,500,120,30);
        Search_button.setBackground(Color.BLACK);
        Search_button.setForeground(Color.WHITE);
        Search_button.addActionListener(new MyHandler());
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
        panel.add(product_type_text);
        panel.add(product_type_label);
        panel.add(Search_button);
        panel.add(medName_label);
        panel.add(productName);

        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Details for Purchase");
        setVisible(true);
        
        

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
             items_type = product_type_text.getText().toString();
                   
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
                if(product_type_text.getText().toString().equals("Medicine")){
                    setVisible(false);
                    new UpdatePurchaseMedDetailsGUI();
                }
                else if(product_type_text.getText().toString().equals("Devices")){
                    setVisible(false);
                    new UpdatePurchaseDeviceDetailsGUI();
                }
                else if(product_type_text.getText().toString().equals("Other_items")){
                    setVisible(false);
                    new UpdatePurchaseOtherDetailsGUI();
                }


            }

            if(e.getSource()==main_menu_button){
                setVisible(false);
                new MainMenuGUI();
            }
            if(e.getSource()==Search_button){
                try{
                    java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    Statement stmt = mycon.createStatement();
                    ResultSet result = stmt.executeQuery("Select I_name from items where I_name='"+productName.getText()+"'");
                    int l=0;
                    while(result.next()){
                        prod_Name = result.getString(1);
                        l++;
                    }
                }
                catch(Exception g) {
                    System.out.println(g.toString());
                }
                if(prod_Name==null){
                    JOptionPane.showMessageDialog(null,"Product Not Found");
                }
                try{
                    java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    Statement stmt = mycon.createStatement();
                    ResultSet result = stmt.executeQuery("select item_id,quantity,price,I_name,items_type from items where I_name='"+productName.getText()+"'");
                    int k=0 ;
                    while(result.next()){
                        array[k][0] = String.valueOf(result.getInt(1));
                        array[k][1] = String.valueOf(result.getInt(2));
                        array[k][2] = String.valueOf(result.getInt(3));
                        array[k][3] = result.getString(4);
                        array[k][4] = result.getString(5);
                        k++;

                    }
                    if(prod_Name!=null){
                        product_id_box.setText(array[0][0]);
                        product_quantity_box.setText(array[0][1]);
                        product_price_box.setText(array[0][2]);
                        product_name_box.setText(array[0][3]);
                        String n = array[0][4];
                        product_type_text.setText(n);
                        JOptionPane.showMessageDialog(null,"Product Found");

                    }

                }
                catch(Exception g) {
                    System.out.println(g.toString());
                }

            }

        }
    }
   
    
}
