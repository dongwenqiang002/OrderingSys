package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.dao.sql.MenuSqlProvider;
import indi.dwq.orderingSys.data.pojo.Menu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MenuMapper {
    @Delete({
        "delete from menutbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into menutbl (id, typeID, ",
        "name, price, pic, ",
        "remark)",
        "values (#{id,jdbcType=INTEGER}, #{typeid,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{price,jdbcType=VARCHAR}, #{pic,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(Menu record);

    @InsertProvider(type=MenuSqlProvider.class, method="insertSelective")
    int insertSelective(Menu record);

    @Select({
        "select",
        "id, typeID, name, price, pic, remark",
        "from menutbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="typeID", property="typeid", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Menu selectByPrimaryKey(Integer id);

    @UpdateProvider(type=MenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Menu record);

    @Update({
        "update menutbl",
        "set typeID = #{typeid,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=VARCHAR},",
          "pic = #{pic,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Menu record);
}