package Model;

public class Sector
{
    private double from;
    private double to;
    private int id;

    public Sector(int id, double from, double to)
    {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public int getId() {
        return id;
    }
}
