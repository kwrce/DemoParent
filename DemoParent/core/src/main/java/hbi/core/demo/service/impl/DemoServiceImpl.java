package hbi.core.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.DTOStatus;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.demo.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.demo.dto.Demo;
import hbi.core.demo.service.IDemoService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DemoServiceImpl extends BaseServiceImpl<Demo> implements IDemoService{
    @Autowired
    private DemoMapper demoMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor =
            Exception.class)
    public List<Demo> selectByDemo(IRequest requestContext, Demo demo, int
            page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return demoMapper.selectByDemo(demo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Demo> batchUpdate(IRequest requestContext, List<Demo> demos){
        for (Demo demo : demos) {
            if (demo.get__status() != null) {
                switch (demo.get__status()) {
                    case DTOStatus.ADD:
                        demoMapper.insertDemo(demo);
                        break;
                    case DTOStatus.UPDATE:
                        demoMapper.updateDemo(demo);
                        break;
                    default:
                        break;
                }
            }
        }
        return demos;
    }

}