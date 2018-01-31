package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.dao.sql.MenutypeSqlProvider;
import indi.dwq.orderingSys.data.pojo.Menutype;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MenutypeMapper {
    @Delete({
        "delete from menutypetbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into menutypetbl (id, name)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})"
    })
    int insert(Menutype record);

    @InsertProvider(type=MenutypeSqlProvider.class, method="insertSelective")
    int insertSelective(Menutype record);

    @Select({
        "select",
        "id, name",
        "from menutypetbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    Menutype selectByPrimaryKey(Integer id);

    @UpdateProvider(type=MenutypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Menutype record);

    @Update({
        "update menutypetbl",
        "set name = #{name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Menutype record);
}