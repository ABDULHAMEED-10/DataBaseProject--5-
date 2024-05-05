import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalePurchaseGUI extends JFrame{
    private JButton Sale;
    private JButton Purchase;


    private JButton view_items;
    private JButton back;
    private JButton View_button;
    private JButton Update_button;
    private JButton Delete_button;



    private JPanel panel;

    public JLabel store_name_label;



    public SalePurchaseGUI(){


        ImageIcon img = new ImageIcon("allItem.jpg");
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
        Sale=new JButton("Sale");
        Purchase=new JButton("Purchase");
        back = new JButton("Back");
        view_items = new JButton("View Items");
        View_button=new JButton("STOCK");
        Update_button=new JButton("Update");
        Delete_button=new JButton("Delete");

        back.setBounds(20,30,130,45);
        view_items.setBounds(102,460,133,45);
        Sale.setBounds(574,460,132,45);
        Purchase.setBounds(327,460,132,45);
        Update_button.setBounds(102,515,133,45);
        Delete_button.setBounds(327,515,133,45);
        View_button.setBounds(574,515,133,45);
        //////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////
        Font f2=new Font("arial",Font.BOLD,34);
        store_name_label=new JLabel("MEDICAL STORE");
        store_name_label.setFont(f2);
        store_name_label.setBounds(263,35,800,50);
        store_name_label.setForeground(Color.black);
        ////////////////////////////////////////////


        /////////////////////////
        panel.add(Sale);
        panel.add(Purchase);
        panel.add(View_button);
        panel.add(Delete_button);
        panel.add(Update_button);

        panel.add(store_name_label);
        panel.add(view_items);
        panel.add(back);
        ///////////////////////////
        Sale.setBackground(Color.BLACK);
        Sale.setForeground(Color.WHITE);

        View_button.setBackground(Color.BLACK);
        View_button.setForeground(Color.WHITE);

        Delete_button.setBackground(Color.BLACK);
        Delete_button.setForeground(Color.WHITE);

        Update_button.setBackground(Color.BLACK);
        Update_button.setForeground(Color.WHITE);

        Purchase.setForeground(Color.WHITE);
        Purchase.setBackground(Color.BLACK);

        view_items.setForeground(Color.WHITE);
        view_items.setBackground(Color.BLACK);



        /////////////////////////////////////////////////

        Sale.addActionListener(new MyHandler());
        Purchase.addActionListener(new MyHandler());
        view_items.addActionListener(new MyHandler());
        back.addActionListener(new MyHandler());
        View_button.addActionListener(new MyHandler());
        Delete_button.addActionListener(new MyHandler());
        Update_button.addActionListener(new MyHandler());

        ///////////////////////////////////////////////////
        add(panel);
        setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Sale Purchase");
        setVisible(true);
        /////////////////////////////////////////

    }
    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==Sale){
                setVisible(false);
                new SaleItemDetails();


            }
            if(e.getSource()==Purchase){
                setVisible(false);
                new PurchaseItemDetails();
            }

            if(e.getSource()==view_items){
                setVisible(false);
                new ViewItems();
            }
            if(e.getSource()==back){
                setVisible(false);
                new MainMenuGUI();
            }
            if(e.getSource()== View_button){
                setVisible(false);
                new Stock();


            }
            if(e.getSource()==Delete_button){
                setVisible(false);
                new DeleteGUI();

            }
            if(e.getSource()==Update_button){
                setVisible(false);
                new UpdatePurchaseItemDetails();

            }

        }
    }
}

