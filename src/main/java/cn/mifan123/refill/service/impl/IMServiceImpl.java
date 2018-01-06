package cn.mifan123.refill.service.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
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
        RegisterInfo registerInfo = RegisterInfo.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        try {
            getClient().registerUsers(new RegisterInfo[]{registerInfo});
        } catch (APIConnectionException | APIRequestException e) {
            throw new BusinessException("IM注册失败：" + e.getMessage());
        }
    }
}
