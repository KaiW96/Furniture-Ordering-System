

class Filing extends OfficeFurniture{
    private char rails;
    private char drawers;
    private char cabinet;

    //Default constructor
    public Filing(String ID, String Type, int Price, String ManuID, char rails, char drawers, char cabinet) {
        super(ID, Type, Price, ManuID);
        this.rails = rails;
        this.drawers = drawers;
        this.cabinet = cabinet;
    }

    //Getter of rails
    public char getRails() {
        return rails;
    }

    //Getter of drawers
    public char getDrawers() {
        return drawers;
    }

    //Getter of cabinets
    public char getCabinet() {
        return cabinet;
    }

    //Setter of rails
    public void setRails(char rails) {
        this.rails = rails;
    }

    //Setter of drawers
    public void setDrawers(char drawers) {
        this.drawers = drawers;
    }

    //Setter of cabinet
    public void setCabinet(char cabinet) {
        this.cabinet = cabinet;
    }

}
