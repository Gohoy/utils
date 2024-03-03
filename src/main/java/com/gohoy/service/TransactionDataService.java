package com.gohoy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gohoy.entities.TransactionData;
import com.gohoy.mapper.TransactionDataMapper;
import org.springframework.stereotype.Service;

@Service
public class TransactionDataService extends ServiceImpl<TransactionDataMapper, TransactionData> {
}
