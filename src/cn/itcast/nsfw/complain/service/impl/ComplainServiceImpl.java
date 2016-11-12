package cn.itcast.nsfw.complain.service.impl;

import cn.itcast.core.service.impl.BaseServiceImpl;
import cn.itcast.nsfw.complain.dao.ComplainDao;
import cn.itcast.nsfw.complain.entity.Complain;
import cn.itcast.nsfw.complain.service.ComplainService;

public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements ComplainService {

    private ComplainDao complainDao;

    public void setComplainDao(ComplainDao complainDao) {
        super.setBaseDao(complainDao);
        this.complainDao = complainDao;
    }



}
