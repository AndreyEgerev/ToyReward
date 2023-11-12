package Model;

import java.util.ArrayList;

public class AllChanceToy {
    private ArrayList<Sector> sectorList;

    public AllChanceToy(ArrayList<Toy> toysList) {
        this.sectorList = new ArrayList<>();
        double rangeTo = 0.0,rangeFrom = 0.0;
        for (int i = 0; i < toysList.size(); i++){
            Toy toy = toysList.get(i);
            rangeTo += toy.getChanceDrop();
            this.sectorList.add(new Sector(i,rangeFrom,rangeTo));
            rangeFrom = rangeTo;
        };
    }
    public int selectSector(double value)throws IllegalArgumentException{
        for (Sector sector:
             sectorList) {
            if (valueInSector(value,sector)) {
                return sector.getId();
            }
        }
        throw new IllegalArgumentException("Некоректное значение. Шанс выпадения не определен");
    }

    private boolean valueInSector (double value, Sector sector){
        return value >= sector.getFrom() && value < sector.getTo();
    }
}
