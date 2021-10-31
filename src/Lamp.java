
package edu.ucalgary.ensf409;


class Lamp extends OfficeFurniture{
    private char base;
    private char bulb;

    //Default constructor
    public Lamp(String ID, String Type, int Price, String ManuID, char base, char bulb) {
        super(ID, Type, Price, ManuID);
        this.base = base;
        this.bulb = bulb;
    }

    //Getter of base
    public int getBase() {
        return base;
    }

    //Getter of bulb
    public int getBulb() {
        return bulb;
    }

    //Setter of base
    public void setBase(char base) {
        this.base = base;
    }

    //Setter of bulb
    public void setBulb(char bulb) {
        this.bulb = bulb;
    }
}
