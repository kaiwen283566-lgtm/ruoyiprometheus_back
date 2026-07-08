package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AiConfig;

public interface IAiConfigService {
    public AiConfig selectAiConfigById(Long id);
    public AiConfig selectAiConfigByKey(String configKey);
    public List<AiConfig> selectAiConfigList(AiConfig config);
    public int insertAiConfig(AiConfig config);
    public int updateAiConfig(AiConfig config);
    public int deleteAiConfigByIds(Long[] ids);
}
