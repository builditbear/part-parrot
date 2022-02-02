package model;

/** A subclass of Part that describes Parts created within the company's organization. Features a Machine ID.*/
public class InHouse extends Part {

    private int machineId;

    /** Constructs a new InHouse Part. */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /** Retrieves this InHouse Part's Machine ID.*/
    public int getMachineId(){
        return this.machineId;
    }

    /** Sets this InHouse Part's Machine ID.
     * @param machineId The desired Machine ID to be set.
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
}