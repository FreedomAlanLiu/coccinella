package org.daybreak.coccinella.domain.service;

import org.daybreak.coccinella.domain.model.AIC;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Alan on 14-3-14.
 */
public interface AicService {

    public List<AIC> loadAics();
}
