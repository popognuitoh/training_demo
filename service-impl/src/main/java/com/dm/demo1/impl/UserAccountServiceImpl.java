package com.dm.demo1.impl;

import com.dm.demo1.entity.UserAccount;
import com.dm.demo1.dao.UserAccountMapper;
import com.dm.demo1.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-08-10
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
