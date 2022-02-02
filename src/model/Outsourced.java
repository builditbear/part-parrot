package model;

/** A subclass of Part describing Parts that are sourced from outside the company. Features a Company Name. */
public class Outsourced extends Part {
    private String companyName;

    /** Constructs a new Outsourced Part. */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** Retrieve this Part's Company Name.
     *
     * @return This Part's Company Name.
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /** Sets this Part's Company Name.
     *
     * @param companyName The Company Name to be set for this Part.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
