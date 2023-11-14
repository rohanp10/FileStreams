import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;

public class RandProductSearch extends JFrame {

    JPanel mainPnl;
    JPanel topPnl;
    JPanel middlePnl;

    JPanel bottomPnl;

    JTextArea displayTA;
    JScrollPane scroller;

    JLabel titleLbl;

    JTextField searchInput;

    JLabel fileLbl;
    ImageIcon icon;

    JButton search;
    JButton quit;


    public Map<String, Integer> frequencies = new TreeMap<>();

    Random rnd = new Random();


    public RandProductSearch() {
        mainPnl = new JPanel();

        mainPnl.setLayout(new BorderLayout());

        createTopPnl();
        mainPnl.add(topPnl, BorderLayout.NORTH);

        createMiddlePnl();
        mainPnl.add(middlePnl, BorderLayout.CENTER);

        createBottomPnl();
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);

        add(mainPnl);

    }

    private void createTopPnl() {
        topPnl = new JPanel();

        topPnl.setLayout(new GridLayout(2, 1));

        topPnl.setBorder(new EmptyBorder(10, 10, 10, 10));

        titleLbl = new JLabel("File Streams", JLabel.CENTER);

        searchInput = new JTextField("Partial Product Name: ");

        searchInput.setPreferredSize(new Dimension(100, 100));

        searchInput.setFont(new Font("Roboto", Font.PLAIN, 18));

        titleLbl.setFont(new Font("Roboto", Font.PLAIN, 36));

        titleLbl.setVerticalTextPosition(JLabel.TOP);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        topPnl.setBackground(new Color(198, 226, 255));

        topPnl.add(titleLbl);
        topPnl.add(searchInput);
    }

    private void createMiddlePnl() {
        middlePnl = new JPanel();
        displayTA = new JTextArea(10, 40);

        displayTA.setFont(new Font("Verdana", Font.PLAIN, 20));

        displayTA.setEditable(false);
        scroller = new JScrollPane(displayTA);
        middlePnl.add(scroller);

        middlePnl.setBackground(new Color(198, 226, 255));

    }

    private void createBottomPnl() {

        bottomPnl = new JPanel();
        bottomPnl.setLayout(new GridLayout(1, 2));

        search = new JButton("Search");
        search.addActionListener((ActionEvent ae) ->
        {

            String userInput = searchInput.getText().substring(22);

            displayTA.append("ID           Name        Description             Cost\n");

            try {


                FileInputStream fileStream = new FileInputStream("ProductData.bin");
                ObjectInputStream objStream = new ObjectInputStream(fileStream);

                String userProduct = (objStream.readObject()).toString();

                objStream.close();

            }
            catch (Exception e) {
                e.getStackTrace();
            }

            ArrayList<Product> products = new ArrayList<>();

            Product first = new Product("000001", "Computer", "Users can store data", 100.0);
            Product second = new Product("000002", "Pencil", "Can be used to write", 5.0);
            Product third = new Product("000003", "Book", "Contains knowledge and words", 20.0);

            products.add(first);
            products.add(second);
            products.add(third);


            for (Product product: products) {
                if ((product.getName()).contains(userInput)) {
                    displayTA.append(product.toCSVDataRecord() + "\n");
                }
            }

            searchInput.setText("Partial Product Name: " + userInput);


        });

        quit = new JButton("Quit");
        quit.addActionListener((ActionEvent ae) ->
        {
            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Message", JOptionPane.YES_NO_OPTION);

            if (answer == 0) {
                System.exit(0);
            }
        });

        search.setPreferredSize(new Dimension(40, 40));
        quit.setPreferredSize(new Dimension(40, 40));

        search.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        quit.setFont(new Font("Sans Serif", Font.PLAIN, 15));

        bottomPnl.add(search);
        bottomPnl.add(quit);

        bottomPnl.setBackground(new Color(198, 226, 255));

    }


}