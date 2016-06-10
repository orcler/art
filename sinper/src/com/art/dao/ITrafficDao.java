package com.art.dao;

import java.util.List;

import com.art.schema.TrafficSchema;

public interface ITrafficDao {
    
    public boolean save(TrafficSchema aTrafficSchema);

    public boolean del(TrafficSchema aTrafficSchema);

    public boolean update(TrafficSchema aTrafficSchema);

    public List<?> query(String aSql);

}
