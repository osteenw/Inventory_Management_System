package Model;

public class Outsourced extends Part {
    private String companyName;

    public Outsourced(int partID, String partName, double partPrice, int partInStock, int min, int max, String companyName) {
        setPartID(partID);
        setPartName(partName);
        setPartPrice(partPrice);
        setPartInStock(partInStock);
        setMin(min);
        setMax(max);
        setCompanyName(companyName);
    }

    public Outsourced() {
        setPartID(0);
        setPartName("");
        setPartPrice(0);
        setPartInStock(0);
        setMin(0);
        setMax(0);
        setCompanyName("");
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

