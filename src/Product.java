import java.io.Serializable;
import java.util.Calendar;

public class Product implements Serializable {

    private String ID;
    private String name;
    private String description;
    private double cost;

    public Product(String ID, String name, String description, double cost) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String toCSVDataRecord()
    {
        return getID() + ", " + getName() + ", " + getDescription() + ", " + getCost();
    }

    public void formatName() {

        String result = name;

        int length = 35;

        if (!(result.length() >= length)) {
            StringBuilder sb = new StringBuilder();
            while (sb.length() < length - result.length()) {
                sb.append(' ');
            }
            sb.insert(0, result);

            name = sb.toString();
        }
    }

    public void formatDescription() {

        String result = description;

        int length = 75;

        if (!(result.length() >= length)) {
            StringBuilder sb = new StringBuilder();
            while (sb.length() < length - result.length()) {
                sb.append(' ');
            }
            sb.insert(0, result);

            description = sb.toString();
        }
    }

    public void formatID() {

        String result = ID;

        int length = 6;

        if (!(result.length() >= length)) {
            StringBuilder sb = new StringBuilder();
            while (sb.length() < length - result.length()) {
                sb.append(' ');
            }
            sb.insert(0, result);

            ID = sb.toString();
        }

    }


}