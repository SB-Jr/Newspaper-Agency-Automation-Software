package nitz.sbjr.project.naas.pojo;

/**
 * Created by sbjr on 11/16/16.
 */

public class Subscription {

    private String title;
    private int price;

    public Subscription(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Subscription() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
