package com.art.dao;

import java.util.List;

import org.hibernate.Session;

import com.art.schema.MissionSchema;

public interface IMissionDao {

    public boolean save(Session aSession, MissionSchema aMissionSchema);

    public boolean del(Session aSession, MissionSchema aMissionSchema);

    public boolean update(Session aSession, MissionSchema aMissionSchema);

    public List<?> query(Session aSession, String aSql);
    
    public MissionSchema getByMissionId(Session aSession, String aMissionId, String aActivityId);
}
