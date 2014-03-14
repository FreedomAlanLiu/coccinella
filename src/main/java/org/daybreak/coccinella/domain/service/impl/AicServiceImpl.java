package org.daybreak.coccinella.domain.service.impl;

import org.daybreak.coccinella.domain.model.AIC;
import org.daybreak.coccinella.domain.repository.AICRepository;
import org.daybreak.coccinella.domain.service.AicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Alan on 14-3-14.
 */
@Service
public class AicServiceImpl implements AicService {

    @Inject
    AICRepository aicRepository;

    @Override
    public List<AIC> loadAics() {
        return aicRepository.findAll();
    }
}
