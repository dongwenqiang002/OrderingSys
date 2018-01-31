package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.dao.sql.TableSqlProvider;
import indi.dwq.orderingSys.data.pojo.Table;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TableMapper {
    @Delete({
        "delete from tabletbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tabletbl (id, Ord_id, ",
        "num, flag, description)",
        "values (#{id,jdbcType=INTEGER}, #{ordId,jdbcType=INTEGER}, ",
        "#{num,jdbcType=VARCHAR}, #{flag,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR})"
    })
    int insert(Table record);

    @InsertProvider(type=TableSqlProvider.class, method="insertSelective")
    int insertSelective(Table record);

    @Select({
        "select",
        "id, Ord_id, num, flag, description",
        "from tabletbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Ord_id", property="ordId", jdbcType=JdbcType.INTEGER),
        @Result(column="num", property="num", jdbcType=JdbcType.VARCHAR),
        @Result(column="flag", property="flag", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    Table selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TableSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Table record);

    @Update({
        "update tabletbl",
        "set Ord_id = #{ordId,jdbcType=INTEGER},",
          "num = #{num,jdbcType=VARCHAR},",
          "flag = #{flag,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Table record);
}