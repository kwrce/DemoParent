package hbi.core.demo.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.demo.dto.Demo;

import java.util.List;

public interface DemoMapper extends Mapper<Demo>{
    List<Demo> selectByDemo(Demo demo);
    int insertDemo(Demo demo);
    int updateDemo(Demo demo);
    int deleteDemo(Demo demo);

}