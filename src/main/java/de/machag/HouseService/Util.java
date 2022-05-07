package de.machag.HouseService;

import de.machag.HouseService.House.House;
import de.machag.HouseService.Tennant.Tennant;

import java.util.List;

public class Util {

    public String fancyPrintAll(List<?> targetList) {
        if(targetList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < targetList.size(); i++) {
                Object targetListElement = targetList.get(i);
                if(targetListElement instanceof House) {
                    House targetHouse = (House) targetListElement;
                    sb.append(targetHouse.toString());
                    if (i < targetList.size() - 1) {
                        sb.append(",");
                    }
                    return sb.toString();
                }
                if(targetListElement instanceof Tennant) {
                    Tennant targetTennant = (Tennant) targetListElement;
                    sb.append(targetTennant.toString());
                    if (i < targetList.size() - 1) {
                        sb.append(",");
                    }
                    return sb.toString();
                }
            }
        }
        return null;
    }

}
