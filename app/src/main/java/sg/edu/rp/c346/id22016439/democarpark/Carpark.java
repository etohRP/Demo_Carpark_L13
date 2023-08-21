package sg.edu.rp.c346.id22016439.democarpark;

public class Carpark {


    private int lotsavailable;
    private String carparkno;

    public Carpark(int lotsavailable, String carparkno){
        this.lotsavailable = lotsavailable;
        this.carparkno = carparkno;
    }


    public int getLotsavailable() {
        return lotsavailable;
    }

    public void setLotsavailable(int lotsavailable) {
        this.lotsavailable = lotsavailable;
    }

    public String getCarparkno() {
        return carparkno;
    }

    public void setCarparkno(String carparkno) {
        this.carparkno = carparkno;
    }

    @Override
    public String toString(){
        return "Carpark Number: " + carparkno + "\nLots Available: " + lotsavailable;
    }
}

