package ph.edu.up.flowers;

/**
 * Created by fulltime on 01/06/2017.
 */

public class Flower {
    private int id;
    private String name;
    private String ease;
    private String instructions;

    public Flower () {}


    public Flower(int id, String name, String ease, String instructions) {
        this.id = id;
        this.name = name;
        this.ease = ease;
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    public String getEase() {

        return ease;
    }

    public String getInstructions() {

        return instructions;
    }

    public void setId(int id) {

        this.id = id;

    }

    public void setName(String name) {

        this.name = name;
    }

    public void setEase(String ease) {

        this.ease = ease;
    }

    public void setInstructions(String instructions) {

        this.instructions = instructions;
    }
}
