package Model;

public class Toy {
    private int idToy;
    private String nameToy;
    private int weightDrop;
    private double modifyWeightDrop;
    private double chanceDrop;
    private int quantity;

    public Toy(int idToy, String nameToy, int weightDrop, int quantity) {
        this.idToy = idToy;
        this.nameToy = nameToy;
        this.weightDrop = weightDrop;
        this.quantity = quantity;
        this.modifyWeightDrop = 1.0d;
    }

    public int getIdToy() {
        return idToy;
    }

    public String getNameToy() {
        return nameToy;
    }

    public int getWeightDrop() {
        return weightDrop;
    }

    public double getUserWeightDrop() {
        return Math.pow(weightDrop, modifyWeightDrop);
    }

    public int getQuantity() {
        return quantity;
    }

    public double getModifyWeightDrop() {
        return modifyWeightDrop;
    }

    public double getChanceDrop() {
        return chanceDrop;
    }

    public void setWeightDrop(int weightDrop) {
        this.weightDrop = weightDrop;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setModifyWeightDrop(double modifyWeightDrop) {
        this.modifyWeightDrop = modifyWeightDrop;
    }

    public void setChanceDrop(double chanceDrop) {
        this.chanceDrop = chanceDrop;
    }

    @Override
    public String toString() {
        return getNameToy();
    }
    public String getDetailedData(){
        StringBuilder detailedData = new StringBuilder();
        detailedData.append("id - ").append(getIdToy()).
                        append(" Название - ").append(getNameToy()).
                            append(" Вес выпадения - ").append(getWeightDrop()).
                                append(" Шанс выпадения - ").append(getChanceDrop()*100).
                                    append("% Количество - ").append(getQuantity());
        return detailedData.toString();
    }
}
