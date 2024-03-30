package org.openengine.pureengine.db;

import static org.openengine.pureengine.db.DBUtil.DB_URL;

public class DBInit {

    public static void main(String[] args) {
        DBUtil.executeStatements(DB_URL, SQLQueryGenerator.getInitStatements());
    }
}
