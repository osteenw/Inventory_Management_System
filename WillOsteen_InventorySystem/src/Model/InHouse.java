package Model;

public class InHouse extends Part {
    private int machineID;

    public InHouse(int partID, String partName, double partPrice, int partInStock, int min, int max, int machineID) {
        setPartID(partID);
        setPartName(partName);
        setPartPrice(partPrice);
        setPartInStock(partInStock);
        setMin(min);
        setMax(max);
        setMachineID(machineID);
    }

    public InHouse(){
        setPartID(0);
        setPartName("");
        setPartPrice(0);
        setPartInStock(0);
        setMin(0);
        setMax(0);
        }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
