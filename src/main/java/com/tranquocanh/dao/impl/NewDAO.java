package com.tranquocanh.dao.impl;

import com.tranquocanh.builder.NewBuilder;
import com.tranquocanh.dao.ICategoryDAO;
import com.tranquocanh.dao.INewDAO;
import com.tranquocanh.mapper.NewMapper;
import com.tranquocanh.model.CategoryModel;
import com.tranquocanh.model.NewModel;
import com.tranquocanh.paging.Pageble;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {


    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public List<NewModel> findByCategoryId(Long categoryId) {

        String sql = "select * from news where categoryId = ?";
        return query(sql, new NewMapper(), categoryId);
    }

    @Override
    public Long save(NewModel newModel) {
        String sql = "insert into news(title, thumbnail, shortdescription, content, categoryid, createddate, createdby) values(?, ?, ?,?,?,?,?) ";
        return insert(sql, newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(),
                newModel.getContent(), newModel.getCategoryId(), newModel.getCreatedDate(), newModel.getCreatedBy());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from news where id = ?";
        update(sql, id);

    }

    @Override
    public NewModel findOne(Long id) {
        String sql = "select * from news where id = ?";
        List<NewModel> news = query(sql, new NewMapper(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public void update(NewModel updateNews) {

        StringBuilder sql = new
                StringBuilder("update news set title = ?, thumbnail = ?, shortdescription = ?,"
        );
        sql.
                append(" content = ?, categoryid = ?, createddate = ?, modifieddate = ?, createdby = ?, modifiedby = ?"
                );
        sql.append(" where id = ?");
        update(sql.toString(), updateNews.getTitle(),
                updateNews.getThumbnail(), updateNews.getShortDescription(),
                updateNews.getContent(), updateNews.getCategoryId(),
                updateNews.getCreatedDate(), updateNews.getModifiedDate(),
                updateNews.getCreatedBy(), updateNews.getModifiedBy(), updateNews.getId());
    }

    @Override
    public List<NewModel> findAll(NewBuilder builder, Pageble pageble) {
        //String sql = "select * from news limit ?, ?" ;
        StringBuilder sql = new StringBuilder(" select * from news as n  ");
        if(StringUtils.isNotBlank(builder.getCode())) {
            sql.append("inner join category as c on n.categoryid = c.id ");
        }
        sql.append("where 1=1 ");
        sql = buildNewQuery(sql, builder);
        if (StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" order by " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" limit " + pageble.getOffset() + "," + pageble.getLimit() + "");
        }
        return query(sql.toString(), new NewMapper());
    }

    private StringBuilder buildNewQuery(StringBuilder sql, NewBuilder builder) {
        Field[] fields = NewBuilder.class.getDeclaredFields();
        for(Field field: fields) {
            String fieldType = field.getType().getName();
            if(fieldType.equals("java.lang.String")) {
                String value = getValue(field, String.class, builder);
                if(StringUtils.isNotBlank(value)) {
                    sql.append(" and lower("+field.getName()+") like '%"+value.toLowerCase()+"%'");
                }
            }
            if(fieldType.equals("java.lang.Integer")) {
                Integer value = getValue(field, Integer.class, builder);
                if(value != null) {
                    sql.append(" and "+field.getName()+" = "+value+" ");
                }
            }
        }
        return sql;
    }

    private <T> T getValue(Field field, Class<T> type, NewBuilder builder) {
        Object result = null;
        Method getter = getGetter(field,builder);
        if(getter != null) {
            try {
                result = getter.invoke(builder);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return type.cast(result);
    }

    private Method getGetter(Field field, NewBuilder builder) {
        String getterName = "get" + StringUtils.capitalize(field.getName());
        try {
            return builder.getClass().getMethod(getterName);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }


    @Override
    public Integer getTotalItem(NewBuilder builder) {
        StringBuilder sql = new StringBuilder("select count(*) from news") ;
        sql.append(" where 1=1 ");
        sql = buildNewQuery(sql, builder);
        return getTotalItem(sql.toString());

    }


}
