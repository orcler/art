package com.art.dao;

import java.util.List;

import org.hibernate.Session;

import com.art.schema.TrafficSchema;

public interface ITrafficDao {
    
    public boolean save(Session aSession, TrafficSchema aTrafficSchema);

    public boolean del(Session aSession, TrafficSchema aTrafficSchema);

    public boolean update(Session aSession, TrafficSchema aTrafficSchema);
    
    public TrafficSchema query(Session aSession, TrafficSchema aTrafficSchema);

    public List<?> query(Session aSession, String aSql);

}
