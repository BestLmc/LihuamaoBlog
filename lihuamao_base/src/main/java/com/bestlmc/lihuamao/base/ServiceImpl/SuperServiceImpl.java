package com.bestlmc.lihuamao.base.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bestlmc.lihuamao.base.mapper.SuperMapper;
import com.bestlmc.lihuamao.base.service.SuperService;


public class SuperServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements SuperService<T> {

}
