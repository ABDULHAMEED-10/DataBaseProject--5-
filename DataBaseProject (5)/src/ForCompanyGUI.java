import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForCompanyGUI extends JFrame {
    private JButton SellerCompany;
    private JButton ManufacturedCompany;


    private JButton Back;




    private JPanel panel;

    public JLabel store_name_label;

    public ForCompanyGUI(){


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
        SellerCompany=new JButton("Seller Company");
        ManufacturedCompany=new JButton("Manf Company");
        Back = new JButton("Back");

        Back.setBounds(102,460,133,45);
        SellerCompany.setBounds(574,460,132,45);
        ManufacturedCompany.setBounds(327,460,132,45);


        //////////////////////////////////////////////////////////
        Font f2=new Font("arial",Font.BOLD,34);
        store_name_label=new JLabel("MEDICAL STORE");
        store_name_label.setFont(f2);
        store_name_label.setBounds(263,35,800,50);
        store_name_label.setForeground(Color.black);
        ////////////////////////////////////////////


        /////////////////////////
        panel.add(SellerCompany);
        panel.add(ManufacturedCompany);

        panel.add(store_name_label);
        panel.add(Back);

        ///////////////////////////
        SellerCompany.setBackground(Color.BLACK);
        SellerCompany.setForeground(Color.WHITE);

        ManufacturedCompany.setForeground(Color.WHITE);
        ManufacturedCompany.setBackground(Color.BLACK);





        /////////////////////////////////////////////////

        SellerCompany.addActionListener(new MyHandler());
        ManufacturedCompany.addActionListener(new MyHandler());
        Back.addActionListener(new MyHandler());


        ///////////////////////////////////////////////////
        add(panel);
        setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Select Company");
        setVisible(true);
        /////////////////////////////////////////

    }
    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==SellerCompany){
                setVisible(false);
                new ViewCompany();


            }
            if(e.getSource()==ManufacturedCompany){
                setVisible(false);
                new ViewManfCompany();
            }

            if(e.getSource()==Back){
                setVisible(false);
                new MainMenuGUI();
            }


        }
    }
}


