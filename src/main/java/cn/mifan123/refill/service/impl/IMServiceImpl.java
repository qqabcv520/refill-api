package cn.mifan123.refill.service.impl;

import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.mifan123.refill.common.exception.BusinessException;
import cn.mifan123.refill.config.CommonConfig;
import cn.mifan123.refill.service.IMService;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IMServiceImpl implements IMService {

    @Resource
    private CommonConfig commonConfig;

    private JMessageClient __client;

    @Synchronized
    private JMessageClient getClient() {
        if(__client == null) {
            __client = new JMessageClient(commonConfig.getJMessageAppKey(), commonConfig.getJMessageSecret());
        }
        return __client;
    }

    @Override
    public void registerUsers(String username, String password) {

        try {
            RegisterInfo registerInfo = RegisterInfo.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
            getClient().registerUsers(new RegisterInfo[]{registerInfo});
        } catch (Exception e) {
            throw new BusinessException("IM注册失败：" + e.getMessage());
        }
    }
}
