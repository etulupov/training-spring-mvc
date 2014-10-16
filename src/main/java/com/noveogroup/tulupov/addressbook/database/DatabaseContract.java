package com.noveogroup.tulupov.addressbook.database;

/**
 * Database contract.
 */
public final class DatabaseContract {
    /**
     * Contact contract.
     */
    public static final class Contact {
        public static final String TABLE_NAME = "Contact";
        public static final String ID = "id";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";
        public static final String IP = "ip";
        public static final String PHOTO = "photo";
    }
}
