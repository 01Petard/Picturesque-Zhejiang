package com.example.dell.myapplication.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.dell.myapplication.module.Msg;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MSG".
*/
public class MsgDao extends AbstractDao<Msg, Long> {

    public static final String TABLENAME = "MSG";

    /**
     * Properties of entity Msg.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property Content = new Property(1, String.class, "content", false, "CONTENT");
        public final static Property Time = new Property(2, String.class, "time", false, "TIME");
        public final static Property NickName = new Property(3, String.class, "nickName", false, "NICK_NAME");
        public final static Property Id = new Property(4, Long.class, "id", false, "ID");
        public final static Property Type = new Property(5, int.class, "type", false, "TYPE");
    }


    public MsgDao(DaoConfig config) {
        super(config);
    }
    
    public MsgDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MSG\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"CONTENT\" TEXT NOT NULL ," + // 1: content
                "\"TIME\" TEXT NOT NULL ," + // 2: time
                "\"NICK_NAME\" TEXT NOT NULL ," + // 3: nickName
                "\"ID\" INTEGER NOT NULL ," + // 4: id
                "\"TYPE\" INTEGER NOT NULL );"); // 5: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MSG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Msg entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindString(2, entity.getContent());
        stmt.bindString(3, entity.getTime());
        stmt.bindString(4, entity.getNickName());
        stmt.bindLong(5, entity.getId());
        stmt.bindLong(6, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Msg entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindString(2, entity.getContent());
        stmt.bindString(3, entity.getTime());
        stmt.bindString(4, entity.getNickName());
        stmt.bindLong(5, entity.getId());
        stmt.bindLong(6, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Msg readEntity(Cursor cursor, int offset) {
        Msg entity = new Msg( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.getString(offset + 1), // content
            cursor.getString(offset + 2), // time
            cursor.getString(offset + 3), // nickName
            cursor.getLong(offset + 4), // id
            cursor.getInt(offset + 5) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Msg entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContent(cursor.getString(offset + 1));
        entity.setTime(cursor.getString(offset + 2));
        entity.setNickName(cursor.getString(offset + 3));
        entity.setId(cursor.getLong(offset + 4));
        entity.setType(cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Msg entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Msg entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Msg entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
