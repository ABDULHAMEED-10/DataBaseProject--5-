import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JFrame{
    private JButton items;
    private JButton customer_Data;
    private JButton medicine_data;
    private JButton company_data;
    private JButton sale_record_data;
    private JButton purchased_record_data;
    private JPanel panel;

    public JLabel store_name_label;
//    public JLabel main_menu_label;


public MainMenuGUI(){


    ImageIcon img = new ImageIcon("main_menu.jpg");
    panel=new JPanel()
    {

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(img.getImage(), 0, 0, getWidth(),getHeight(),null);

        }
    };

    panel.setLayout(null);
    items=new JButton("Items");
    customer_Data=new JButton("Customer Data");
    medicine_data=new JButton("Medicine Data");
    company_data=new JButton("Company Data");
    sale_record_data=new JButton("Sales Data");
    purchased_record_data=new JButton("Purchased Data");

    items.setBounds(104,463,132,45);
    customer_Data.setBounds(329,463,133,45);
    medicine_data.setBounds(576,463,132,45);
    company_data.setBounds(104,515,132,45);
    sale_record_data.setBounds(329,515,133,45);
    purchased_record_data.setBounds(576,515,132,45);


    //////////////////////////////////////////////////////////
    Font f2=new Font("arial",Font.BOLD,30);
    store_name_label=new JLabel("MEDICAL STORE");
    store_name_label.setFont(f2);
    store_name_label.setBounds(263,35,800,50);
    store_name_label.setForeground(Color.black);
    ////////////////////////////////////////////
    Font f1=new Font("arial",Font.BOLD,35);
//    main_menu_label=new JLabel("MAIN MENU");
//    main_menu_label.setFont(f1);
//    main_menu_label.setBounds(230,140,600,30);
//    main_menu_label.setForeground(Color.WHITE);
    /////////////////////////
    panel.add(items);
    panel.add(customer_Data);
    panel.add(sale_record_data);
    panel.add(company_data);
    panel.add(medicine_data);
    panel.add(purchased_record_data);
//    panel.add(main_menu_label);
    panel.add(store_name_label);
     ///////////////////////////
    items.setBackground(Color.BLACK);
    items.setForeground(Color.WHITE);

    customer_Data.setForeground(Color.WHITE);
    customer_Data.setBackground(Color.BLACK);

    sale_record_data.setForeground(Color.WHITE);
    sale_record_data.setBackground(Color.BLACK);

    company_data.setForeground(Color.WHITE);
    company_data.setBackground(Color.BLACK);

    medicine_data.setForeground(Color.WHITE);
    medicine_data.setBackground(Color.BLACK);

    purchased_record_data.setForeground(Color.WHITE);
    purchased_record_data.setBackground(Color.BLACK);

    ////////////////////////////////////////////
//    main_menu_label.setForeground(Color.WHITE);
    /////////////////////////////////////////////////

    items.addActionListener(new MyHandler());
    customer_Data.addActionListener(new MyHandler());
    sale_record_data.addActionListener(new MyHandler());
    company_data.addActionListener(new MyHandler());
    medicine_data.addActionListener(new MyHandler());
    purchased_record_data.addActionListener(new MyHandler());

    ///////////////////////////////////////////////////
    add(panel);
    setSize(800,600);
    setResizable(false);
    setLocationRelativeTo(null);
    setTitle("Main menu");
    setVisible(true);
    /////////////////////////////////////////

}
    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==items){
                setVisible(false);
                new SalePurchaseGUI();
            }
            if(e.getSource()==customer_Data){
                setVisible(false);
                new CustomerData();
            }
            if(e.getSource()==medicine_data){
                setVisible(false);
                new ViewMedicines();
            }
            if(e.getSource()==company_data){
                setVisible(false);
                new ForCompanyGUI();
            }
            if(e.getSource()==sale_record_data){
                setVisible(false);
                new SaleRecord();
            }
            if(e.getSource()==purchased_record_data){
                setVisible(false);
                new PurchasedData();
            }
        }
    }
}

