package hbi.core.demo.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.service.IBaseService;
import hbi.core.demo.dto.Demo;

import java.util.List;

public interface IDemoService extends IBaseService<Demo>, ProxySelf<IDemoService>{
    List<Demo> selectByDemo(IRequest requestContext, Demo demo, int page, int
            pagesize);

    List<Demo> batchUpdate(IRequest requestContext, @StdWho List<Demo>
            demos);

}