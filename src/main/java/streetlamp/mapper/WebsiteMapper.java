package streetlamp.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import streetlamp.pojo.Website;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface WebsiteMapper {
    public List<Website> queryAll();

    public Page<Website> findByPaging(Map param);
}
