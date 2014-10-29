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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
public class ContactDaoJDBCTemplateImpl implements ContactDao {
    private static final int PARAM_FIRST_NAME = 1;
    private static final int PARAM_LAST_NAME = 2;
    private static final int PARAM_EMAIL = 3;
    private static final int PARAM_PHONE = 4;
    private static final int PARAM_IP = 5;
    private static final int PARAM_PHOTO = 6;
    private static final DSLContext CONTEXT = DSL.using(SQLDialect.H2);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactDaoJDBCTemplateImpl(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
                        final PreparedStatement statement =
                                connection.prepareStatement(sql, new String[]{ID});
                        statement.setString(PARAM_FIRST_NAME, contact.getFirstname());
                        statement.setString(PARAM_LAST_NAME, contact.getLastname());
                        statement.setString(PARAM_EMAIL, contact.getEmail());
                        statement.setString(PARAM_PHONE, contact.getPhone());
                        statement.setInt(PARAM_IP, contact.getIp());
                        statement.setBlob(PARAM_PHOTO, new ByteArrayInputStream(contact.getPhoto()));

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

        getJdbcTemplate().update(query,
                new Object[]{
                        contact.getFirstname(),
                        contact.getLastname(),
                        contact.getEmail(),
                        contact.getPhone(),
                        contact.getIp(),
                        contact.getPhoto(),
                        contact.getId(),
                });
    }

    @Override
    public List<Contact> query(final Pageable pageable) {
        final Collection<SortField<Object>> sortFields = new ArrayList<>();

        final Sort sort = pageable.getSort();

        if (sort != null) {
            for (final Sort.Order order : sort) {
                sortFields.add(order.isAscending() ? field(order.getProperty()).asc()
                        : field(order.getProperty()).desc());
            }
        }

        final String sql = CONTEXT.select()
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

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
