package com.tgcity.profession.network.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.tgcity.profession.network.greendao.model.HttpRequestLog;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * @author TGCity
 * DAO for table "HTTP_REQUEST_LOG".
 */
public class HttpRequestLogDao extends AbstractDao<HttpRequestLog, Long> {

    public static final String TABLENAME = "HTTP_REQUEST_LOG";

    /**
     * Properties of entity HttpRequestLog.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ApiName = new Property(1, String.class, "apiName", false, "API_NAME");
        public final static Property RequestData = new Property(2, String.class, "requestData", false, "REQUEST_DATA");
        public final static Property CreateTime = new Property(3, long.class, "createTime", false, "CREATE_TIME");
        public final static Property Type = new Property(4, int.class, "type", false, "TYPE");
    }


    public HttpRequestLogDao(DaoConfig config) {
        super(config);
    }
    
    public HttpRequestLogDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HTTP_REQUEST_LOG\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"API_NAME\" TEXT," + // 1: apiName
                "\"REQUEST_DATA\" TEXT," + // 2: requestData
                "\"CREATE_TIME\" INTEGER NOT NULL ," + // 3: createTime
                "\"TYPE\" INTEGER NOT NULL );"); // 4: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HTTP_REQUEST_LOG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HttpRequestLog entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String apiName = entity.getApiName();
        if (apiName != null) {
            stmt.bindString(2, apiName);
        }
 
        String requestData = entity.getRequestData();
        if (requestData != null) {
            stmt.bindString(3, requestData);
        }
        stmt.bindLong(4, entity.getCreateTime());
        stmt.bindLong(5, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HttpRequestLog entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String apiName = entity.getApiName();
        if (apiName != null) {
            stmt.bindString(2, apiName);
        }
 
        String requestData = entity.getRequestData();
        if (requestData != null) {
            stmt.bindString(3, requestData);
        }
        stmt.bindLong(4, entity.getCreateTime());
        stmt.bindLong(5, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HttpRequestLog readEntity(Cursor cursor, int offset) {
        HttpRequestLog entity = new HttpRequestLog( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // apiName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // requestData
            cursor.getLong(offset + 3), // createTime
            cursor.getInt(offset + 4) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HttpRequestLog entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setApiName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRequestData(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCreateTime(cursor.getLong(offset + 3));
        entity.setType(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HttpRequestLog entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HttpRequestLog entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HttpRequestLog entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}