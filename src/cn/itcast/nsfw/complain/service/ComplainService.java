package cn.itcast.nsfw.complain.service;

import cn.itcast.core.service.BaseService;
import cn.itcast.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain> {
    //月末自动处理本月以上投诉
    public void autoDeal();
}
