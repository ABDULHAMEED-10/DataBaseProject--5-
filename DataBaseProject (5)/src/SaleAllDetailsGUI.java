
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SaleAllDetailsGUI extends JFrame {

    static int[][] array = new int[17][5];
    static String Cust_name;
    static String Cust_age;
    static long Cust_Ph_num;
    static long Cust_CNIC;
    static long CNIC;

    private JTextField customer_name_text;
    private JTextField customer_CNIC_text;
    private JTextField customer_age_text;
    private JTextField customer_phNum_text;


    /////////////////////////////////////////


    private JLabel customer_name_label;
    private JLabel customer_CNIC_label;
    private JLabel customer_age_label;
    private JLabel customer_phNum_label;

    private JPanel panel;
    private JLabel main_label;
    ////////////////////////////////////////
    private JButton back_button;
    private JButton next_button;
    private JButton main_menu_button;
    public SaleAllDetailsGUI(){

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


        customer_name_text=new JTextField();
        customer_CNIC_text=new JTextField();
        customer_age_text=new JTextField();
        customer_phNum_text=new JTextField();




        customer_name_text.setBounds(430,280,200,27);
        customer_CNIC_text.setBounds(430,330,200,27);
        customer_phNum_text.setBounds(430,380,200,27);
        customer_age_text.setBounds(430,430,200,27);


        customer_CNIC_text.setBorder(null);
        customer_name_text.setBorder(null);
        customer_age_text.setBorder(null);
        customer_phNum_text.setBorder(null);


//////////////////////////////////////////////////////////////
// LABELS

        customer_name_label = new JLabel("CUSTOMER NAME");
        customer_CNIC_label = new JLabel("CUSTOMER CNIC_NUM");
        customer_phNum_label = new JLabel("CUSTOMER PHONE_NUM");
        customer_age_label = new JLabel("CUSTOMER AGE");


        customer_name_label.setBounds(260,282,200,30);
        customer_CNIC_label.setBounds(260,332,200,30);
        customer_phNum_label.setBounds(260,382,200,30);
        customer_age_label.setBounds(260,432,200,30);




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



        panel.add(customer_name_label);
        panel.add(customer_name_text);

        panel.add(customer_CNIC_label);
        panel.add(customer_CNIC_text);

        panel.add(customer_age_label);
        panel.add(customer_age_text);

        panel.add(customer_phNum_text);
        panel.add(customer_phNum_label);


        panel.add(main_label);


        panel.add(main_menu_button);
        panel.add(next_button);
        panel.add(back_button);

        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Sale Devices");
        setVisible(true);


    }
    public void Insert_Item_details() {
        try{
            CNIC =Long.parseLong(customer_CNIC_text.getText());
            Cust_Ph_num=Long.parseLong(customer_phNum_text.getText());
            Cust_name=customer_name_text.getText();
            Cust_age=customer_age_text.getText();
            Cust_CNIC =Long.parseLong(customer_CNIC_text.getText());

        }
        catch(Exception g){
            System.out.println(g.toString());
        }

    }

    public static long getCNIC(){
        return CNIC;
    }
    public static String  getCust_age(){
        return Cust_age;
    }
    public static String  getCust_name(){
        return Cust_name;
    }
    public static long getCust_Ph_num(){
        return Cust_Ph_num ;
    }

    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==back_button){
                setVisible(false);
                new SaleItemDetails();
            }
            if(e.getSource()==next_button){
                Insert_Item_details();

                if ((Integer.parseInt(customer_age_text.getText()))>100 || (Integer.parseInt(customer_age_text.getText())<5 ) || customer_age_text.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Invalid Age");
                    customer_age_text.setText("");
                }

                    setVisible(false);
                    new BillGeneration();




            }

            if(e.getSource()==main_menu_button){
                setVisible(false);
                new MainMenuGUI();
            }

        }
    }

}
