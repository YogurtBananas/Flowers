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


    public Flower(String name, String ease, String instructions) {
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
