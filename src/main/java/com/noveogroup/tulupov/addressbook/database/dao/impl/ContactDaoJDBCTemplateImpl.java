package com.noveogroup.tulupov.addressbook.database.dao.impl;


import com.noveogroup.tulupov.addressbook.database.dao.ContactDao;
import com.noveogroup.tulupov.addressbook.model.Contact;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.SortField;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.noveogroup.tulupov.addressbook.model.Contact.*;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

/**
 * Contact dao JDBC template implementation.
 */
public class ContactDaoJDBCTemplateImpl extends JdbcDaoSupport implements ContactDao {

    private static final DSLContext CONTEXT = DSL.using(SQLDialect.H2);

    @Autowired
    public ContactDaoJDBCTemplateImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public void add(final Contact contact) {
        final String sql = CONTEXT.insertInto(table(TABLE_NAME),
                field(FIRST_NAME),
                field(LAST_NAME),
                field(EMAIL),
                field(PHONE),
                field(IP),
                field(PHOTO))
                .values(contact.getFirstname(),
                        contact.getLastname(),
                        contact.getEmail(),
                        contact.getPhone(),
                        contact.getIp(),
                        contact.getPhoto())
                .getSQL();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement statement =
                                connection.prepareStatement(sql, new String[] {ID});
                        statement.setString(1, contact.getFirstname());
                        statement.setString(2, contact.getLastname());
                        statement.setString(3, contact.getEmail());
                        statement.setString(4, contact.getPhone());
                        statement.setInt(5, contact.getIp());
                        statement.setBlob(6, new ByteArrayInputStream(contact.getPhoto()));

                        return statement;
                    }
                },
                keyHolder);

        contact.setId((Integer) keyHolder.getKey());
    }

    @Override
    public void remove(final Contact contact) {
        final String query = CONTEXT.delete(table(TABLE_NAME))
                .where(field(ID).equal(contact.getId()))
                .getSQL(ParamType.INLINED);

        getJdbcTemplate().update(query);
    }

    @Override
    public Contact get(final Integer id) {
        final String query = CONTEXT.select().from(table(TABLE_NAME))
                .where(field(ID).equal(id))
                .getSQL(ParamType.INLINED);

        final Contact contact = (Contact) getJdbcTemplate().queryForObject(
                query, new BeanPropertyRowMapper(Contact.class));

        return contact;
    }

    @Override
    public void update(final Contact contact) {
        final String query = CONTEXT.update(table(TABLE_NAME))
                .set(field(FIRST_NAME), contact.getFirstname())
                .set(field(LAST_NAME), contact.getLastname())
                .set(field(EMAIL), contact.getEmail())
                .set(field(PHONE), contact.getPhone())
                .set(field(IP), contact.getIp())
                .set(field(PHOTO), contact.getPhoto())
                .where(field(ID).equal(contact.getId())).getSQL();

        getJdbcTemplate().update(query, new Object[]{
                contact.getFirstname(),
                contact.getLastname(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getIp(),
                contact.getPhoto(),
                contact.getId()
        });
    }

    @Override
    public List<Contact> query(Pageable pageable) {
        Collection<SortField<Object>> sortFields = new ArrayList<>();

        Sort sort = pageable.getSort();

        if (sort != null) {
            for (Sort.Order order : sort) {
                sortFields.add(order.isAscending() ? field(order.getProperty()).asc() :
                        field(order.getProperty()).desc());
            }
        }

        String sql = CONTEXT.select()
                .from(TABLE_NAME)
                .orderBy(sortFields)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset()).getSQL();

        return getJdbcTemplate().query(sql,
                new BeanPropertyRowMapper(Contact.class),
                new Object[]{pageable.getPageSize(), pageable.getOffset()});
    }

    @Override
    @SuppressWarnings("deprecation")
    public long count() {
        final String query = CONTEXT.select(org.jooq.impl.DSL.count())
                .from(table(TABLE_NAME)).getSQL();

        return getJdbcTemplate().queryForLong(query);
    }
}
