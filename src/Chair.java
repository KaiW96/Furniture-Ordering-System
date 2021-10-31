
package edu.ucalgary.ensf409;

class Chair extends OfficeFurniture{
    private char legs;
    private char arms;
    private char seat;
    private char cushion;

    //Default constructor used to build chair
    public Chair(String ID, String type, int price, String manuID, char legs, char arms, char seat, char cushion) {
        super(ID, type, price, manuID);
        this.legs = legs;
        this.arms = arms;
        this.seat = seat;
        this.cushion = cushion;
    }

    //Getter of legs
    public char getLegs() {
        return legs;
    }

    //Getter of arms
    public char getArms() {
        return arms;
    }

    //Getter of seat
    public char getSeat() {
        return seat;
    }

    //Getter of cushion
    public char getCushion() {
        return cushion;
    }

    //Setter of legs
    public void setLegs(char legs) {
        this.legs = legs;
    }

    //Setter of arms
    public void setArms(char arms) {
        this.arms = arms;
    }

    //Setter of seat
    public void setSeat(char seat) {
        this.seat = seat;
    }

    //Setter of cushion
    public void setCushion(char cushion) {
        this.cushion = cushion;
    }

}
