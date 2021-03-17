package entities;

public class Registration {

    private int memberID;
    private String teamID;
    private int price;
    private String name;

    public Registration(int memberID, String teamID, int price, String name) {
        this.memberID = memberID;
        this.teamID = teamID;
        this.price = price;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "memberID=" + memberID +
                ", teamID='" + teamID + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
