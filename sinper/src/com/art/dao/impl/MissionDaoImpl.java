package com.art.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.art.dao.IMissionDao;
import com.art.schema.MissionSchema;

public class MissionDaoImpl implements IMissionDao {

    @Override
    public boolean save(Session aSession, MissionSchema aMissionSchema) {
	aSession.save(aMissionSchema);
	return false;
    }

    @Override
    public boolean del(Session aSession, MissionSchema aMissionSchema) {
	aSession.delete(aMissionSchema);
	return false;
    }

    @Override
    public boolean update(Session aSession, MissionSchema aMissionSchema) {
	aSession.update(aMissionSchema);
	return true;
    }

    @Override
    public List<?> query(Session aSession, String aSql) {
	return null;
    }

    @Override
    public MissionSchema getByMissionId(Session aSession, String aMissionId, String aActivityId) {
	String tSql = " from MissionSchema where missionid='" + aMissionId + "' and activityid='" +aActivityId + "' ";
	Query query = aSession.createQuery(tSql);
	MissionSchema  aSchema = (MissionSchema) query.list().get(0);
	return aSchema;
    }

 

}
