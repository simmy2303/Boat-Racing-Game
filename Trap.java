public class Trap extends Placement{

    public Trap(){
        super();
    }

    @Override
    public String toString(){
        return String.format("%s trap (-%d)\n", super.toString(), super.getMagnitude());
    }
}
