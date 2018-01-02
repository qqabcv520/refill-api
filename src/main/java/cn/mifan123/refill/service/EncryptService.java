package cn.mifan123.refill.service;

/**
 * Created by 米饭 on 2017-05-23.
 */
public interface EncryptService {


    /**
     * 加密
     * @author 范子才
     * @param password 需要加密的内容
     * @param salt 加盐
     * @return 密文
     * @version 2016年4月22日 下午10:52:45
     */
    String encryptPassword(String password, String salt);


    /**
     * 加密
     * @author 范子才
     * @param token 需要加密的内容
     * @return 密文
     * @version 2016年4月22日 下午10:52:45
     */
    String tokenDigest(String token);

    /**
     * 字符串哈希
     * @param strText 需要加密的字符串，加密类型
     * @param strType 哈希类型
     * @return
     */
    String messageDigest(String strText, String strType);

    /**
     * 生成简单随机字符串token
     * @author 范子才
     * @return
     * @version 2016年4月22日 下午10:52:45
     */
    String generateToken();


    /**
     * 生成JWT字符串token
     * @param id
     * @return
     */
    String generateToken(Integer id);
}
