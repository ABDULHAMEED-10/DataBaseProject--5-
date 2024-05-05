
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SaleItemDetails extends JFrame {
    static double Totalprice;
    static double grandTotal;
    static int i=0;
    static int id;

    static String type;
    static boolean check;
    static String[][] array ;
    static String[][] array2 ;
    static String[][] forCart = new String[50][5];
    static int index=0;

    static int quantity;
    static double price;
    static String name;
    private JTextField product_name_box;
    private JTextField product_quantity_box;
    private JTextField product_price_box;

    private JComboBox product_type_combo;
  /////////////////////////////////////////
    private JLabel product_name;

    private JLabel product_quantity;
    private JLabel product_price;

    private JLabel product_type_label;
    private JPanel panel;
    private JLabel main_label;
    ////////////////////////////////////////
    private JButton back_button;
    private JButton more_button;
    private JButton next_button;
    private JButton main_menu_button;


    public SaleItemDetails(){
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
        main_label=new JLabel("SALE PRODUCT");
        Font f1=new Font("arial",Font.BOLD,30);
        main_label.setFont(f1);
        main_label.setBounds(325,40,800,50);
        main_label.setForeground(Color.WHITE);
///////////////////////////////////////////////////////////
// Text Boxes
        product_name_box=new JTextField();
        product_quantity_box=new JTextField();
        product_price_box=new JTextField();

        ////////////////////////
        String[] product_category_array={"","Medicine","Devices","Other_items"};
        product_type_combo=new JComboBox(product_category_array);
        //////////////////////
        product_type_combo.setBounds(430,180,200,27);
        product_name_box.setBounds(430,230,200,27);
        product_quantity_box.setBounds(430,280,200,27);
        product_price_box.setBounds(430,330,200,27);


        product_quantity_box.setBorder(null);
        product_price_box.setBorder(null);
        product_name_box.setBorder(null);


//////////////////////////////////////////////////////////////
// LABELS
        product_type_label = new JLabel("SELECT TYPE");
        product_name=new JLabel("PRODUCT NAME");
        product_price=new JLabel("PRODUCT PRICE");
        product_quantity=new JLabel("PRODUCT QUANTITY");

        product_type_label.setBounds(260,182,200,30);
        product_name.setBounds(260,232,200,30);
        product_quantity.setBounds(260,280,200,30);
        product_price.setBounds(260,330,200,30);



//////////////////////////////////////////////////////////////
// back button
        back_button=new JButton("BACK");
        back_button.setBounds(45,500,120,30);
//next button
        next_button=new JButton("NEXT");
        next_button.setBounds(725,500,120,30);
        next_button.setBackground(Color.BLACK);
        next_button.setForeground(Color.WHITE);
        //more button
        more_button=new JButton("MORE");
        more_button.setBounds(600,500,120,30);
        more_button.setBackground(Color.BLACK);
        more_button.setForeground(Color.WHITE);
///////////////////////////////////////////////////////
        back_button.addActionListener(new MyHandler());
        next_button.addActionListener(new MyHandler());
        more_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////
        main_menu_button=new JButton("MAIN MENU");
        main_menu_button.addActionListener(new MyHandler());
        main_menu_button.setBounds(45,465,120,30);
        main_menu_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////
        panel.add(product_name);
        panel.add(product_name_box);
        panel.add(main_menu_button);
        panel.add(product_type_combo);
        panel.add(product_type_label);
        panel.add(product_quantity);
        panel.add(product_quantity_box);
        panel.add(product_price);
        panel.add(product_price_box);
        panel.add(main_label);
        panel.add(more_button);
        panel.add(next_button);
        panel.add(back_button);

        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Add Item Details");
        setVisible(true);

    }
////////////////////////////////////////////////////////////////
    public static int getQuantity(){
        return quantity;
    }

    public static String get_name(){
        return name;
    }
    public static double getGrandTotal(){
        return grandTotal;
    }
    public static int getId(){
        return SaleItemDetails.extracting_id();
    }
    public static  String get_type(){
        return type;
    }

/////////////////////////////////////////////////////////////////////

    public void Insert_Item_details() {

        try{

            quantity=Integer.parseInt(product_quantity_box.getText());
            price=Integer.parseInt(product_price_box.getText());
            name=product_name_box.getText();
            type=product_type_combo.getSelectedItem().toString();
            i++;

        }
        catch(Exception g){
            System.out.println(g.toString());
        }
    }
    public static int extracting_id(){
        array2=new String[1][1];
        try{
            java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
            Statement stmt = mycon.createStatement();
            ResultSet result = stmt.executeQuery("select item_id from items where I_name='"+name+"'");
            int i=0;
            while(result.next()){
                array2[i][0] = String.valueOf(result.getInt(1));
                i++;
            }
        }
        catch(Exception g) {
            System.out.println(g.toString());
        }
        int i = Integer.parseInt(array2[0][0]);
        return i ;
    }



    public boolean check() {
        array = new String[17][4];
        boolean Presence_check = false;
        boolean quant_check = false;
        boolean type_check = false;
        boolean price_check = false;
        try {
            java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
            Statement stmt = mycon.createStatement();

            ResultSet result = stmt.executeQuery("select * from items where I_name='"+product_name_box.getText()+"'and Items_type='"+product_type_combo.getSelectedItem()+"'");

            int i = 0;
            while (result.next()) {

                array[i][0] = String.valueOf(result.getInt(2));
                array[i][1] = result.getString(3);
                array[i][2] = result.getString(6);
                array[i][3] = result.getString(4);

                i++;
            }

        int l = 0;
        if (!(product_name_box.getText().equals("") && product_quantity_box.getText().equals("") && product_quantity_box.getText().equals("0") && String.valueOf(product_price_box.getText()).equals("") && product_type_combo.getSelectedItem().equals("") && check)) {
            //presences check
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j][1] != null) {
                    if (array[j][1].equals((product_name_box.getText()))) {

                        Presence_check = true;
                        l = j;

                        break;
                    }
                } else if (array[j][1] == null) {
                    break;
                }
            }
            if (!Presence_check) {
                product_name_box.setText("");
                JOptionPane.showMessageDialog(null, "Product not found");

            }
            if(Presence_check) {
                //quantity check
                if (Integer.parseInt(array[l][0]) >= Integer.parseInt(product_quantity_box.getText())) {
                    quant_check = true;
                }

                //type check
                if (array[l][2].equals(product_type_combo.getSelectedItem())) {
                    type = (String) product_type_combo.getSelectedItem();

                    type_check = true;
                }
                //price check
                if (Integer.parseInt(array[l][3]) < (Integer.parseInt(product_price_box.getText()))) {
                    price_check = true;
                }


                if (!quant_check) {
                    product_quantity_box.setText("");

                    JOptionPane.showMessageDialog(null, "Not Enough Quantity");
                }
                if (!type_check) {
                    product_type_combo.setSelectedItem("");
                    JOptionPane.showMessageDialog(null, "Invalid type");
                }
                if (!price_check) {
                    product_price_box.setText("");
                    JOptionPane.showMessageDialog(null, "Invalid Price");
                }
            }
        }

        if (type_check && quant_check && Presence_check && price_check){
            check = true;
            forCart[index][0] = product_name_box.getText();
            forCart[index][1] = (String) product_type_combo.getSelectedItem();
            forCart[index][2] = product_quantity_box.getText();
            forCart[index][3] = product_price_box.getText();
            forCart[index][4] = String.valueOf((Integer.parseInt(product_price_box.getText())) * (Integer.parseInt(product_quantity_box.getText())));
            grandTotal+=Integer.parseInt(product_price_box.getText())*Integer.parseInt(product_quantity_box.getText());
        }


    }
        catch (Exception g){
            System.out.println(g.toString());
        }
        return check;
}


    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==back_button){
                setVisible(false);
                new SalePurchaseGUI();

            }
            if(e.getSource()==next_button){
                Insert_Item_details();

                if(check()) {

                    setVisible(false);
                    new SaleAllDetailsGUI();
                }
            }

            if(e.getSource()==main_menu_button){
                setVisible(false);
                new MainMenuGUI();
            }
            if(e.getSource()==more_button){
                Insert_Item_details();
                if(check()) {
                    product_name_box.setText("");
                    product_quantity_box.setText("");
                    product_price_box.setText("");
                    product_type_combo.setSelectedItem("");
                    index++;
                }
            }
        }
    }
}


