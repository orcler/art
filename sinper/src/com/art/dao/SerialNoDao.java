package com.art.dao;

import com.art.schema.SerialNoSchema;

public interface SerialNoDao {
    
    public boolean save(SerialNoSchema aSerialNoSchema);
    
    public SerialNoSchema getByType(String aType);

}
