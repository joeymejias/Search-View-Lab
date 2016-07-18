package ly.generalassemb.drewmahrt.shoppinglistwithsearch;

/**
 * Created by joey on 7/14/16.
 */
public class GroceryListItem {
    int id;
    String name;
    String description;
    String type;
    double price;

    public GroceryListItem() {

    }

    public GroceryListItem(int id, String name, String description, String type, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
