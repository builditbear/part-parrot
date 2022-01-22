package model;

public class Outsourced extends Part {
    private String companyName;

    public Outsourced(int idType, String name, double price, int stock, int min, int max, String companyName) {
        super(idType, name, price, stock, min, max);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
