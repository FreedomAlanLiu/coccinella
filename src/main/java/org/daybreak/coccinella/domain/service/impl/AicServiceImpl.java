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
    private AICRepository aicRepository;

    @Override
    public List<AIC> loadAics() {
        return aicRepository.findAll();
    }

    @Override
    public AIC loadAic(long id) {
        return aicRepository.findById(id);
    }

    @Override
    public AIC saveAic(AIC aic) {
        return aicRepository.save(aic);
    }

    @Override
    public void deleteAic(long id) {
        AIC aic = aicRepository.findById(id);
        if (aic != null) {
            aicRepository.delete(aic);
        }
    }
}
