package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AiConfigMapper;
import com.ruoyi.system.domain.AiConfig;
import com.ruoyi.system.service.IAiConfigService;

@Service
public class AiConfigServiceImpl implements IAiConfigService {
    @Autowired
    private AiConfigMapper aiConfigMapper;

    @Override
    public AiConfig selectAiConfigById(Long id) {
        return aiConfigMapper.selectAiConfigById(id);
    }

    @Override
    public AiConfig selectAiConfigByKey(String configKey) {
        return aiConfigMapper.selectAiConfigByKey(configKey);
    }

    @Override
    public List<AiConfig> selectAiConfigList(AiConfig config) {
        return aiConfigMapper.selectAiConfigList(config);
    }

    @Override
    public int insertAiConfig(AiConfig config) {
        return aiConfigMapper.insertAiConfig(config);
    }

    @Override
    public int updateAiConfig(AiConfig config) {
        return aiConfigMapper.updateAiConfig(config);
    }

    @Override
    public int deleteAiConfigByIds(Long[] ids) {
        return aiConfigMapper.deleteAiConfigByIds(ids);
    }
}
