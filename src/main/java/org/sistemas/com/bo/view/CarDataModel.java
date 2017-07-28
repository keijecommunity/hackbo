package org.sistemas.com.bo.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;


import org.primefaces.model.SelectableDataModel;
import org.sistemas.com.bo.domain.Coordenadai;

public class CarDataModel extends ListDataModel<Coordenadai> implements SelectableDataModel<Coordenadai>, Serializable {  

    public CarDataModel() {
    }

    public CarDataModel(List<Coordenadai> data) {
        super(data);
    }
    
    public Coordenadai getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
        List<Coordenadai> cars = (List<Coordenadai>) getWrappedData();
        
        for(Coordenadai car : cars) {
            if(car.getTitulo().equals(rowKey))
                return car;
        }
        
        return null;
    }

    public Object getRowKey(Coordenadai car) {
        return car.getTitulo();
    }
}
