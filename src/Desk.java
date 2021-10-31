


class Desk extends OfficeFurniture{
    private char legs;
    private char top;
    private char drawer;

    //Default constructor, builds a new desk
    public Desk(String ID, String Type, int Price, String ManuId, char legs, char top, char drawer) {
        super(ID, Type, Price, ManuId);
        this.legs = legs;
        this.top = top;
        this.drawer = drawer;
    }

    //Getter of legs
    public char getLegs() {
        return legs;
    }

    //Getter of top
    public char getTop() {
        return top;
    }

    //Getter of drawer
    public char getDrawer() {
        return drawer;
    }

    //Setter of legs
    public void setLegs(char legs) {
        this.legs = legs;
    }

    //Setter of top
    public void setTop(char top) {
        this.top = top;
    }

    //Setter of drawer
    public void setDrawer(char drawer) {
        this.drawer = drawer;
    }

}
