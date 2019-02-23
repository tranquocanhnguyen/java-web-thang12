package com.tranquocanh.dao.impl;

import com.tranquocanh.builder.NewBuilder;
import com.tranquocanh.dao.INewDAO;
import com.tranquocanh.mapper.NewMapper;
import com.tranquocanh.model.NewModel;
import com.tranquocanh.paging.Pageble;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {


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
        StringBuilder sql = new StringBuilder(" select * from news");
        if (StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" order by " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" limit " + pageble.getOffset() + "," + pageble.getLimit() + "");
        }
        return query(sql.toString(), new NewMapper());
    }

    @Override
    public Integer getTotalItem() {
        String sql = "select count(*) from news";
        return getTotalItem(sql);

    }


}
