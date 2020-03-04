package Model;

public abstract class Part extends Inventory {
    private int partID;
    private String partName;
    private double partPrice;
    private int partInStock;
    private int min;
    private int max;

    public Part() {
        this.partID = 0;
        this.partName = "";
        this.partPrice = 0;
        this.partInStock = 0;
        this.min = 0;
        this.max = 0;
    }

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    public int getPartInStock() {
        return partInStock;
    }

    public void setPartInStock(int partInStock) {
        this.partInStock = partInStock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
