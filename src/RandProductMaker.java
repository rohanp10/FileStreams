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

public class RandProductMaker extends JFrame implements Serializable{

    JPanel mainPnl;
    JPanel descriptionPnl;

    JPanel namePnl;

    JPanel idPnl;

    JPanel optionPnl;

    JPanel costPnl;

    JPanel userFields;

    JPanel orderPnl;
    JPanel buttonPnl;

    JLabel recordCount;

    JTextField nameField;
    JTextField idField;
    JTextField descriptionField;

    JTextField costField;

    JTextField cost;

    ButtonGroup group;

    ArrayList<Product> products;

    JButton quit;
    JButton userAdd;

    int userRecordCount = 0;

    public RandProductMaker() {

        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(4, 1));

        createInfoPanel();
        mainPnl.add(optionPnl);

        createDescriptionPanel();
        mainPnl.add(descriptionPnl);

        createButtonPnl();
        mainPnl.add(buttonPnl);

        createTextPnl();
        mainPnl.add(orderPnl);

        add(mainPnl);

    }

    private void createInfoPanel() {

        optionPnl = new JPanel();
        optionPnl.setLayout(new GridLayout(1, 3));

        namePnl = new JPanel();
        namePnl.setLayout(new GridBagLayout());

        namePnl.setBorder(new TitledBorder(new EtchedBorder(), "Name"));

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(400, 50));

        namePnl.add(nameField);

        idPnl = new JPanel();

        idPnl.setLayout(new GridBagLayout());
        idPnl.setBorder(new TitledBorder(new EtchedBorder(), "ID"));

        idField = new JTextField();
        idField.setPreferredSize(new Dimension(400, 50));

        idPnl.add(idField);

        costPnl = new JPanel();
        costPnl.setBorder(new TitledBorder(new EtchedBorder(), "Cost"));
        costPnl.setLayout(new GridBagLayout());

        costField = new JTextField();
        costField.setPreferredSize(new Dimension(400, 50));

        costPnl.add(costField);

        optionPnl.add(idPnl);
        optionPnl.add(namePnl);
        optionPnl.add(costPnl);

        optionPnl.setBorder(new EmptyBorder(10,10, 10, 10));

    }

    private void createDescriptionPanel() {

        descriptionPnl = new JPanel();
        descriptionPnl.setLayout(new GridBagLayout());
        descriptionPnl.setBorder(new TitledBorder(new EtchedBorder(), "Description"));

        descriptionField = new JTextField();
        descriptionField.setPreferredSize(new Dimension(800, 50));

        descriptionPnl.add(descriptionField);



    }

    private void createTextPnl() {

        orderPnl = new JPanel();

        recordCount = new JLabel("Records Entered: 0", JLabel.CENTER);

        recordCount.setFont(new Font("Roboto", Font.PLAIN, 20));

        recordCount.setVerticalTextPosition(JLabel.TOP);
        recordCount.setHorizontalTextPosition(JLabel.CENTER);

        orderPnl.add(recordCount);

    }


    private void createButtonPnl() {

        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridBagLayout());

        userAdd = new JButton("Add");
        userAdd.addActionListener(
                (ActionEvent ae) ->
                {

                    products = new ArrayList<>();

                    String id = idField.getText();
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    double cost = Double.parseDouble(costField.getText());

                    Product userProduct = new Product(id, name, description, cost);

                    userProduct.formatID();
                    userProduct.formatName();
                    userProduct.formatDescription();

                    products.add(userProduct);

                    try {

                        FileOutputStream file = new FileOutputStream("ProductData.bin");
                        ObjectOutputStream output = new ObjectOutputStream(file);

                        output.writeObject(products);

                    }
                    catch (Exception e) {
                        e.getStackTrace();
                    }


                    idField.setText("");
                    nameField.setText("");
                    descriptionField.setText("");
                    costField.setText("");

                    userRecordCount += 1;

                    recordCount.setText("Records Entered: " + userRecordCount);

                }
        );


        quit = new JButton("Quit");
        quit.addActionListener(
                (ActionEvent ae) ->
                {

                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Message", JOptionPane.YES_NO_OPTION);

                    if (answer == 0) {
                        System.exit(0);
                    }
                }
        );

        userAdd.setPreferredSize(new Dimension(200, 40));
        quit.setPreferredSize(new Dimension(200, 40));

        buttonPnl.add(userAdd);
        buttonPnl.add(quit);

    }

}